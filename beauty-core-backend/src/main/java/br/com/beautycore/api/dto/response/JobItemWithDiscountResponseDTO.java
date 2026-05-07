package br.com.beautycore.api.dto.response;

import java.math.BigDecimal;

public record JobItemWithDiscountResponseDTO(
        Long id,
        String name,
        BigDecimal basePrice,
        BigDecimal discount
) {
}