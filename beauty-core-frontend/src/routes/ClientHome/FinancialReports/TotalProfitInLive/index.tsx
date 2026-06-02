import './styles.css';
import { useEffect, useState } from 'react';
import type { TotalProfitInLiveDTO } from '../../../../models/financial-report';
import * as financialReportsServices from '../../../../services/financial-reports-service.ts';

export default function TotalProfitInLive() {

  const [totalProfitInLive, setTotalProfitInLive] = useState<TotalProfitInLiveDTO>();

  useEffect(() => {
    financialReportsServices.getTotalProfitInLive()
      .then(response => {
        setTotalProfitInLive(response.data);
        console.log(response.data);
      })
  }, []);

  return (
    <section id="total-profit-in-live-section" className="bcf-container-1200px">

      <div className='bcf-total-profit-in-live-modal-container'>
        <h2>Total ao vivo:</h2>

        <h3>Pix: <span>R$ {totalProfitInLive?.pix.toFixed(2)}</span></h3>
        <h3>Dinheiro: <span>R$ {totalProfitInLive?.cash.toFixed(2)}</span></h3>
        <h3>Débito: <span>R$ {totalProfitInLive?.debit.toFixed(2)}</span></h3>
        <h3>Crédito: <span>R$ {totalProfitInLive?.credit.toFixed(2)}</span></h3>
        <h3>Total apurado: <span>R$ {totalProfitInLive?.totalProfitInLive.toFixed(2)}</span></h3>
      </div>

    </section>
  );
}