import { useEffect, useState } from 'react';
import './styles.css';
import type { ServiceDTO } from '../../../../models/service-dto';
import * as jobItemService from '../../../../services/job-item-service.ts';
import ServiceCard from '../../../../components/ServiceCard/index.tsx';

export default function ServicesListing() {

  const [services, setServices] = useState<ServiceDTO[]>([]);

  useEffect(() => {
    jobItemService.findAllServices()
      .then(response => {
        setServices(response.data);
      })
  }, []);

  return (
    <section id="services-listing-section" className="bcf-container-1200px">

      <h2 className='bcf-listing-services-title'>Listagem dos serviços disponíveis: </h2>

      <div className='bcf-services-cards-modal'>
        {
          services.map(
            serviceItem => <ServiceCard key={serviceItem.id} serviceDTO={serviceItem} />
          )
        }
      </div>

    </section>
  );
}