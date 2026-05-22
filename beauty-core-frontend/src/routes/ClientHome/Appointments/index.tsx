import './styles.css';
import { Link, Outlet } from "react-router-dom";

export default function Appointments() {
  return (
    <>
      <section id="appointments-section" className="bcf-container-1200px">
        <div className='bcf-appointments-modal'>
          <h2>Atendimentos:</h2>
          <h3>Gerencie todos os atendimentos do salão:</h3>

          <Link to={"/appointments/in-waiting"}>
            <h4>Listar atendimentos em espera:</h4>
          </Link>

          <Link to={"/appointments/in-progress"}>
            <h4>Listar atendimentos em andamento:</h4>
          </Link>

          <Link to={"/appointments/finished"}>
            <h4>Listar atendimentos finalizados:</h4>
          </Link>

          <Link to={"/appointments/canceled"}>
            <h4>Listar atendimentos cancelados:</h4>
          </Link>

          <Link to={"/appointments/not-paid"}>
            <h4>Listar atendimentos não pagos:</h4>
          </Link>

        </div>
      </section>
      <Outlet />
    </>
  );
}