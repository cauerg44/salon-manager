
import { NavLink, Outlet } from 'react-router-dom';
import ButtonSecondary from '../../../components/ButtonSecondary';
import './styles.css';

export default function Specializations() {
  return (
    <>
      <section id="specializations-section" className="bcf-container-1200px">
        <div className='bcf-specialization-modal'>
          <h2>Especialidades:</h2>
          <NavLink to="/specializations/listing" className={({ isActive }) => isActive ? "menu-item menu-item-active" : "menu-item"}>
            <h3>Listar especialidades:</h3>
          </NavLink>
          <ButtonSecondary text='Cadastrar' />
        </div>
      </section>
      <Outlet />
    </>
  );
}