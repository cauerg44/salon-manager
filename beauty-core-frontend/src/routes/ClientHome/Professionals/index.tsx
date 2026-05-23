import { Link, Outlet } from 'react-router-dom';
import ButtonSecondary from '../../../components/ButtonSecondary';
import './styles.css';

export default function Professionals() {
  return (
    <>
      <section id="professionals-section" className="bcf-container-1200px">
        <div className='bcf-professionals-modal'>
          <h2>Profissionais:</h2>
          <h3>Gerencie os profissionais do salão: </h3>
          <div className='bcf-professionals-modal-buttons'>
            <Link to={"/professionals/listing"}>
              <ButtonSecondary text='Listar' />
            </Link>
            <ButtonSecondary text='Cadastrar' />
          </div>
        </div>
      </section>
      <Outlet />
    </>
  );
}