package br.com.barberflow.api.dto.response;

import br.com.barberflow.api.entity.Procedure;

import java.math.BigDecimal;

public record ProcedureResponseDTO (
        Long id,
        String name,
        BigDecimal basePrice
) {
    public ProcedureResponseDTO(Procedure entity) {
        this (
            entity.getId(),
            entity.getName(),
            entity.getBasePrice()
        );
    }
}
