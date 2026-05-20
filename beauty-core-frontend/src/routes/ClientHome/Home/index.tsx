import { Link } from 'react-router-dom';
import ButtonPrimary from '../../../components/ButtonPrimary/index.tsx';
import './styles.css';
import { useContext, useEffect, useState } from 'react';
import type { ProfessionalLoggedDTO } from '../../../models/professional-logged.ts';
import * as professionalService from '../../../services/professional-service.ts';
import { ContextProfessionalLogged } from '../../../utils/context-professional-logged.ts';

export default function Home() {

  const [professionalLogged, setProfessionalLogged] = useState<ProfessionalLoggedDTO>();

  const { setContextProfessionalLogged } = useContext(ContextProfessionalLogged);

  useEffect(() => {
    professionalService.findProfessionalLogged()
      .then(response => {
        setProfessionalLogged(response.data);
        setContextProfessionalLogged(response.data.name);
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