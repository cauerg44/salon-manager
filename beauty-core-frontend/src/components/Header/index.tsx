
import { useContext } from 'react';
import './styles.css';
import { Link } from 'react-router-dom';
import { ContextProfessionalLogged } from '../../utils/context-professional-logged.ts';
import homeIcon from '../../assets/bcf-home-icon.svg';

export default function Header() {

  const { contextProfessionalLogged } = useContext(ContextProfessionalLogged);

  return (
    <header>
      <nav className='bcf-container-1200px'>
        <Link to={"/home"}>
          <img src={homeIcon} alt="Tela inicial" />
        </Link>

        <Link to={"/"}>
          <h1>Sistema PDV: Salão de Beleza</h1>
        </Link>

        {
          contextProfessionalLogged
            ? <h3>Olá, <b>{contextProfessionalLogged}!</b></h3>
            :
            <Link to={"/login"}>
              <h3>Login</h3>
            </Link>
        }
      </nav>
    </header>
  );
}