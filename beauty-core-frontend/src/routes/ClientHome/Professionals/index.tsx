import { Link, Outlet } from 'react-router-dom';
import ButtonSecondary from '../../../components/ButtonSecondary';
import './styles.css';
import ReturnGeneralPageButton from '../../../components/ReturnGeneralPageButtun';

export default function Professionals() {
  return (
    <>
      <section id="professionals-section" className="bcf-container-1200px">
        <div className='bcf-professionals-modal'>
          <ReturnGeneralPageButton />
          <h2>Profissionais:</h2>
          <h3>Gerencie os profissionais do salão: </h3>
          <div className='bcf-professionals-modal-buttons'>
            <Link to={"/professionals/listing"}>
              <ButtonSecondary text='Listar' />
            </Link>

            <Link to={"/professionals/create"}>
              <ButtonSecondary text='Cadastrar' />
            </Link>
          </div>
        </div>
      </section>
      <Outlet />
    </>
  );
}