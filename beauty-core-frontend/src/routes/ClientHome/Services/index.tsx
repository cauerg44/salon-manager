import { Link } from 'react-router-dom';
import ButtonSecondary from '../../../components/ButtonSecondary';
import './styles.css';

export default function Services() {

  return (
    <section id="services-section" className="bcf-container-1200px">
      <div className='bcf-services-modal'>
        <h2>Serviços do salão:</h2>
        <h3>Gerencie os serviços dispóniveis no salão:</h3>

        <div className='bcf-specialization-modal-buttons'>
          <Link to={"/services/listing"}>
            <ButtonSecondary text='Listar' />
          </Link>
          <ButtonSecondary text='Cadastrar' />
        </div>

      </div>
    </section>
  );
}