package br.com.beautycore.api.dto.request;

import jakarta.validation.constraints.*;

import java.util.Set;

public record ProfessionalPatchRequestDTO(

        @Size(max = 30, message = "Nome deve ter no máximo 30 caracteres")
        String name,

        Set<@NotNull(message = "ID do especialidade não pode ser nulo") @Positive(message = "ID do especialidade deve ser positivo") Long> specializationsIds
) {

}