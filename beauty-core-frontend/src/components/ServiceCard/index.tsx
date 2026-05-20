import './styles.css';
import editIcon from '../../assets/bcf-edit-icon.svg';
import trashIcon from '../../assets/bcf-trash-icon.svg';
import type { ServiceDTO } from '../../models/service-dto';

type Props = {
  serviceDTO: ServiceDTO;
}

export default function ServiceCard({ serviceDTO }: Props) {
  return (
    <div className='bcf-services-card-modal-container'>
      <div className='bcf-services-card-container'>
        <div>
          <h3>{serviceDTO.name}</h3>
          <h4>R$ {serviceDTO.basePrice.toFixed(2)}</h4>
        </div>
      </div>
      <div className='bcf-services-card-actions'>
        <div className='bcf-services-card-action-item'>
          <img src={editIcon} alt="Editar serviço" />
          <h4>Editar</h4>
        </div>
        <div className='bcf-services-card-action-item'>
          <img src={trashIcon} alt="Excluir serviço" />
          <h4>Excluir</h4>
        </div>
      </div>
    </div>
  );
}