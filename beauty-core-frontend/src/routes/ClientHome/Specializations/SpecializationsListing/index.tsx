import './styles.css';
import { useEffect, useState } from 'react';
import type { SpecialtyDTO } from '../../../../models/specialty';
import * as specializationService from '../../../../services/specialization-service.ts';
import editIcon from '../../../../assets/bcf-edit-icon.svg';
import trashIcon from '../../../../assets/bcf-trash-icon.svg';
import DialogModalInfo from '../../../../components/DialogInfo/index.tsx';

export default function SpecializationsListing() {

  const [dialogInfoData, setDialogInfoData] = useState({
    visible: false,
    message: "Operação com sucesso!"
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

  function handleDeleteClick() {
    setDialogInfoData({ ...dialogInfoData, visible: true });
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
                    <img src={editIcon} alt="Edit icon" />
                    <img onClick={handleDeleteClick} src={trashIcon} alt="Trash icon" />
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
    </>
  );
}