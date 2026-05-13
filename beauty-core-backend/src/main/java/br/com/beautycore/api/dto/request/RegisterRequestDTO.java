package br.com.beautycore.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public record RegisterRequestDTO(
        @NotBlank
        String name,

        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotEmpty(message = "O profissional deve haver pelo menos 1 especialidade")
        Set<@NotNull(message = "ID da especialidade não pode ser nulo")
        @Positive(message = "ID do especialidade deve ser positivo") Long> specializationsIds
) {
}
