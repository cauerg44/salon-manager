import './styles.css'
import editIcon from '../../assets/bcf-edit-icon.svg';
import trashIcon from '../../assets/bcf-trash-icon.svg';
import type { SpecialtyDTO } from '../../models/specialty';

type Props = {
  specialty: SpecialtyDTO;
}

export default function SpecialtyCard({ specialty }: Props) {
  return (
    <div className='bcf-specialty-card'>
      <h3>{specialty.name}</h3>
      <div className='bcf-specialty-card-icons'>
        <img src={editIcon} alt="Edit icon" />
        <img src={trashIcon} alt="Trash icon" />
      </div>
    </div>
  );
}