import type { ProfessionalDTO } from '../../models/professional';
import './styles.css';

type Props = {
  professional: ProfessionalDTO;
}

export default function ProfessionalCard({ professional }: Props) {
  return (
    <div className='bfc-professional-card-modal-container'>

      <div className='bfc-professional-card-modal-infos'>
        <h3>{professional.name}</h3>
        {
          professional.isActive
            ?
            <div className='bfc-professional-card-modal-info-status-working'>
              Trabalhando
            </div>
            :
            <div className='bfc-professional-card-modal-info-status-available'>
              Disponível
            </div>
        }

        {
          professional.isActive
            ?
            <div className='bfc-professional-card-modal-info-status-active'>
              Ativo
            </div>
            :
            <div className='bfc-professional-card-modal-info-status-desactivated'>
              Inativo
            </div>
        }

      </div>

      <div className='bfc-professional-card-modal-specializations'>

        <h4>Especialidades do profissional</h4>

        {
          professional.specializations.map(
            specialty =>
              <div key={specialty.id} className='bfc-professional-card-modal-specialization-item'>
                {specialty.name}
              </div>
          )
        }

      </div>

    </div>
  );
}

{/* <div className='bfc-professional-card-modal-actions'>
  <div className='bfc-professional-card-action-option-activate'>
    Ativar
  </div>
  <div className='bfc-professional-card-action-option-deactivate'>
    Desativar
  </div>
</div> */}