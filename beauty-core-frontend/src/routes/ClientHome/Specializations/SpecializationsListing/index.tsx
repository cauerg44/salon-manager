import './styles.css';
import { useEffect, useState } from 'react';
import type { SpecialtyDTO } from '../../../../models/specialty';
import * as specializationService from '../../../../services/specialization-service.ts';
import editIcon from '../../../../assets/bcf-edit-icon.svg';
import trashIcon from '../../../../assets/bcf-trash-icon.svg';
import DialogModalInfo from '../../../../components/DialogInfo/index.tsx';
import DialogConfirmation from '../../../../components/DialogConfirmation/index.tsx';
import { useNavigate } from 'react-router-dom';

export default function SpecializationsListing() {

  const navigate = useNavigate();

  const [dialogInfoData, setDialogInfoData] = useState({
    visible: false,
    message: "Operação com sucesso!"
  })

  const [dialogConfirmationData, setDialogConfirmationData] = useState({
    visible: false,
    id: 0,
    message: "Tem certeza?"
  })

  const [specializations, setSpecializations] = useState<SpecialtyDTO[]>([]);

  useEffect(() => {
    specializationService.findAll()
      .then(response => {
        setSpecializations(response.data);
      })
  }, []);

  function handleDialogInfoClose() {
    setDialogInfoData({ ...dialogInfoData, visible: false });
  }

  function handleDeleteClick(specialtyId: number) {
    setDialogConfirmationData({ ...dialogConfirmationData, id: specialtyId, visible: true });
  }

  function handleEditClick(specialtyId: number) {
    navigate(`/specializations/edit/${specialtyId}`);
  }

  function handleDialogConfirmationAnswer(answer: boolean, specialtyId: number) {
    if (answer) {
      specializationService.deleteById(specialtyId)
        .then(() => {
          setDialogInfoData({
            ...dialogInfoData,
            message: 'Operação feita com sucesso!',
            visible: true
          })
          setSpecializations(specializations.filter(item => item.id !== specialtyId));
        })
        .catch(() => {
          setDialogInfoData({
            ...dialogInfoData,
            message: 'Apenas o administrador pode remover!',
            visible: true
          })
        });
    }

    setDialogConfirmationData({ ...dialogConfirmationData, visible: false });
  }

  return (
    <>
      <section id="specializations-listing-section" className="bcf-container-1200px">
        <div className='bcf-specialty-cards-modal'>
          {
            specializations.map(
              specialty =>
                <div key={specialty.id} className='bcf-specialty-card'>
                  <h3>{specialty.name}</h3>
                  <div className='bcf-specialty-card-icons'>
                    <img onClick={() => handleEditClick(specialty.id)} src={editIcon} alt="Edit icon" />
                    <img onClick={() => handleDeleteClick(specialty.id)} src={trashIcon} alt="Trash icon" />
                  </div>
                </div>
            )
          }
        </div>
      </section>

      {
        dialogInfoData.visible &&
        <DialogModalInfo
          message={dialogInfoData.message}
          onDialogClose={handleDialogInfoClose}
        />
      }

      {
        dialogConfirmationData.visible &&
        <DialogConfirmation
          id={dialogConfirmationData.id}
          message={dialogConfirmationData.message}
          onDialogAnswer={handleDialogConfirmationAnswer}
        />
      }
    </>
  );
}