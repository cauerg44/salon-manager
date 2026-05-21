package br.com.beautycore.api.repository;

import br.com.beautycore.api.entity.Payment;
import br.com.beautycore.api.projections.TotalProfitByProfessionalProjection;
import br.com.beautycore.api.projections.TotalProfitInLiveProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FinancialReportRepository extends JpaRepository<Payment, Long> {

    @Query(nativeQuery = true, value = """
        SELECT
            COALESCE(SUM(CASE WHEN p.amount_paid > 0 THEN p.amount_paid ELSE 0 END), 0) AS totalProfitInLive,
            COALESCE(SUM(CASE WHEN p.payment_method = 'PIX' THEN p.amount_paid ELSE 0 END), 0) AS pix,
            COALESCE(SUM(CASE WHEN p.payment_method = 'CASH' THEN p.amount_paid ELSE 0 END), 0) AS cash,
            COALESCE(SUM(CASE WHEN p.payment_method = 'DEBIT' THEN p.amount_paid ELSE 0 END), 0) AS debit,
            COALESCE(SUM(CASE WHEN p.payment_method = 'CREDIT' THEN p.amount_paid ELSE 0 END), 0) AS credit
        FROM payments p
        INNER JOIN appointments a ON a.id = p.appointment_id
        WHERE p.paid_at BETWEEN :start AND :end
    """)
    TotalProfitInLiveProjection getTotalProfitInLive(LocalDateTime start, LocalDateTime end);
}