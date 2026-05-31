import { Link, Outlet } from 'react-router-dom';
import ButtonSecondary from '../../../components/ButtonSecondary';
import './styles.css';
import ReturnGeneralPageButton from '../../../components/ReturnGeneralPageButtun';

export default function Services() {

  return (
    <>
      <section id="services-section" className="bcf-container-1200px">
        <div className='bcf-services-modal'>
          <ReturnGeneralPageButton />
          <h2>Serviços do salão:</h2>
          <h3>Gerencie os serviços dispóniveis no salão:</h3>

          <div className='bcf-specialization-modal-buttons'>

            <Link to={"/services/listing"}>
              <ButtonSecondary text='Listar' />
            </Link>

            <Link to={"/services/create"}>
              <ButtonSecondary text='Cadastrar' />
            </Link>

          </div>

        </div>
      </section>
      <Outlet />
    </>
  );
}