import './styles.css';
import { useState } from 'react';
import * as authService from '../../../services/auth-service.ts';
import { useNavigate } from 'react-router-dom';
import FormInput from '../../../components/FormInput/index.tsx';
import * as forms from '../../../utils/forms.ts';

export default function Login() {

  const navigate = useNavigate();

  const [submitResponseFail, setSubmitResponseFail] = useState(false);

  const [formData, setFormData] = useState<any>({
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
      validation: function (value: string) {
        return value.length === 0;
      },
      message: "A senha não pode ser vazia",
    }
  })

  function handleSubmit(event: any) {
    event.preventDefault();

    setSubmitResponseFail(false);

    authService.loginRequest(forms.toValues(formData))
      .then(response => {
        authService.saveAccessToken(response.data.token);
        navigate("/home");
      })
      .catch(() => {
        setSubmitResponseFail(true);
      })
  }

  function handleInputChange(event: any) {
    setFormData(forms.updateAndValidate(formData, event.target.name, event.target.value));
  }

  function handleTurnDirty(name: string) {
    setFormData(forms.dirtyAndValidate(formData, name));
  }

  return (
    <section id="login-section" className="bcf-container-1200px">
      <div className='bcf-modal-form-login-container'>
        <h2>Login</h2>
        <form className='bcf-modal-form' onSubmit={handleSubmit}>
          <FormInput
            {...formData.email}
            onTurnDirty={handleTurnDirty}
            onChange={handleInputChange}
          />
          <div className='bcf-form-error'>{formData.email.message}</div>

          <FormInput
            {...formData.password}
            onTurnDirty={handleTurnDirty}
            onChange={handleInputChange}
          />

          {
            submitResponseFail &&
            <div className='bcf-form-global-error'>
              Usuário ou senha inválidos
            </div>
          }

          <button type='submit'>Entrar</button>
        </form>
      </div>
    </section>
  );
}