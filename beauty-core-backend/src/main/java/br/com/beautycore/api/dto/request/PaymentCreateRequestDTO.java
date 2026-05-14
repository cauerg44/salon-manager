package br.com.beautycore.api.dto.request;

import br.com.beautycore.api.enums.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PaymentCreateRequestDTO(

        @NotNull(message = "É nescessário ter associar com o atendimento finalizado.")
        Long appointmentId,

        @NotNull(message = "Valor do pagamento é obrigatório")
        @DecimalMin(value = "0.01", message = "Valor do pagamento deve ser maior que zero")
        @Digits(integer = 6, fraction = 2, message = "Valor deve ter no máximo 2 casas decimais")
        BigDecimal amount,

        @NotNull(message = "Método de pagamento é obrigatório")
        PaymentMethod paymentMethod
) {
}