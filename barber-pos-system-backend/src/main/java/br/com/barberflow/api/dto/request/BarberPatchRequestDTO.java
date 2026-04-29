package br.com.barberflow.api.dto.request;

import jakarta.validation.constraints.Size;

public record BarberPatchRequestDTO(

        @Size(max = 20, message = "Nome do barbeiro deve ter no máximo 20 caracteres")
        String name
) {
}
