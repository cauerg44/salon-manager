import './styles.css';
import FormInput from '../../../../components/FormInput/index.tsx';
import { useNavigate, useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import * as forms from '../../../../utils/forms.ts';
import * as jobItemService from '../../../../services/job-item-service.ts';

export default function ServiceForm() {

  const params = useParams();

  const navigate = useNavigate();

  const isEditing = params.serviceId !== undefined;

  const [formData, setFormData] = useState<any>({
    name: {
      value: "",
      id: "name",
      name: "name",
      type: "text",
      placeholder: "Nome do serviço",
      validation: function (value: string) {
        return value.length >= 3 && value.length <= 30;
      },
      message: "Nome do serviço deve conter no máximo 30 caracteres"
    },
    basePrice: {
      value: "",
      id: "basePrice",
      name: "basePrice",
      type: "number",
      placeholder: "Preço do serviço",
      validation: function (value: any) {
        return Number(value) > 0;
      },
      message: "O preço deve ser positivo",
    }
  })

  useEffect(() => {
    if (isEditing) {
      jobItemService.findById(Number(params.serviceId))
        .then(response => {
          const formDataUpdated = forms.updateAll(formData, response.data);
          setFormData(formDataUpdated)
        })
    }
  }, [])

  function handleInputChange(event: any) {
    setFormData(forms.updateAndValidate(formData, event.target.name, event.target.value));
  }

  function handleTurnDirty(name: string) {
    setFormData(forms.dirtyAndValidate(formData, name));
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
      requestBody.id = Number(params.serviceId);
      console.log(requestBody);
    }

    const request = isEditing
      ? jobItemService.update(requestBody)
      : jobItemService.create(requestBody)

    request
      .then(() => {
        navigate("/services/listing")
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
          {isEditing ? "Editar serviço:" : "Novo serviço:"}
        </h2>

        <div className='bcf-professional-form-modal-container'>
          <h3>Dados do serviço: </h3>

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
                {...formData.basePrice}
                onTurnDirty={handleTurnDirty}
                onChange={handleInputChange}
              />
              <div className='bcf-form-error'>{formData.basePrice.message}</div>
            </div>

            <button type='submit'>
              {isEditing ? 'Salvar alterações' : 'Cadastrar serviço'}
            </button>

          </form>
        </div>
      </section>
    </>
  );
}