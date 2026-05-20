import './styles.css';
import ClientCard from '../../../../components/ClientCard';
import { useEffect, useState } from 'react';
import type { ClientDTO } from '../../../../models/client';
import * as clientService from '../../../../services/client-service.ts';
import SearchBar from '../../../../components/SearchBar/index.tsx';

export default function ClientsListing() {

  const [clients, setClients] = useState<ClientDTO[]>([]);

  const [clientName, setClientName] = useState<string>("");

  useEffect(() => {
    clientService.findAllClientsPaged(0, clientName, 12)
      .then(response => {
        setClients(response.data.content);
      })
  }, [clientName]);

  function handleSearch(text: string) {
    setClientName(text);
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
    </section>
  );
}