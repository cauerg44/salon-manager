import './styles.css';
import { useEffect, useState } from 'react';
import type { AppointmentDTO } from '../../../../models/appointment';
import * as appointmentService from '../../../../services/appointment-service.ts';
import AppointmentNotPaidCard from '../../../../components/AppointmentNotPaidCard/index.tsx';

export default function AppointmentsNotPaid() {

  const [appointments, setAppointments] = useState<AppointmentDTO[]>([]);

  useEffect(() => {
    appointmentService.findAllAppointmentsNotPaid()
      .then(response => {
        setAppointments(response.data.content);
      })
  }, []);

  return (
    <section id="appointments-canceled-section" className="bcf-container-1200px">
      <h2 className='bcf-appointment-status-title-section'>Atendimentos que não estão pagos:</h2>

      <div className='bcf-appointments-cards-container'>
        {
          appointments.map(
            appointment => <AppointmentNotPaidCard key={appointment.id} appointmentDTO={appointment} />
          )
        }
      </div>
    </section>
  );
}