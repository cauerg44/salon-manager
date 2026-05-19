
import { Link, Outlet } from 'react-router-dom';
import ButtonSecondary from '../../../components/ButtonSecondary';
import './styles.css';

export default function Clients() {
  return (
    <>
      <section id="clients-section" className="bcf-container-1200px">
        <div className='bcf-clients-modal'>
          <h2>Clientes:</h2>
          <h3>Gerencie os clientes do salão: </h3>
          <div className='bcf-clients-modal-buttons'>
            <Link to={"/clients/listing"}>
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