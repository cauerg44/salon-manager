
import { useState } from 'react';
import './styles.css';
import { Link } from 'react-router-dom';

export default function Header() {

  const [professionalName, setProfessionalName] = useState<string>("");

  return (
    <header>
      <nav className='bcf-container-1200px'>
        <span>✄</span>

        <Link to={"/"}>
          <h1>Sistema PDV: Salão de Beleza</h1>
        </Link>

        {
          professionalName
            ? <h3>Olá, <b>{professionalName}!</b></h3>
            : <h3>Login</h3>
        }
      </nav>
    </header>
  );
}