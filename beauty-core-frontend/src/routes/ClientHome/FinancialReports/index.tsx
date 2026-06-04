import { Link, Outlet } from 'react-router-dom';
import './styles.css';
import ReturnGeneralPageButton from '../../../components/ReturnGeneralPageButtun';

export default function FinancialReports() {

  return (
    <>
      <section id="financial-reports-section" className="bcf-container-1200px">
        <div className='bcf-financial-reports-modal'>

          <ReturnGeneralPageButton />

          <h2>Relatório financeiro:</h2>
          <h3>Vizualize o total apurado no dia:</h3>

          <Link to={"/financial-reports/total-profit-in-live"}>
            <h4>Ver total apurado hoje</h4>
          </Link>

          <Link to={"/financial-reports/professional-total-profit-in-live"}>
            <h4>Ver meu total apurado hoje</h4>
          </Link>

          <Link to={"/financial-reports/professional-total-profit-filtered"}>
            <h4>Filtrar total apurado por datas</h4>
          </Link>
        </div>
      </section>
      <Outlet />
    </>
  );
}