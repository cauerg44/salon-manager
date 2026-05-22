import type { AppointmentDTO } from '../../models/appointment';
import './styles.css';

type Props = {
  appointmentDTO: AppointmentDTO;
}

export default function AppointmentFinishedCard({ appointmentDTO }: Props) {
  return (
    <div className='bcf-appointment-card-container'>

      <div className='bcf-appointment-finished-card-infos'>
        <h3>Profissional: {appointmentDTO.professional.name}</h3>
        <h3>Cliente: {appointmentDTO.client.name}</h3>
        <div className='bcf-appointment-finished-card-status'>
          Finalizado
        </div>
        <h4>Desconto: R$ {appointmentDTO.discount.toFixed(2)}</h4>
        <h4>Preço total: <span>R$ {appointmentDTO.totalValue.toFixed(2)}</span></h4>
        {
          appointmentDTO.isPaid === false
            ? <h4>Pago: Não</h4>
            : <h4>Pago: Sim</h4>
        }

      </div>

      <div className='bcf-appointment-in-waiting-card-services'>
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

    </div>
  );
}