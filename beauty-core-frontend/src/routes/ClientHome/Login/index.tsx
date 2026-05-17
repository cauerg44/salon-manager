import { Link } from 'react-router-dom';
import ButtonPrimary from '../../../components/ButtonPrimary';
import './styles.css';

export default function Login() {
  return (
    <section id="login-section" className="bcf-container-1200px">
      <div className='bcf-modal-form-login-container'>
        <h2>Login</h2>
        <form className='bcf-modal-form'>
          <input type="text" placeholder='Email' />
          <input type="text" placeholder='Senha' />
          <button type='submit'>Entrar</button>
        </form>
      </div>
    </section>
  );
}