package br.com.beautycore.api.entity;

import br.com.beautycore.api.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professional_id", nullable = false)
    private Professional professional;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_status", nullable = false)
    private AppointmentStatus appointmentStatus;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AppointmentServiceEntity> services = new HashSet<>();

    @Column(name = "discount", nullable = false, precision = 5, scale = 2)
    private BigDecimal discount;

    @Column(name = "total_value", nullable = false, precision = 5, scale = 2)
    private BigDecimal totalValue;

    @Column(name = "remaining_value", nullable = false, precision = 5, scale = 2)
    private BigDecimal remainingValue;

    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "appointment")
    private List<Payment> payments;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;
}