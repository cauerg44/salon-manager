
import { Link } from 'react-router-dom';
import './styles.css';

export default function General() {
  return (
    <section id="general-section" className="bcf-container-1200px">
      <div className='bcf-general-modal'>
        <h2>Ações rápidas:</h2>
        <div className='bcf-actions-items-general'>

          <Link to={"/appointments"}>
            <div className='bcf-action-item'>Atendimentos</div>
          </Link>

          <Link to={"/professionals"}>
            <div className='bcf-action-item'>Professionais</div>
          </Link>

          <Link to={"/clients"}>
            <div className='bcf-action-item'>Clientes</div>
          </Link>

          <Link to={"/services"}>
            <div className='bcf-action-item'>Serviços</div>
          </Link>

          <Link to={"/specializations"}>
            <div className='bcf-action-item'>Especialidades</div>
          </Link>

          <Link to={"/financial-reports"}>
            <div className='bcf-action-item'>Financeiro</div>
          </Link>

        </div>
      </div>
    </section>
  );
}