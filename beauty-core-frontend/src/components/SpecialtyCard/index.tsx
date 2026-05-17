import './styles.css'
import editIcon from '../../assets/bcf-edit-icon.svg';
import trashIcon from '../../assets/bcf-trash-icon.svg';


export default function SpecialtyCard() {
  return (
    <div className='bcf-specialty-card'>
      <h3>Especialidade</h3>
      <div className='bcf-specialty-card-icons'>
        <img src={editIcon} alt="Edit icon" />
        <img src={trashIcon} alt="Trash icon" />
      </div>
    </div>
  );
}