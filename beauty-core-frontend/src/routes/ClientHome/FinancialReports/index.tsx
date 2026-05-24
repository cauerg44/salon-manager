import { Link, Outlet } from 'react-router-dom';
import './styles.css';

export default function FinancialReports() {

  return (
    <>
      <section id="financial-reports-section" className="bcf-container-1200px">
        <div className='bcf-financial-reports-modal'>
          <h2>Relatório financeiro:</h2>
          <h3>Vizualize o lucro total ao vivo no dia, e por cada profissional:</h3>

          <Link to={"/financial-reports/total-profit-in-live"}>
            <h4>Ver lucro total ao vivo no dia de hoje</h4>
          </Link>

          <Link to={"/financial-reports/professional-total-profit-in-live"}>
            <h4>Ver meu lucro de hoje</h4>
          </Link>
        </div>
      </section>
      <Outlet />
    </>
  );
}