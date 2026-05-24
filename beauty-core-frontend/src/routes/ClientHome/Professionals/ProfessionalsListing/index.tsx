import './styles.css';
import { useEffect, useState } from 'react';
import * as professionalService from '../../../../services/professional-service.ts';
import SearchBar from '../../../../components/SearchBar/index.tsx';
import ButtonTertiary from '../../../../components/ButtonTertiary/index.tsx';
import type { ProfessionalDTO } from '../../../../models/professional.ts';
import ProfessionalCard from '../../../../components/ProfessionalCard/index.tsx';

type QueryParams = {
  page: number;
  name: string;
}

export default function ProfessionalsListing() {

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

  return (
    <section id='professionals-listing-section' className='bcf-container-1200px'>

      <h2 className='bcf-search-bar-message'>Barra de pesquisa:</h2>

      <SearchBar placeholderText='Digite o nome do profissional' onSearch={handleSearch} />

      <h3 className='bcf-professionals-count-info'>{countProfessionals} profissionais encontrados:</h3>

      <div className='bcf-professionals-cards-modal'>

        {
          professionals.map(
            professional => <ProfessionalCard key={professional.id} professional={professional} />
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
  );
}