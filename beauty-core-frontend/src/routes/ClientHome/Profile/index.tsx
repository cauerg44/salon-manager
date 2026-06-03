import './styles.css';
import * as authService from '../../../services/auth-service.ts';
import * as professionalService from '../../../services/professional-service.ts';
import { Link, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import type { ProfessionalLoggedDTO } from '../../../models/professional-logged.ts';

export default function Profile() {

  const navigate = useNavigate();

  const [professionalLogged, setProfessionalLogged] = useState<ProfessionalLoggedDTO>();

  useEffect(() => {
    professionalService.findProfessionalLogged()
      .then(response => {
        setProfessionalLogged(response.data);
      })
  }, []);

  function handleClickLogout() {
    authService.logout();
    navigate("/login")
  }

  return (
    <section id="profile-section" className="bcf-container-1200px">
      <div className='bcf-profile-modal'>
        <h2>Perfil:</h2>
        <h3>Olá <b>{professionalLogged?.name}</b>! Acesse as opções abaixo para gerenciar seu perfil:</h3>

        <Link to={"/appointments"}>
          <h4>Vizualizar os atendimentos</h4>
        </Link>


        <Link to={"/financial-reports/total-profit-in-live"}>
          <h4>Vizualizar total apurado de hoje</h4>
        </Link>

        {
          professionalLogged?.roles.some(role => role.authority !== "ROLE_RECEPCIONIST") &&
          < Link to={"/financial-reports/professional-total-profit-in-live"}>
            <h4>Ver meu total apurado de hoje</h4>
          </Link>
        }


        <button onClick={handleClickLogout}>Sair da conta</button>
      </div>
    </section>
  );
}