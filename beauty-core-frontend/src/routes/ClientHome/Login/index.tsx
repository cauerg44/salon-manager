import './styles.css';
import { useState } from 'react';
import type { CredentialsDTO } from '../../../models/auth';
import * as authService from '../../../services/auth-service.ts';

export default function Login() {

  const [formData, setFormData] = useState<CredentialsDTO>({
    email: '',
    password: ''
  });

  function handleSubmit(event: any) {
    event.preventDefault();
    authService.loginRequest(formData)
      .then(response => {
        authService.saveAccessToken(response.data.token);
      })
      .catch(error => {
        console.log("Erro no login ", error)
      })
  }

  function handleInputChange(event: any) {
    const value = event.target.value;
    const name = event.target.name;
    setFormData({ ...formData, [name]: value });
  }

  return (
    <section id="login-section" className="bcf-container-1200px">
      <div className='bcf-modal-form-login-container'>
        <h2>Login</h2>
        <form className='bcf-modal-form' onSubmit={handleSubmit}>
          <input
            name='email'
            value={formData.email}
            type="text"
            placeholder='Email'
            onChange={handleInputChange}
          />
          <input
            name='password'
            value={formData.password}
            type="text"
            placeholder='Senha'
            onChange={handleInputChange}
          />
          <button type='submit'>Entrar</button>
        </form>
      </div>
    </section>
  );
}