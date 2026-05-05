package br.com.beautycore.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SpecialtyCreateRequestDTO(

        @NotBlank(message = "Nome da especialidade não pode ser vazio.")
        String name
) {
}
