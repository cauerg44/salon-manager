package br.com.barberflow.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BarberRequestDTO(

        @NotBlank(message = "Nome do barbeiro não pode estar vazio.")
        @Size(max = 50, message = "Nome do barbeiro deve ter no máximo 50 caracteres")
        String name
) {
}
