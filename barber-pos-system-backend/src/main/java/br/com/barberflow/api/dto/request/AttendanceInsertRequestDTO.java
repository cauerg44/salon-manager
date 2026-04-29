package br.com.barberflow.api.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record AttendanceInsertRequestDTO(

        @NotNull(message = "Barbeiro é obrigatório")
        @Positive(message = "ID do barbeiro deve ser positivo")
        Long barberId,

        @NotNull(message = "Cliente é obrigatório")
        @Positive(message = "ID do cliente deve ser positivo")
        Long clientId,

        @NotEmpty(message = "Deve conter pelo menos um procedimento")
        List<@NotNull @Positive Long> proceduresIds
) {
}