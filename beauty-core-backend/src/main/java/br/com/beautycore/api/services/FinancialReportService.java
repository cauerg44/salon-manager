package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.response.DataFiltered;
import br.com.beautycore.api.dto.response.ProfessionalProfitGroupByDate;
import br.com.beautycore.api.dto.response.TotalProfitFiltered;
import br.com.beautycore.api.projections.TotalProfitInLiveProjection;
import br.com.beautycore.api.projections.TotalProfitProfessionalProjection;
import br.com.beautycore.api.repository.FinancialReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    public List<ProfessionalProfitGroupByDate> findTotalProfitGroupedByProfessionalAndDate(String start, String end) {

        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return repository.findTotalProfitGroupedByProfessionalAndDate(startDate, endDate);
    }

    @Transactional(readOnly = true)
    public TotalProfitFiltered getTotalProfitFiltered(String start, String end) {

        List<DataFiltered> resultSet = getDates(start, end);

        BigDecimal sum = BigDecimal.ZERO;

        for (DataFiltered totalDay : resultSet) {
            sum = sum.add(totalDay.total());
        }

        return new TotalProfitFiltered(resultSet, sum);
    }

    @Transactional(readOnly = true)
    private List<DataFiltered> getDates(String start, String end) {

        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return repository.getTotalProfitFiltered(startDate, endDate);
    }

}
