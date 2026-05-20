import './styles.css';
import ClientCard from '../../../../components/ClientCard';
import { useEffect, useState } from 'react';
import type { ClientDTO } from '../../../../models/client';
import * as clientService from '../../../../services/client-service.ts';
import SearchBar from '../../../../components/SearchBar/index.tsx';
import ButtonTertiary from '../../../../components/ButtonTertiary/index.tsx';

type QueryParams = {
  page: number;
  name: string;
}

export default function ClientsListing() {

  const [isLastPage, setIsLastPage] = useState<boolean>(false);

  const [clients, setClients] = useState<ClientDTO[]>([]);

  const [queryParams, setQueryParams] = useState<QueryParams>({
    page: 0,
    name: ""
  });

  useEffect(() => {
    clientService.findAllClientsPaged(queryParams.page, queryParams.name)
      .then(response => {
        const nextPage = response.data.content;
        setClients(clients.concat(nextPage));
        setIsLastPage(response.data.last);
      });
  }, [queryParams]);

  function handleSearch(searchText: string) {
    setClients([]);
    setQueryParams({ ...queryParams, page: 0, name: searchText })
  }

  function handleNextPageClick() {
    setQueryParams({ ...queryParams, page: queryParams.page + 1 });
  }

  return (
    <section id='clients-listing-section' className='bcf-container-1200px'>

      <h2 className='bcf-search-bar-message'>Barra de pesquisa:</h2>
      <SearchBar onSearch={handleSearch} />

      <div className='bcf-clients-cards-modal'>
        {
          clients.map(
            client => <ClientCard key={client.id} client={client} />
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