package br.com.beautycore.api.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record JobItemCreateRequestDTO(

        @NotBlank(message = "Nome do serviço não pode ser vazio")
        @Size(max = 30, message = "Nome do serviço deve ter no máximo 30 caracteres")
        String name,

        @Positive(message = "Preço do serviço deve ser positivo")
        @Digits(integer = 10, fraction = 2, message = "Preço deve ter no máximo 2 casas decimais")
        BigDecimal basePrice
) {
}