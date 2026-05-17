
import { Link, Outlet } from 'react-router-dom';
import ButtonSecondary from '../../../components/ButtonSecondary';
import './styles.css';

export default function Specializations() {
  return (
    <>
      <section id="specializations-section" className="bcf-container-1200px">
        <div className='bcf-specialization-modal'>
          <h2>Especialidades:</h2>
          <h3>Gerencie as especialidades dos profissionais no salão de beleza: </h3>
          <div className='bcf-specialization-modal-buttons'>
            <Link to={"/specializations/listing"}>
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