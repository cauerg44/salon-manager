
import './styles.css';
import { Link } from 'react-router-dom';

export default function Header() {

  return (
    <header>
      <nav className='bcf-container-1200px'>
        <span>✄</span>

        <Link to={"/"}>
          <h1>Sistema PDV: Salão de Beleza</h1>
        </Link>

        <h3>Olá, <b>Júnior!</b></h3>
      </nav>
    </header>
  );
}