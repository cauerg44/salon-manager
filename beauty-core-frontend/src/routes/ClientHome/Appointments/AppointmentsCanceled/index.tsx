import './styles.css';
import { useEffect, useState } from 'react';
import type { AppointmentDTO } from '../../../../models/appointment';
import * as appointmentService from '../../../../services/appointment-service.ts'
import AppointmentCanceledCard from '../../../../components/AppointmentCanceledCard/index.tsx';

export default function AppointmentsCanceled() {

  const [appointments, setAppointments] = useState<AppointmentDTO[]>([]);

  useEffect(() => {
    appointmentService.findAllAppointmentsByStatus("CANCELED")
      .then(response => {
        console.log(response.data);
        setAppointments(response.data.content);
      })
  }, []);

  return (
    <section id="appointments-canceled-section" className="bcf-container-1200px">
      <h2 className='bcf-appointment-status-title-section'>Atendimentos cancelados:</h2>

      <div className='bcf-appointments-cards-container'>
        {
          appointments.map(
            appointment => <AppointmentCanceledCard key={appointment.id} appointmentDTO={appointment} />
          )
        }
      </div>
    </section>
  );
}