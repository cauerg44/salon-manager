package br.com.beautycore.api.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "appointment_service")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AppointmentServiceEntity {

    @EmbeddedId
    private AppointmentServicePK id = new AppointmentServicePK();

    private BigDecimal priceAtMoment;
    private BigDecimal discount;

    public AppointmentServiceEntity(Appointment appointment, JobItem service, BigDecimal priceAtMoment, BigDecimal discount) {
        id.setAppointment(appointment);
        id.setJobItem(service);
        this.priceAtMoment = priceAtMoment;
        this.discount = discount;
    }

    public Appointment getAppointment() {
        return id.getAppointment();
    }

    public void setAppointment(Appointment appointment) {
        id.setAppointment(appointment);
    }

    public JobItem getService() {
        return id.getJobItem();
    }

    public void setService(JobItem service) {
        id.setJobItem(service);
    }
}