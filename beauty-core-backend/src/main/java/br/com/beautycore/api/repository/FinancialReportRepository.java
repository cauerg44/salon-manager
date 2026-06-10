package br.com.beautycore.api.repository;

import br.com.beautycore.api.entity.Payment;
import br.com.beautycore.api.projections.TotalProfitInLiveProjection;
import br.com.beautycore.api.projections.TotalProfitProfessionalProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;

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
       select
       coalesce(sum(case when pay.amount_paid > 0 then pay.amount_paid else 0 end), 0) as total_profit,
       coalesce(sum(case when pay.payment_method = 'PIX' then pay.amount_paid else 0 end), 0) as pix,
       coalesce(sum(case when pay.payment_method = 'CASH' then pay.amount_paid else 0 end), 0) as cash,
       coalesce(sum(case when pay.payment_method = 'DEBIT' then pay.amount_paid else 0 end), 0) as debit,
       coalesce(sum(case when pay.payment_method = 'CREDIT' then pay.amount_paid else 0 end), 0) as credit
       from payments pay
       inner join appointments app on pay.appointment_id = app.id
       inner join professionals prof on app.professional_id = prof.id
       where prof.id = :professionalId
       and pay.paid_at >= current_date()
       and pay.paid_at < current_date() + interval 1 day;
    """)
    TotalProfitProfessionalProjection getProfessionalTotalProfit(Long professionalId);

    @Query(nativeQuery = true, value = """
        SELECT sum(pay.amount_paid) as total_calculated
        FROM payments pay
        WHERE pay.paid_at >= :start
        AND pay.paid_at < :end + INTERVAL 1 DAY;
    """)
    BigDecimal getTotalProfitFiltered(LocalDate start, LocalDate end);
}