package br.com.beautycore.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SpecialtyCreateRequestDTO(

        @NotBlank(message = "Nome da especialidade não pode ser vazio.")
        @Size(max = 30, message = "Nome da especialidade deve ter no máximo 30 caracteres")
        String name
) {
}
