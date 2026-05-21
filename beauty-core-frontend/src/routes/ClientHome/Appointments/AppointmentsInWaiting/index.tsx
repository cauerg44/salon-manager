import './styles.css';
import { useEffect, useState } from 'react';
import AppointmentsInWaitingCard from '../../../../components/AppointmentInWaitingCard';
import type { AppointmentDTO } from '../../../../models/appointment';
import * as appointmentService from '../../../../services/appointment-service.ts'

export default function AppointmentsInWaiting() {

  const [appointments, setAppointments] = useState<AppointmentDTO[]>([]);

  useEffect(() => {
    appointmentService.findAllAppointmentsByStatus("WAITING")
      .then(response => {
        console.log(response.data);
        setAppointments(response.data.content);
      })
  }, []);

  return (
    <section id="appointments-in-waiting-section" className="bcf-container-1200px">
      <h2 className='bcf-appointment-status-title-section'>Atendimentos em espera:</h2>

      <div className='bcf-appointments-cards-container'>
        {
          appointments.map(
            appointment => <AppointmentsInWaitingCard key={appointment.id} appointmentDTO={appointment} />
          )
        }
      </div>
    </section>
  );
}