package br.com.beautycore.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProfessionalProfitGroupByDate(
        String professional,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date,

        BigDecimal total
) {
}
