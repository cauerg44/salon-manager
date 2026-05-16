
import { Link } from 'react-router-dom';
import './styles.css';

export default function General() {
  return (
    <section id="general-section" className="bcf-container-1200px">
      <div className='bcf-general-modal'>
        <h2>Ações rápidas:</h2>
        <div className='bcf-actions-items-general'>
          <div className='bcf-action-item'>◷ Atendimentos</div>
          <div className='bcf-action-item'>✂︎ Profissionais</div>
          <div className='bcf-action-item'>👤︎ Clientes</div>
          <div className='bcf-action-item'>☰ Serviços</div>
          <Link to={"/specializations"}>
            <div className='bcf-action-item'>✪ Especialidades</div>
          </Link>
          <div className='bcf-action-item'>💳︎ Pagamentos</div>
        </div>
      </div>
    </section>
  );
}