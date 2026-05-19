import './styles.css'
import editIcon from '../../assets/bcf-edit-icon.svg';
import trashIcon from '../../assets/bcf-trash-icon.svg';
import type { ClientDTO } from '../../models/client';

type Props = {
  client: ClientDTO;
}

export default function ClientCard({ client }: Props) {
  return (
    <div className='bcf-card-client-container'>
      <div className='bcf-card-client'>
        <h2>{client.name}</h2>
        <h3>Nascimento: {client.birthDate}</h3>
        <h3>Telefone: {client.phone}</h3>
        <h3>Crédito: R$ {client.credit.toFixed(2)}</h3>
      </div>
      <div className='bcf-card-client-actions'>
        <div className='bcf-card-client-actions-item'>
          <img src={editIcon} alt="Editar" />
          <h4>Editar</h4>
        </div>
        <div className='bcf-card-client-actions-item'>
          <img src={trashIcon} alt="Excluir" />
          <h4>Excluir</h4>
        </div>
      </div>
    </div>
  );
}