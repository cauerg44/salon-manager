import './styles.css';
import { useState } from 'react';
import * as authService from '../../../services/auth-service.ts';
import { useNavigate } from 'react-router-dom';
import FormInput from '../../../components/FormInput/index.tsx';

export default function Login() {

  const navigate = useNavigate();

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
    }
  })

  function handleSubmit(event: any) {
    event.preventDefault();
    authService.loginRequest({ email: formData.email.value, password: formData.password.value })
      .then(response => {
        authService.saveAccessToken(response.data.token);
        navigate("/home");
      })
      .catch(error => {
        console.log("Erro no login ", error)
      })
  }

  function handleInputChange(event: any) {
    const value = event.target.value;
    const name = event.target.name;
    setFormData({ ...formData, [name]: { ...formData[name], value: value } });
  }

  return (
    <section id="login-section" className="bcf-container-1200px">
      <div className='bcf-modal-form-login-container'>
        <h2>Login</h2>
        <form className='bcf-modal-form' onSubmit={handleSubmit}>
          <FormInput
            {...formData.email}
            onChange={handleInputChange}
          />
          <FormInput
            {...formData.password}
            onChange={handleInputChange}
          />
          <button type='submit'>Entrar</button>
        </form>
      </div>
    </section>
  );
}