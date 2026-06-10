package br.com.beautycore.api.projections;

import java.math.BigDecimal;

public interface TotalProfitProfessionalProjection {

    BigDecimal getTotalProfit();
    BigDecimal getPix();
    BigDecimal getCash();
    BigDecimal getDebit();
    BigDecimal getCredit();
}