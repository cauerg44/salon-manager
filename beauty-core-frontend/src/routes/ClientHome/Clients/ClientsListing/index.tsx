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