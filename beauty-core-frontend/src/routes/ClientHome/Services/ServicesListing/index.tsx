import './styles.css';
import { useEffect, useState } from 'react';
import type { ServiceDTO } from '../../../../models/service-dto';
import * as jobItemService from '../../../../services/job-item-service.ts';
import editIcon from '../../../../assets/bcf-edit-icon.svg';
import trashIcon from '../../../../assets/bcf-trash-icon.svg';
import DialogConfirmation from '../../../../components/DialogConfirmation/index.tsx';
import DialogModalInfo from '../../../../components/DialogInfo/index.tsx';

export default function ServicesListing() {

  const [dialogInfoData, setDialogInfoData] = useState({
    visible: false,
    message: "Operação com sucesso!"
  })

  const [dialogConfirmationData, setDialogConfirmationData] = useState({
    visible: false,
    id: 0,
    message: "Tem certeza?"
  })

  const [services, setServices] = useState<ServiceDTO[]>([]);

  useEffect(() => {
    jobItemService.findAllServices()
      .then(response => {
        setServices(response.data);
      })
  }, []);

  function handleDeleteClick(serviceId: number) {
    setDialogConfirmationData({ ...dialogConfirmationData, id: serviceId, visible: true });
  }

  function handleDialogInfoClose() {
    setDialogInfoData({ ...dialogInfoData, visible: false });
  }

  function handleDialogConfirmationAnswer(answer: boolean, serviceId: number) {
    if (answer) {
      jobItemService.deleteServiceById(serviceId)
        .then(() => {
          setServices(services.filter(item => item.id !== serviceId));
          setDialogInfoData({ ...dialogInfoData, message: 'Serviço removido com sucesso!', visible: true });
        })
        .catch(error => {
          if (error.response.status === 403) {
            setDialogInfoData({ ...dialogInfoData, message: 'Apenas o administrador tem acesso para remover este serviço do salão!', visible: true });
            return;
          }

          setDialogInfoData({ ...dialogInfoData, message: error.response.data.error, visible: true });
        });
    }

    setDialogConfirmationData({ ...dialogConfirmationData, visible: false });
  }

  return (
    <>
      <section id="services-listing-section" className="bcf-container-1200px">

        <h2 className='bcf-listing-services-title'>Listagem dos serviços disponíveis: </h2>

        <div className='bcf-services-cards-modal'>
          {
            services.map(
              serviceItem =>
                <div key={serviceItem.id} className='bcf-services-card-modal-container'>
                  <div className='bcf-services-card-container'>
                    <div>
                      <h3>{serviceItem.name}</h3>
                      <h4>R$ {serviceItem.basePrice.toFixed(2)}</h4>
                    </div>
                  </div>


                  <div className='bcf-services-card-actions'>

                    <div className='bcf-services-card-action-item'>
                      <img src={editIcon} alt="Editar serviço" />
                      <h5>Editar</h5>
                    </div>

                    <div onClick={() => handleDeleteClick(serviceItem.id)} className='bcf-services-card-action-item'>
                      <img src={trashIcon} alt="Excluir serviço" />
                      <h5>Excluir</h5>
                    </div>

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