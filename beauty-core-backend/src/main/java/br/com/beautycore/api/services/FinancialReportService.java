package br.com.beautycore.api.services;

import br.com.beautycore.api.projections.TotalProfitFiltered;
import br.com.beautycore.api.projections.TotalProfitInLiveProjection;
import br.com.beautycore.api.projections.TotalProfitProfessionalProjection;
import br.com.beautycore.api.repository.FinancialReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class FinancialReportService {

    private final FinancialReportRepository repository;

    @Transactional(readOnly = true)
    public TotalProfitInLiveProjection getTotalProfitInLive() {
        return repository.getTotalProfitInLive();
    }

    @Transactional(readOnly = true)
    public TotalProfitProfessionalProjection getProfessionalTotalProfitInLive(Long professionalId) {
        return repository.getProfessionalTotalProfit(professionalId);
    }
    @Transactional(readOnly = true)
    public TotalProfitFiltered getTotalProfitFiltered(String start, String end) {

        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return repository.getTotalProfitFiltered(startDate, endDate);
    }

}
