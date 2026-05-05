package br.com.beautycore.api.entity;

import br.com.beautycore.api.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "checkouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Checkout {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private BigDecimal grossValue;
    private BigDecimal discount;
    private BigDecimal totalValue;
    private BigDecimal remainingAmount;

    private Boolean isPaid;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "checkout")
    private List<Payment> payments = new ArrayList<>();

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime finishedAt;

    public void addPayment(Payment payment) {
        BigDecimal amountPaid = payment.getAmountPaid();
        payment.setCheckout(this);

        this.remainingAmount = this.remainingAmount.subtract(amountPaid);

        // If client pays more than original price:
        if (remainingAmount.compareTo(BigDecimal.ZERO) < 0) {
            appointment.getClient().setCredit(remainingAmount.abs());
            this.isPaid = true;
            this.finishedAt = LocalDateTime.now();
        }

        // Check if checkout is finished
        if (remainingAmount.compareTo(BigDecimal.ZERO) == 0) {
            this.isPaid = true;
            this.finishedAt = LocalDateTime.now();
        }
    }

    public void removePayment(Payment payment) {
        this.payments.remove(payment);
    }
}