package br.com.beautycore.api.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Set;

public record AppointmentPatchRequestDTO(


        Long professionalId,
        @NotEmpty(message = "O atendimento deve ter pelo menos 1 serviço")

        Set<@NotNull(message = "ID do serviço não pode ser nulo") @Positive(message = "ID do serviço deve ser positivo") Long> servicesIds,

        @Digits(integer = 6, fraction = 2, message = "Desconto deve ter no máximo 2 casas decimais")
        BigDecimal discount
) {
}