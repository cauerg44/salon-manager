package br.com.beautycore.api.entity;

import br.com.beautycore.api.enums.AppointmentStatus;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal discount;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal totalValue;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal remainingValue;

    private Boolean isPaid;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "appointment")
    private List<Payment> payments;

    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private LocalDateTime updatedAt;
}