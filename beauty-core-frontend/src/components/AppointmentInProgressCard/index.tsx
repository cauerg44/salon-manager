import { Link, useNavigate } from 'react-router-dom';
import type { AppointmentDTO } from '../../models/appointment';
import './styles.css';
import * as appointmentService from '../../services/appointment-service.ts';

type Props = {
  appointmentDTO: AppointmentDTO;
}

export default function AppointmentsInProgressCard({ appointmentDTO }: Props) {

  const navigate = useNavigate();

  function handleFinishAppointmentClick() {
    appointmentService.finishAppointment(appointmentDTO.id)
      .then(() => {
        navigate("/appointments/finished")
      })
      .catch(() => {
        navigate("/appointments/in-progress")
      })
  }

  return (
    <div className='bcf-appointment-card-container'>

      <div className='bcf-appointment-in-progress-card-infos'>
        <h3>Profissional: {appointmentDTO.professional.name}</h3>
        <h3>Cliente: {appointmentDTO.client.name}</h3>
        <div className='bcf-appointment-in-progress-card-status'>
          Em atendimento
        </div>
        <h4>Preço total: <span>R$ {appointmentDTO.totalValue.toFixed(2)}</span></h4>
        {
          appointmentDTO.isPaid === false
            ? <h4>Pago: Não</h4>
            : <h4>Pago: Sim</h4>
        }
      </div>

      <div className='bcf-appointment-in-progress-card-services'>
        {
          appointmentDTO.services.map(
            service =>
              <div key={service.id} className='bcf-appointment-card-service-item'>
                <h4>{service.name}</h4>
                <span>R$ {service.basePrice.toFixed(2)}</span>
              </div>
          )
        }

      </div>

      <div className='bcf-appointment-in-progress-card-actions'>
        <h3>Ações rápidas: </h3>

        <h4 onClick={handleFinishAppointmentClick} className='bcf-action-finish-appointment'>Finalizar atendimento</h4>

        <Link to={`/appointments/edit/${appointmentDTO.id}`}>
          <h4 className='bcf-action-edit-appointment'>Editar atendimento</h4>
        </Link>
      </div>

    </div>
  );
}