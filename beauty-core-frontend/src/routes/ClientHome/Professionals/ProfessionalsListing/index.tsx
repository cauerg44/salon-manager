import './styles.css';
import { useEffect, useState } from 'react';
import * as professionalService from '../../../../services/professional-service.ts';
import SearchBar from '../../../../components/SearchBar/index.tsx';
import ButtonTertiary from '../../../../components/ButtonTertiary/index.tsx';
import type { ProfessionalDTO } from '../../../../models/professional.ts';
import DialogModalInfo from '../../../../components/DialogInfo/index.tsx';
import DialogConfirmation from '../../../../components/DialogConfirmation/index.tsx';

type QueryParams = {
  page: number;
  name: string;
}

export default function ProfessionalsListing() {

  const [dialogInfoData, setDialogInfoData] = useState({
    message: 'Operação com sucesso!',
    visible: false
  });

  const [dialogConfirmationData, setDialogConfirmationData] = useState({
    action: '',
    message: 'Tem certeza?',
    id: 0,
    visible: false
  });

  const [isLastPage, setIsLastPage] = useState<boolean>(false);

  const [professionals, setProfessionals] = useState<ProfessionalDTO[]>([]);

  const [countProfessionals, setCountProfessionals] = useState<number>();

  const [queryParams, setQueryParams] = useState<QueryParams>({
    page: 0,
    name: ""
  });

  useEffect(() => {
    professionalService.findAllProfessionals(queryParams.page, queryParams.name)
      .then(response => {
        const nextPage = response.data.content;
        setProfessionals(professionals.concat(nextPage));
        setIsLastPage(response.data.last);
        setCountProfessionals(response.data.numberOfElements);
      });
  }, [queryParams]);

  function handleSearch(searchText: string) {
    setProfessionals([]);
    setQueryParams({ ...queryParams, page: 0, name: searchText })
  }

  function handleNextPageClick() {
    setQueryParams({ ...queryParams, page: queryParams.page + 1 });
  }

  function handleDialogInfoClose() {
    setDialogInfoData({ ...dialogInfoData, visible: false });
  }

  function handleDeactivateClick(professionalId: number) {
    setDialogConfirmationData({ ...dialogConfirmationData, action: 'deactivate', message: 'Deseja desativar o profissional?', id: professionalId, visible: true });
  }

  function handleActivateClick(professionalId: number) {
    setDialogConfirmationData({ ...dialogConfirmationData, action: 'activate', message: 'Deseja ativar o profissional?', id: professionalId, visible: true });
  }

  function handleConfirmationAnswer(answer: boolean) {
    if (answer) {

      if (dialogConfirmationData.action === 'activate') {
        professionalService.activateProfessional(dialogConfirmationData.id)
          .then(() => {
            setProfessionals([]);
            setQueryParams({ ...queryParams, page: 0 });
          })
          .catch(error => {
            if (error.response.status === 403) {
              setDialogInfoData({ ...dialogInfoData, message: 'Apenas o administrador realizar esta ação!', visible: true });
              return;
            }
            setDialogInfoData({ ...dialogInfoData, message: error.response.data.error, visible: true });
          })
      }

      if (dialogConfirmationData.action === 'deactivate') {
        professionalService.deactivateProfessional(dialogConfirmationData.id)
          .then(() => {
            setProfessionals([]);
            setQueryParams({ ...queryParams, page: 0 });
            setDialogInfoData({ ...dialogInfoData, message: 'Profissional desativado com sucesso!', visible: true });
          })
          .catch(error => {
            console.log("deu erro", error);
            if (error.response.status === 403) {
              setDialogInfoData({ ...dialogInfoData, message: 'Apenas o administrador realizar esta ação!', visible: true });
              return;
            }
            setDialogInfoData({ ...dialogInfoData, message: error.response.data.error, visible: true });
          })
      }
    }

    setDialogConfirmationData({ ...dialogConfirmationData, visible: false });
  }

  return (
    <>
      <section id='professionals-listing-section' className='bcf-container-1200px'>

        <h2 className='bcf-search-bar-message'>Barra de pesquisa:</h2>

        <SearchBar placeholderText='Digite o nome do profissional' onSearch={handleSearch} />

        <h3 className='bcf-professionals-count-info'>{countProfessionals} profissionais encontrados:</h3>

        <div className='bcf-professionals-cards-modal'>

          {
            professionals.map(
              professional =>
                <div key={professional.id} className='bfc-professional-card-modal-container'>

                  <div className='bfc-professional-card-modal-infos'>
                    <h3>{professional.name}</h3>


                    {
                      professional.isWorking
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

                  <div className='bfc-professional-card-modal-actions'>
                    <div onClick={() => handleActivateClick(professional.id)} className='bfc-professional-card-action-option-activate'>
                      Ativar
                    </div>
                    <div onClick={() => handleDeactivateClick(professional.id)} className='bfc-professional-card-action-option-deactivate'>
                      Desativar
                    </div>
                  </div>

                </div>
            )
          }

        </div>

        {
          !isLastPage &&
          <div onClick={handleNextPageClick}>
            <ButtonTertiary text='Carregar mais' />
          </div>
        }

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
          message={dialogConfirmationData.message}
          id={dialogConfirmationData.id}
          onDialogAnswer={handleConfirmationAnswer}
        />
      }

    </>
  );
}