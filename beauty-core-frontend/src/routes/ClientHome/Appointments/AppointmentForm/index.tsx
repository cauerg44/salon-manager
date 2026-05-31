import './styles.css';
import FormInput from '../../../../components/FormInput/index.tsx';
import { useNavigate, useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import * as forms from '../../../../utils/forms.ts';
import * as clientService from '../../../../services/client-service.ts';

export default function AppointmentForm() {

  const params = useParams();

  const navigate = useNavigate();

  const isEditing = params.clientId !== undefined;

  const [formData, setFormData] = useState<any>({
    name: {
      value: "",
      id: "name",
      name: "name",
      type: "text",
      placeholder: "Nome",
      validation: function (value: string) {
        return value.length >= 3 && value.length <= 80;
      },
      message: "Favor informar um nome de 3 e 80 caracteres"
    },
    phone: {
      value: "",
      id: "phone",
      name: "phone",
      type: "text",
      placeholder: "Telefone",
      validation: function (value: string = "") {
        return /^\d{11}$/.test(value);
      },
      message: "Favor informar um telefone com 11 dígitos",
    },
    birthDate: {
      value: "",
      id: "birthDate",
      name: "birthDate",
      type: "text",
      placeholder: "Data de nascimento (dia/mês/ano)",
      mask: "dd/mm/yyyy",
      replacement: {
        d: /\d/,
        m: /\d/,
        y: /\d/,
      },
      validation: function (value: string = "") {
        return /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/\d{4}$/.test(value);
      },
      message: "Favor informar uma data válida",
    }
  })

  useEffect(() => {
    if (isEditing) {
      clientService.findClientById(Number(params.clientId))
        .then(response => {
          const formDataUpdated = forms.updateAll(formData, response.data);
          setFormData(formDataUpdated);
        })
    }
  }, []);

  function handleTurnDirty(name: string) {
    setFormData(forms.dirtyAndValidate(formData, name));
  }

  function handleInputChange(event: any) {
    setFormData(forms.updateAndValidate(formData, event.target.name, event.target.value));
  }

  function handleSubmit(event: any) {
    event.preventDefault();

    const formDataValidated = forms.dirtyAndValidateAll(formData);

    if (forms.hasAnyInvalid(formDataValidated)) {
      setFormData(formDataValidated);
      return;
    }

    const requestBody = forms.toValues(formData);

    if (isEditing) {
      requestBody.id = Number(params.clientId);
    }

    const request = isEditing
      ? clientService.update(requestBody)
      : clientService.create(requestBody);

    request
      .then(() => {
        navigate("/clients/listing");
      })
      .catch(error => {
        const newInputs = forms.setBackendErrors(formData, error.response.data.errors);
        setFormData(newInputs);
      })
  }

  return (
    <>
      <section id='professional-form-section' className='bcf-container-1200px'>

        <h2 className='bcf-form-title-section'>
          {isEditing ? "Editar cliente:" : "Novo cliente:"}
        </h2>

        <div className='bcf-professional-form-modal-container'>
          <h3>Dados do cliente: </h3>

          <form onSubmit={handleSubmit} className='bcf-professional-form'>

            <div className="bcf-form-control">
              <FormInput
                {...formData.name}
                onTurnDirty={handleTurnDirty}
                onChange={handleInputChange}
              />
              <div className='bcf-form-error'>{formData.name.message}</div>
            </div>

            <div className="bcf-form-control">
              <FormInput
                {...formData.phone}
                onTurnDirty={handleTurnDirty}
                onChange={handleInputChange}
              />
              <div className='bcf-form-error'>{formData.phone.message}</div>
            </div>

            <div className="bcf-form-control">
              <FormInput
                {...formData.birthDate}
                onTurnDirty={handleTurnDirty}
                onChange={handleInputChange}
              />
              <div className='bcf-form-error'>{formData.birthDate.message}</div>
            </div>

            <button type='submit'>
              {isEditing ? 'Salvar alterações' : 'Registrar'}
            </button>

          </form>
        </div>
      </section>
    </>
  );
}