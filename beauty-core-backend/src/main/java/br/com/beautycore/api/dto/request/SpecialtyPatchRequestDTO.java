package br.com.beautycore.api.dto.request;

import jakarta.validation.constraints.Max;

public record SpecialtyPatchRequestDTO(

        @Max(value = 30, message = "Nome da especialidade deve ter no máximo 30 caracteres")
        String name
) {
}
