import { useEffect, useState } from 'react';
import './styles.css';
import FormInput from '../../../../components/FormInput';
import * as forms from '../../../../utils/forms.ts';
import * as specialtyService from '../../../../services/specialization-service.ts';
import * as professionalService from '../../../../services/professional-service.ts';
import * as authService from '../../../../services/auth-service.ts';
import { useNavigate, useParams } from 'react-router-dom';
import type { SpecialtyDTO } from '../../../../models/specialty.ts';
import FormSelect from '../../../../components/FormSelect/index.tsx';
import { selectStyles } from '../../../../utils/select.ts';

export default function ProfessionalForm() {

  const params = useParams();

  const navigate = useNavigate();

  const isEditing = params.professionalId !== undefined;

  const [specializations, setSpecializations] = useState<SpecialtyDTO[]>([]);

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
    email: {
      value: "",
      id: "email",
      name: "email",
      type: "text",
      placeholder: "Email",
      validation: function (value: string = "") {
        return /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(value.toLowerCase());
      },
      message: "Favor informar um email válido",
    },
    password: {
      value: "",
      id: "password",
      name: "password",
      type: "password",
      placeholder: "Senha",
      validation: function (value: string = "") {
        return isEditing ? true : value.length > 0;
      },
      message: "A senha não pode ser vazia",
    },
    specializations: {
      value: [],
      id: 'specializations',
      name: 'specializations',
      placeholder: 'Especialidades',
      validation: function (value: SpecialtyDTO[]) {
        return value.length > 0;
      },
      message: "Escolha ao menos uma especialidade"
    }
  });

  useEffect(() => {
    specialtyService.findAll()
      .then(response => {
        setSpecializations(response.data);
      });
  }, []);

  useEffect(() => {
    if (isEditing) {
      professionalService.findProfessionalById(Number(params.professionalId))
        .then(response => {
          const newFormData = forms.updateAll(formData, response.data);
          setFormData(newFormData);
        });
    }
  }, []);

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
    requestBody.specializationsIds = requestBody.specializations.map((obj: SpecialtyDTO) => obj.id);
    delete requestBody.specializations;

    if (isEditing) {
      requestBody.id = Number(params.professionalId);
      delete requestBody.password;
    }

    const request = isEditing
      ? professionalService.updateProfessional(requestBody)
      : authService.registerRequest(requestBody);

    request
      .then(() => {
        navigate("/professionals/listing");
      })
      .catch(error => {
        const newInputs = forms.setBackendErrors(formData, error.response.data.errors);
        setFormData(newInputs);
      });
  }

  function handleTurnDirty(name: string) {
    setFormData(forms.dirtyAndValidate(formData, name));
  }

  return (
    <>
      <section id='professional-form-section' className='bcf-container-1200px'>

        <h2 className='bcf-form-title-section'>
          {isEditing ? "Editar profissional:" : "Novo profissional:"}
        </h2>

        <div className='bcf-form-modal-container'>
          <h3>Dados do profissional: </h3>

          <form onSubmit={handleSubmit} className='bcf-form-modal'>

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
                {...formData.email}
                onTurnDirty={handleTurnDirty}
                onChange={handleInputChange}
              />
              <div className='bcf-form-error'>{formData.email.message}</div>
            </div>

            {
              !isEditing && (
                <div className="bcf-form-control">
                  <FormInput
                    {...formData.password}
                    onTurnDirty={handleTurnDirty}
                    onChange={handleInputChange}
                  />
                  <div className='bcf-form-error'>{formData.password.message}</div>
                </div>
              )
            }

            <FormSelect
              {...formData.specializations}
              className='bcf-form-select bcf-form-select-container'
              styles={selectStyles}
              options={specializations}
              onChange={(obj: any) => {
                const newFormData = forms.updateAndValidate(formData, "specializations", obj);
                setFormData(newFormData);
              }}
              onTurnDirty={handleTurnDirty}
              isMulti
              getOptionLabel={(obj: any) => obj.name}
              getOptionValue={(obj: any) => String(obj.id)}
            />
            <div className='bcf-form-error'>{formData.specializations.message}</div>

            <button type='submit'>
              {isEditing ? 'Salvar alterações' : 'Registrar'}
            </button>

          </form>
        </div>
      </section>
    </>
  );
}