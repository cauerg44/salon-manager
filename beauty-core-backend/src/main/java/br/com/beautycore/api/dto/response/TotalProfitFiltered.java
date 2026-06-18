package br.com.beautycore.api.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record TotalProfitFiltered(
        List<DataFiltered> list,
        BigDecimal totalCalculated
) {
}
