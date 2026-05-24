import './styles.css';
import * as authService from '../../../services/auth-service.ts';
import { Link, useNavigate } from 'react-router-dom';

export default function Profile() {

  const navigate = useNavigate();

  function handleClickLogout() {
    authService.logout();
    navigate("/login")
  }

  return (
    <section id="profile-section" className="bcf-container-1200px">
      <div className='bcf-profile-modal'>
        <h2>Perfil:</h2>
        <h3>Gerencie seu perfil, acesse as opções abaixo:</h3>

        <Link to={"/financial-reports/professional-total-profit-in-live"}>
          <h4>Ver meu lucro no dia</h4>
        </Link>


        <button onClick={handleClickLogout}>Sair da conta</button>
      </div>
    </section>
  );
}