import './styles.css';
import SpecialtyCard from '../../../../components/SpecialtyCard';
import { useEffect, useState } from 'react';
import type { SpecialtyDTO } from '../../../../models/specialty';
import * as specializationService from '../../../../services/specialization-service.ts';

export default function SpecializationsListing() {

  const [specializations, setSpecializations] = useState<SpecialtyDTO[]>([]);

  useEffect(() => {
    specializationService.findAll()
      .then(response => {
        setSpecializations(response.data);
        console.log(response);
      })
  }, []);

  return (
    <section id="specializations-listing-section" className="bcf-container-1200px">
      <div className='bcf-specialty-cards-modal'>
        {
          specializations.map(
            specialty => <SpecialtyCard key={specialty.id} specialty={specialty} />
          )
        }
      </div>
    </section>
  );
}