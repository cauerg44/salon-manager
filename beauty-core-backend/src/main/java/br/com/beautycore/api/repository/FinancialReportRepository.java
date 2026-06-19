package br.com.beautycore.api.repository;

import br.com.beautycore.api.dto.response.DataFiltered;
import br.com.beautycore.api.entity.Payment;
import br.com.beautycore.api.projections.TotalProfitInLiveProjection;
import br.com.beautycore.api.projections.TotalProfitProfessionalProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        WHERE p.paid_at >= current_date() and p.paid_at < current_date() + interval 1 day;
    """)
    TotalProfitInLiveProjection getTotalProfitInLive();

    @Query(nativeQuery = true, value = """
       SELECT
       coalesce(sum(CASE WHEN pay.amount_paid > 0 THEN pay.amount_paid ELSE 0 END), 0) AS total_profit,
       coalesce(sum(CASE WHEN pay.payment_method = 'PIX' THEN pay.amount_paid ELSE 0 END), 0) AS pix,
       coalesce(sum(CASE WHEN pay.payment_method = 'CASH' THEN pay.amount_paid ELSE 0 END), 0) AS cash,
       coalesce(sum(CASE WHEN pay.payment_method = 'DEBIT' THEN pay.amount_paid ELSE 0 END), 0) AS debit,
       coalesce(sum(CASE WHEN pay.payment_method = 'CREDIT' THEN pay.amount_paid ELSE 0 END), 0) AS credit
       FROM payments pay
       INNER JOIN appointments app ON pay.appointment_id = app.id
       INNER JOIN professionals prof ON app.professional_id = prof.id
       WHERE prof.id = :professionalId
       AND pay.paid_at >= current_date()
       AND pay.paid_at < current_date() + INTERVAL 1 DAY;
    """)
    TotalProfitProfessionalProjection getProfessionalTotalProfit(Long professionalId);

    @Query(nativeQuery = true, value = """
        SELECT DATE(pay.paid_at) as date, SUM(pay.amount_paid) as total FROM payments pay
        WHERE pay.paid_at BETWEEN :start AND :end + INTERVAL 1 DAY
        GROUP BY DATE(pay.paid_at)
        ORDER BY DATE(pay.paid_at) ASC
    """)
    List<DataFiltered> getTotalProfitFiltered(LocalDate start, LocalDate end);
}