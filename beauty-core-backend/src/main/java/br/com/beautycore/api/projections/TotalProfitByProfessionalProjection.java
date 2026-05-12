package br.com.beautycore.api.projections;

import java.math.BigDecimal;

public interface TotalProfitByProfessionalProjection {

    String getProfessional();
    BigDecimal getTotalProfit();
    BigDecimal getPix();
    BigDecimal getCash();
    BigDecimal getDebit();
    BigDecimal getCredit();
}