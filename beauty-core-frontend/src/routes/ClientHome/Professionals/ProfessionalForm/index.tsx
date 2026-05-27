import { useEffect, useState } from 'react';
import './styles.css';
import FormInput from '../../../../components/FormInput';
import * as forms from '../../../../utils/forms.ts';
import * as professionalService from '../../../../services/professional-service.ts';
import { useParams } from 'react-router-dom';

export default function ProfessionalForm() {

  const params = useParams();

  const isEditing = params.professionalId !== undefined;

  const [formData, setFormData] = useState<any>({
    name: {
      value: "",
      id: "name",
      name: "name",
      type: "text",
      placeholder: "Nome",
    },
    email: {
      value: "",
      id: "email",
      name: "email",
      type: "text",
      placeholder: "Email",
      validation: function (value: string) {
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
    }
  });

  useEffect(() => {
    if (isEditing) {
      professionalService.findProfessionalById(Number(params.professionalId))
        .then(response => {
          console.log('formData', formData);
          console.log('response.data', response.data);
          const newFormData = forms.updateAll(formData, response.data);
          setFormData(newFormData);
        })
    }
  }, [])

  function handleInputChange(event: any) {
    setFormData(forms.update(formData, event.target.name, event.target.value));
  }

  function handleSubmit(event: any) {
    event.preventDefault();
  }

  return (
    <>
      <section id='professional-form-section' className='bcf-container-1200px'>

        <h2 className='bcf-form-title-section'>Novo profissional:</h2>

        <div className='bcf-professional-form-modal-container'>
          <h3>Dados do profissional: </h3>

          <form onSubmit={handleSubmit} className='bcf-professional-form'>
            <FormInput
              {...formData.name}
              onChange={handleInputChange}
            />
            <FormInput
              {...formData.email}
              onChange={handleInputChange}
            />
            {
              !isEditing &&
              <FormInput
                {...formData.password}
                onChange={handleInputChange}
              />
            }

            {/* <select className='bcf-select' required>
              <option value="" disabled selected>Especialidades</option>
              <option value="1">Cabeleleiro(a)</option>
              <option value="2">Trancista</option>
              <option value="3">Esteticista</option>
            </select> */}
            {
              isEditing
                ?
                <button type='submit'>Salvar alterações</button>
                :
                <button type='submit'>Registrar</button>
            }

          </form>

        </div>

      </section>
    </>
  );
}