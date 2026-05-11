package br.com.beautycore.api.repository;

import br.com.beautycore.api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(nativeQuery = true, value = """
        SELECT sum(p.amount_paid) as profit_in_live FROM payments p
        INNER JOIN appointments a ON a.id = p.appointment_id
        WHERE p.paid_at >= :start AND p.paid_at <= :end;
    """)
    BigDecimal getTotalProfitInLive(LocalDateTime start, LocalDateTime end);
}