package br.com.beautycore.api.entity;

import br.com.beautycore.api.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "amount_paid", nullable = false, precision = 5, scale = 2)
    private BigDecimal amountPaid;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "paid_at", columnDefinition = "DEFAULT CURRENT TIMESTAMP")
    private LocalDateTime paidAt;
}