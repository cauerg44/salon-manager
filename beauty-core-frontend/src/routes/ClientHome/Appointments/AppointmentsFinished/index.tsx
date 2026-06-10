import './styles.css';
import { useEffect, useState } from 'react';
import type { AppointmentDTO } from '../../../../models/appointment';
import * as appointmentService from '../../../../services/appointment-service.ts'
import AppointmentFinishedCard from '../../../../components/AppointmentFinishedCard/index.tsx';

export default function AppointmentsFinished() {

  const [appointments, setAppointments] = useState<AppointmentDTO[]>([]);

  const [count, setCount] = useState<number>();

  useEffect(() => {
    appointmentService.findAllAppointmentsByStatus("FINISHED")
      .then(response => {
        setCount(response.data.content.length);
        setAppointments(response.data.content);
      })
  }, []);

  return (
    <section id="appointments-in-finished-section" className="bcf-container-1200px">
      <h2 className='bcf-appointment-status-title-section'>{count} atendimento(s) finalizado(s):</h2>

      <div className='bcf-appointments-cards-container'>
        {
          appointments.map(
            appointment => <AppointmentFinishedCard key={appointment.id} appointmentDTO={appointment} />
          )
        }
      </div>
    </section>
  );
}