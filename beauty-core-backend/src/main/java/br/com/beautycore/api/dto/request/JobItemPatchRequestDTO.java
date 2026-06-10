package br.com.beautycore.api.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record JobItemPatchRequestDTO(

        @Size(max = 30, message = "Nome do serviço deve ter no máximo 30 caracteres")
        String name,

        @Positive(message = "Preço do serviço deve ser positivo")
        @Digits(integer = 10, fraction = 2, message = "Preço deve ter no máximo 2 casas decimais")
        BigDecimal basePrice
) {
}