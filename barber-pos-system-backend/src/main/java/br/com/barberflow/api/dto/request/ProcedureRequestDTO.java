package br.com.barberflow.api.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProcedureRequestDTO (

        @NotBlank(message = "Nome do serviço / procedimento não deve estar vazio")
        @Size(max = 30, message = "Nome deve ter no máximo 30 caracteres")
        String name,

        @NotNull(message = "Preço do serviço é obrigatório")
        @Positive(message = "Preço do serviço deve ser positivo")
        @Digits(integer = 6, fraction = 2, message = "Preço inválido")
        BigDecimal basePrice
) {
}
