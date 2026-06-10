package br.com.beautycore.api.entity;

import jakarta.persistence.Column;
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

    @Column(name = "price_at_moment", precision = 5, scale = 2, nullable = false)
    private BigDecimal priceAtMoment;

    public AppointmentServiceEntity(Appointment appointment, JobItem service, BigDecimal priceAtMoment) {
        id.setAppointment(appointment);
        id.setJobItem(service);
        this.priceAtMoment = priceAtMoment;
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