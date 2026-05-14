package br.com.beautycore.api.projections;

import java.math.BigDecimal;

public interface TotalProfitInLiveProjection {

    BigDecimal getTotalProfitInLive();
    BigDecimal getPix();
    BigDecimal getCash();
    BigDecimal getDebit();
    BigDecimal getCredit();

}