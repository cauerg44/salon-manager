package br.com.beautycore.api.repository;

import br.com.beautycore.api.entity.Payment;
import br.com.beautycore.api.projections.TotalProfitInLiveProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(nativeQuery = true, value = """
        SELECT
        sum(case when p.amount_paid > 0 then p.amount_paid else 0 end) as total_profit_in_live,
        sum(case when p.payment_method = 'PIX' then p.amount_paid else 0 end) as pix,
        sum(case when p.payment_method = 'CASH' then p.amount_paid else 0 end) as cash,
        sum(case when p.payment_method = 'DEBIT' then p.amount_paid else 0 end) as debit,
        sum(case when p.payment_method = 'CREDIT' then p.amount_paid else 0 end) as credit
        FROM payments p
        INNER JOIN appointments a ON a.id = p.appointment_id
        WHERE p.paid_at BETWEEN :start AND :end
    """)
    TotalProfitInLiveProjection getTotalProfitInLive(LocalDateTime start, LocalDateTime end);
}