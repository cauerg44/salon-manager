import './styles.css';
import { useEffect, useState } from 'react';
import type { ProfessionalTotalProfitInLive } from '../../../../models/financial-report';
import * as financialReportsService from '../../../../services/financial-reports-service.ts';
import * as professionalService from '../../../../services/professional-service.ts';
import type { ProfessionalLoggedDTO } from '../../../../models/professional-logged.ts';

export default function ProfessionalTotalProfit() {

  const [professionalLogged, setProfessionalLogged] = useState<ProfessionalLoggedDTO>();

  const [professionalTotalProfitInLive, setProfessionalTotalProfitInLive] = useState<ProfessionalTotalProfitInLive>();

  useEffect(() => {
    professionalService.findProfessionalLogged()
      .then(response => {
        setProfessionalLogged(response.data);
      });
  }, []);

  useEffect(() => {

    // just for avoiding undefinied value
    if (!professionalLogged?.id) return;

    financialReportsService
      .getProfessionalTotalProfitInLive(professionalLogged.id)
      .then(response => {
        console.log(response.data)
        setProfessionalTotalProfitInLive(response.data);
      });
  }, [professionalLogged]);

  return (
    <section id="professional-total-profit-in-live-section" className="bcf-container-1200px">

      <div className='bcf-professional-total-profit-in-live-modal-container'>
        <h2>Meu total ao vivo:</h2>

        <h3>Pix: <span>R$ {professionalTotalProfitInLive?.pix.toFixed(2)}</span></h3>
        <h3>Dinheiro: <span>R$ {professionalTotalProfitInLive?.cash.toFixed(2)}</span></h3>
        <h3>Débito: <span>R$ {professionalTotalProfitInLive?.debit.toFixed(2)}</span></h3>
        <h3>Crédito: <span>R$ {professionalTotalProfitInLive?.credit.toFixed(2)}</span></h3>
        <h3>Meu total de lucro: <span>R$ {professionalTotalProfitInLive?.totalProfit.toFixed(2)}</span></h3>
      </div>

    </section>
  );
}