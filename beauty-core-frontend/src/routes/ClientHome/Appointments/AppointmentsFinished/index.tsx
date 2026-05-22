import './styles.css';
import { useEffect, useState } from 'react';
import type { AppointmentDTO } from '../../../../models/appointment';
import * as appointmentService from '../../../../services/appointment-service.ts'
import AppointmentFinishedCard from '../../../../components/AppointmentFinishedCard/index.tsx';

export default function AppointmentsFinished() {

  const [appointments, setAppointments] = useState<AppointmentDTO[]>([]);

  useEffect(() => {
    appointmentService.findAllAppointmentsByStatus("FINISHED")
      .then(response => {
        console.log(response.data);
        setAppointments(response.data.content);
      })
  }, []);

  return (
    <section id="appointments-in-waiting-section" className="bcf-container-1200px">
      <h2 className='bcf-appointment-status-title-section'>Atendimentos finalizados:</h2>

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