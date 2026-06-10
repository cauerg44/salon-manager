import './styles.css';
import { useEffect, useState } from 'react';
import FormInput from '../../../../components/FormInput/index.tsx';
import { useNavigate, useParams } from 'react-router-dom';
import * as forms from '../../../../utils/forms.ts';
import * as specialtyService from '../../../../services/specialization-service.ts';

export default function SpecialtyForm() {

  const params = useParams();

  const navigate = useNavigate();

  const isEditing = params.specialtyId !== undefined;

  const [formData, setFormData] = useState<any>({
    name: {
      value: "",
      id: "name",
      name: "name",
      type: "text",
      placeholder: "Nome",
      validation: function (value: string) {
        return value.length >= 3 && value.length <= 30;
      },
      message: "Favor informar um nome de 3 e 30 caracteres"
    }
  });

  useEffect(() => {
    if (isEditing) {
      specialtyService.findSpecialtyById(Number(params.specialtyId))
        .then(response => {
          const newFormData = forms.updateAll(formData, response.data);
          setFormData(newFormData);
        })
    }
  }, []);

  function handleInputChange(event: any) {
    setFormData(forms.updateAndValidate(formData, event.target.name, event.target.value));
  }

  function handleTurnDirty(name: string) {
    setFormData(forms.dirtyAndValidate(formData, name));
  }

  function handleSubmit(event: any) {
    event.preventDefault();

    // Suja todos os campos e valida
    const formDataValidated = forms.dirtyAndValidateAll(formData);

    // Se algum campo for inválido
    if (forms.hasAnyInvalid(formDataValidated)) {
      setFormData(formDataValidated);
      return;
    }

    // Transforma o formData para Payload
    const requestBody = forms.toValues(formData);

    // Se estiver editando, pega o ID
    if (isEditing) {
      requestBody.id = Number(params.specialtyId);
    }

    const request = isEditing
      ? specialtyService.updateSpecialty(requestBody)
      : specialtyService.create(requestBody);


    request.then(() => {
      navigate("/specializations/listing");
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
          {isEditing ? "Editar especialidade:" : "Nova especialidade:"}
        </h2>

        <div className='bcf-form-modal-container'>

          <h3>{isEditing ? 'Editar' : 'Cadastrar'}</h3>

          <form onSubmit={handleSubmit} className='bcf-form-modal'>

            <div className="bcf-form-control">
              <FormInput
                {...formData.name}
                onTurnDirty={handleTurnDirty}
                onChange={handleInputChange}
              />
              <div className='bcf-form-error'>{formData.name.message}</div>
            </div>

            <button type='submit'>
              {isEditing ? 'Salvar alterações' : 'Salvar'}
            </button>

          </form>
        </div>
      </section>
    </>
  );
}