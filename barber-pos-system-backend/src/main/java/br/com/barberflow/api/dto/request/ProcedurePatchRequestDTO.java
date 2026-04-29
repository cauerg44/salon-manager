package br.com.barberflow.api.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProcedurePatchRequestDTO(

        @Size(max = 30, message = "Nome deve ter no máximo 30 caracteres")
        String name,

        @Positive(message = "Preço do serviço deve ser positivo")
        @Digits(integer = 6, fraction = 2, message = "Preço inválido")
        BigDecimal basePrice
) {
}
