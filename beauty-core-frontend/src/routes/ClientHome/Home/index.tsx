import { Link, useNavigate } from 'react-router-dom';
import ButtonPrimary from '../../../components/ButtonPrimary';
import './styles.css';
import { useEffect, useState } from 'react';
import type { ProfessionalLoggedDTO } from '../../../models/professional-logged';
import * as professionalService from '../../../services/professional-service.ts';

export default function Home() {

  const navigate = useNavigate();

  const [professionalLogged, setProfessionalLogged] = useState<ProfessionalLoggedDTO>();

  useEffect(() => {
    professionalService.findProfessionalLogged()
      .then(response => {
        setProfessionalLogged(response.data);
        console.log(response.data);
      })
  }, []);

  return (
    <section id="home-section" className="bcf-container-1200px">
      <div className='bcf-home-modal'>
        <h2>Seja bem-vindo(a) novamente, {professionalLogged?.name}!</h2>
        <h3>Começe a usar o sistema para ter um melhor gerenciamento financeiro e fluxo do salão</h3>

        <Link to={"/general"}>
          <ButtonPrimary text="Começar" />
        </Link>
      </div>
    </section>
  );
}