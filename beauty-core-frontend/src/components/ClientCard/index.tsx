import './styles.css'
import editIcon from '../../assets/bcf-edit-icon.svg';
import type { ClientDTO } from '../../models/client';
import { useNavigate } from 'react-router-dom';

type Props = {
  client: ClientDTO;
}

export default function ClientCard({ client }: Props) {

  const navigate = useNavigate();

  function handleEditClick() {
    navigate(`/clients/edit/${client.id}`);
  }

  return (
    <div className='bcf-card-client-container'>
      <div className='bcf-card-client'>
        <h2>{client.name}</h2>
        <h3>Nascimento: {client.birthDate}</h3>
        <h3>Telefone: {client.phone}</h3>
        <h3>Crédito: R$ {client.credit.toFixed(2)}</h3>
      </div>

      <div className='bcf-card-client-actions'>
        <div onClick={handleEditClick} className='bcf-card-client-actions-item'>
          <img src={editIcon} alt="Editar" />
          <h4>Editar</h4>
        </div>
      </div>

    </div>
  );
}