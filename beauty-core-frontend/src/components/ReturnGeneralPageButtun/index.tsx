import { Link } from 'react-router-dom';
import './styles.css';

export default function ReturnGeneralPageButton() {
  return (
    <Link to={"/general"}>
      <div className='bcf-option-back-general'>
        Voltar
      </div>
    </Link>
  );
}