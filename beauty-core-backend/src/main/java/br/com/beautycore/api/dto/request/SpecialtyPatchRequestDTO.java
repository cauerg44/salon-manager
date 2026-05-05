package br.com.beautycore.api.dto.request;

import jakarta.validation.constraints.Size;

public record SpecialtyPatchRequestDTO(

        @Size(max = 30, message = "Nome do serviço deve ter no máximo 30 caracteres")
        String name
) {
}
