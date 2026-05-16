import ButtonPrimary from '../../../components/ButtonPrimary';
import './styles.css';

export default function Home() {
  return (
    <section id="home-section" className="bcf-container-1200px">
      <div className='bcf-home-modal'>
        <h2>Seja bem-vindo(a) novamente!</h2>
        <h3>Começe a usar o sistema para ter um melhor gerenciamento financeiro e fluxo do salão</h3>
        <ButtonPrimary text="Começar" />
      </div>
    </section>
  );
}