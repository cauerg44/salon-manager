import './styles.css';
import ClientCard from '../../../../components/ClientCard';
import { useEffect, useState } from 'react';
import type { ClientDTO } from '../../../../models/client';
import * as clientService from '../../../../services/client-service.ts';

export default function ClientsListing() {

  const [clients, setClients] = useState<ClientDTO[]>([]);

  useEffect(() => {
    clientService.findAllClientsPaged(0, "ca", 12)
      .then(response => {
        setClients(response.data.content);
      })
  }, [clients])

  return (
    <section id='clients-listing-section' className='bcf-container-1200px'>
      <h2 className='bcf-search-bar-message'>Barra de pesquisa:</h2>
      <div className='bcf-search-bar'>
        <button className='btn-left'>🔎︎</button>
        <input
          placeholder='Digite o nome do cliente'
          type="text"
        />
        <button className='btn-right' type="reset">🗙</button>
      </div>
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