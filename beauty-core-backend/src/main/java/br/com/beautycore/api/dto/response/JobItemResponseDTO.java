package br.com.beautycore.api.dto.response;

import java.math.BigDecimal;

public record JobItemResponseDTO(
        Long id,
        String name,
        BigDecimal basePrice
) {
}