import './styles.css';
import { useNavigate, useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import * as forms from '../../../../utils/forms.ts';
import * as clientService from '../../../../services/client-service.ts';
import type { ProfessionalDTO } from '../../../../models/professional.ts';
import type { ClientDTO } from '../../../../models/client.ts';
import FormSelect from '../../../../components/FormSelect/index.tsx';
import { selectStyles } from '../../../../utils/select.ts';
import * as appointmentService from '../../../../services/appointment-service.ts';
import * as professionalService from '../../../../services/professional-service.ts';
import * as jobItemService from '../../../../services/job-item-service.ts';
import type { ServiceDTO } from '../../../../models/service-dto.ts';
import FormInput from '../../../../components/FormInput/index.tsx';
import DialogModalInfo from '../../../../components/DialogInfo/index.tsx';
import type { AppointmentDTO } from '../../../../models/appointment.ts';

export default function AppointmentForm() {

  const params = useParams();

  const navigate = useNavigate();

  const isEditing = params.appointmentId !== undefined;

  const [appointment, setAppointment] = useState<AppointmentDTO>();

  const [professionals, setProfessionals] = useState<ProfessionalDTO[]>([]);

  const [clients, setClients] = useState<ClientDTO[]>([]);

  const [services, setServices] = useState<ServiceDTO[]>([]);

  const [dialogInfoData, setDialogInfoData] = useState({
    visible: false,
    message: "Operação feita com sucesso!"
  })

  const [formData, setFormData] = useState<any>({
    professionals: {
      value: null,
      id: 'professionals',
      name: 'professionals',
      placeholder: 'Selecionar profissional',
      validation: function (value: ProfessionalDTO) {
        return value != null;
      },
      message: "Favor escolher um profissional para o atendimento"
    },
    clients: {
      value: null,
      id: 'clients',
      name: 'clients',
      placeholder: 'Selecionar cliente',
      validation: function (value: ClientDTO) {
        return value != null;
      },
      message: "Favor escolher um cliente para o atendimento"
    },
    services: {
      value: [],
      id: 'services',
      name: 'services',
      placeholder: 'Serviços',
      validation: function (value: any[]) {
        return value.length > 0;
      },
      message: "Escolha ao menos um serviço para esse atendimento"
    },
    discount: {
      value: 0,
      id: 'discount',
      name: 'discount',
      placeholder: 'Desconto',
      type: 'number',
      validation: function (value: any) {
        return Number(value) >= 0;
      },
      message: "Desconto deve ser positivo"
    }
  })

  useEffect(() => {
    if (isEditing) {
      appointmentService.findAppointmentById(Number(params.appointmentId))
        .then(response => {

          setAppointment(response.data);

          const adaptedData = {
            professionals: response.data.professional,
            clients: response.data.client,
            services: response.data.services,
            discount: response.data.discount
          };

          const formDataUpdated = forms.updateAll(formData, adaptedData);
          setFormData(formDataUpdated);
        });
    }
  }, []);

  useEffect(() => {
    professionalService.findAllProfessionals(0, "", 100, "name")
      .then(response => {
        setProfessionals(response.data.content);
      });
  }, []);

  useEffect(() => {
    clientService.findAllClientsNotInAppointment()
      .then(response => {
        setClients(response.data);
      });
  }, []);

  useEffect(() => {
    jobItemService.findAllServices()
      .then(response => {
        setServices(response.data);
      });
  }, []);

  function handleTurnDirty(name: string) {
    setFormData(forms.dirtyAndValidate(formData, name));
  }

  function handleInputChange(event: any) {
    console.log(event.target.value, typeof event.target.value);
    setFormData(forms.updateAndValidate(formData, event.target.name, event.target.value));
  }

  function handleDialogInfoClose() {
    setDialogInfoData({ ...dialogInfoData, visible: false });
    if (!isEditing) {
      navigate("/appointments/in-waiting");
    }
  }

  function handleSubmit(event: any) {

    event.preventDefault();

    const formDataValidated = forms.dirtyAndValidateAll(formData);

    if (forms.hasAnyInvalid(formDataValidated)) {
      setFormData(formDataValidated);
      return;
    }

    const requestBody = forms.toValues(formData);

    requestBody.professionalId = requestBody.professionals.id;
    requestBody.clientId = requestBody.clients.id;
    requestBody.servicesIds = requestBody.services.map((obj: ServiceDTO) => obj.id);

    delete requestBody.professionals;
    delete requestBody.clients;
    delete requestBody.services;

    if (isEditing) {
      requestBody.id = Number(params.appointmentId);
    }

    const request = isEditing
      ? appointmentService.update(requestBody)
      : appointmentService.create(requestBody)

    request
      .then(() => {
        setDialogInfoData({
          visible: true,
          message: isEditing
            ? 'Atendimento editado com sucesso!'
            : 'Atendimento criado com sucesso!'
        });
      })
      .catch(error => {
        console.log(error);
      })
  }

  return (
    <>
      <section id='professional-form-section' className='bcf-container-1200px'>

        <h2 className='bcf-form-title-section'>
          {isEditing ? "Editar atendimento:" : "Novo atendimento:"}
        </h2>

        <div className='bcf-professional-form-modal-container'>
          <h3>Dados do atendimento: </h3>

          <form onSubmit={handleSubmit} className='bcf-professional-form'>

            <FormSelect
              {...formData.professionals}
              className='bcf-form-select bcf-form-select-container'
              styles={selectStyles}
              options={professionals}
              onChange={(obj: any) => {
                const newFormData = forms.updateAndValidate(formData, "professionals", obj);
                setFormData(newFormData);
              }}
              onTurnDirty={handleTurnDirty}
              getOptionLabel={(obj: any) => obj.name}
              getOptionValue={(obj: any) => String(obj.id)}
            />
            <div className='bcf-form-error'>{formData.professionals.message}</div>

            <FormSelect
              {...formData.clients}
              className='bcf-form-select bcf-form-select-container'
              styles={selectStyles}
              options={clients}
              onChange={(obj: any) => {
                const newFormData = forms.updateAndValidate(formData, "clients", obj);
                setFormData(newFormData);
              }}
              onTurnDirty={handleTurnDirty}
              getOptionLabel={(obj: any) => obj.name + " " + obj.birthDate}
              getOptionValue={(obj: any) => String(obj.id)}
            />
            <div className='bcf-form-error'>{formData.clients.message}</div>

            {
              appointment?.appointmentStatus !== 'FINISHED' &&
              <>
                <FormSelect
                  {...formData.services}
                  className='bcf-form-select bcf-form-select-container'
                  styles={selectStyles}
                  options={services}
                  onChange={(obj: any) => {
                    const newFormData = forms.updateAndValidate(formData, "services", obj);
                    setFormData(newFormData);
                  }}
                  onTurnDirty={handleTurnDirty}
                  isMulti
                  getOptionLabel={(obj: any) => obj.name}
                  getOptionValue={(obj: any) => String(obj.id)}
                />
                <div className='bcf-form-error'>{formData.services.message}</div>

              </>
            }

            {
              isEditing && appointment?.appointmentStatus !== 'FINISHED' &&
              <>
                <h5 className='bcf-discount-message-form'>Aplique o desconto se desejar:</h5>
                <div className="bcf-form-control">
                  <FormInput
                    {...formData.discount}
                    onTurnDirty={handleTurnDirty}
                    onChange={handleInputChange}
                  />
                  <div className='bcf-form-error'>{formData.discount.message}</div>
                </div>
              </>
            }

            <button type='submit'>
              {isEditing ? 'Salvar alterações' : 'Registrar'}
            </button>

          </form>
        </div>
      </section>

      {
        dialogInfoData.visible &&
        <DialogModalInfo
          message={dialogInfoData.message}
          onDialogClose={handleDialogInfoClose}
        />
      }
    </>
  );
}