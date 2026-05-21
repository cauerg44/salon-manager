package br.com.beautycore.api.services;

import br.com.beautycore.api.projections.TotalProfitInLiveProjection;
import br.com.beautycore.api.repository.FinancialReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FinancialReportService {

    private final FinancialReportRepository repository;

    @Transactional(readOnly = true)
    public TotalProfitInLiveProjection getTotalProfitInLive() {

        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(23, 59, 59);

        return repository.getTotalProfitInLive(start, end);
    }
}
