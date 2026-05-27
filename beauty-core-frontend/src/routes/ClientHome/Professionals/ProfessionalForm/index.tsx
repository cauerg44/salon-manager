import ButtonPrimary from '../../../../components/ButtonPrimary';
import './styles.css';

export default function ProfessionalForm() {

  return (
    <>
      <section id='professional-form-section' className='bcf-container-1200px'>

        <h2 className='bcf-form-title-section'>Novo profissional:</h2>

        <div className='bcf-professional-form-modal-container'>
          <h3>Dados do profissional: </h3>

          <form className='bcf-professional-form'>
            <input type="text" placeholder='Nome' />
            <input type="text" placeholder='Email' />
            <input type="password" placeholder='Senha' />
            <select className='bcf-select' required>
              <option value="" disabled selected>Especialidades</option>
              <option value="1">Cabeleleiro(a)</option>
              <option value="2">Trancista</option>
              <option value="3">Esteticista</option>
            </select>
            <ButtonPrimary text='Registrar' />
          </form>

        </div>

      </section>
    </>
  );
}