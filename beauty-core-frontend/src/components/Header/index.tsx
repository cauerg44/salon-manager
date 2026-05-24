import './styles.css';
import { Link } from 'react-router-dom';
import homeIcon from '../../assets/bcf-home-icon.svg';
import profileConfigIcon from '../../assets/bcf-profile-config-icon.svg';

export default function Header() {

  return (
    <header>
      <nav className='bcf-container-1200px'>
        <Link to={"/home"}>
          <img src={homeIcon} alt="Tela inicial" />
        </Link>

        <Link to={"/"}>
          <h1>Sistema de Gestão: Salão de Beleza</h1>
        </Link>

        <Link to={"/profile"}>
          <img src={profileConfigIcon} alt="Tela inicial" />
        </Link>
      </nav>
    </header>
  );
}