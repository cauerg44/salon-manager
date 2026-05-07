package br.com.beautycore.api.entity;

import br.com.beautycore.api.enums.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_seq")
    @SequenceGenerator(name = "appointment_seq", sequenceName = "sq_appointments", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AppointmentServiceEntity> services = new HashSet<>();

    private BigDecimal totalValue;
    private BigDecimal remainingValue;
    private Boolean isPaid;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "appointment")
    private List<Payment> payments;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime finishedAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
}