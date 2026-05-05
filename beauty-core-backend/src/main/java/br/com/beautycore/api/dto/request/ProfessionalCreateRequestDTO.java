package br.com.beautycore.api.dto.request;

import jakarta.validation.constraints.*;

import java.util.Set;

public record ProfessionalCreateRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 30, message = "Nome deve ter no máximo 30 caracteres")
        String name,

        @NotEmpty(message = "O profissional deve haver pelo menos 1 especialidade")
        Set<@NotNull(message = "ID da especialidade não pode ser nulo")
        @Positive(message = "ID do especialidade deve ser positivo") Long> specializations
) {}