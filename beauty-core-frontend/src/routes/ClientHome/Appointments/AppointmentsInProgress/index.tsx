import './styles.css';
import { useEffect, useState } from 'react';
import type { AppointmentDTO } from '../../../../models/appointment';
import * as appointmentService from '../../../../services/appointment-service.ts'
import AppointmentsInProgressCard from '../../../../components/AppointmentInProgressCard/index.tsx';

export default function AppointmentsInProgress() {

  const [appointments, setAppointments] = useState<AppointmentDTO[]>([]);

  const [count, setCount] = useState<number>();

  useEffect(() => {
    appointmentService.findAllAppointmentsByStatus("IN_PROGRESS")
      .then(response => {
        setCount(response.data.content.length);
        setAppointments(response.data.content);
      })
  }, []);

  return (
    <section id="appointments-in-waiting-section" className="bcf-container-1200px">
      <h2 className='bcf-appointment-status-title-section'>{count} atendimento(s) em andamento:</h2>

      <div className='bcf-appointments-cards-container'>
        {
          appointments.map(
            appointment => <AppointmentsInProgressCard key={appointment.id} appointmentDTO={appointment} />
          )
        }
      </div>
    </section>
  );
}