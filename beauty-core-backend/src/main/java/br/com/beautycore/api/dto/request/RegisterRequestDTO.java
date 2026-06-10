package br.com.beautycore.api.dto.request;

import jakarta.validation.constraints.*;

import java.util.Set;

public record RegisterRequestDTO(

        @NotBlank
        @Size(min = 3, max = 30, message = "O nome deve ter entre no mínimo 3 caracteres e máximo 30.")
        String name,

        @NotBlank(message = "E-mail não pode estar vazio")
        @Pattern(
                regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
                message = "E-mail inválido"
        )
        String email,

        @NotBlank(message = "Senha não pode estar vazia")
        String password,

        @NotEmpty(message = "O profissional deve haver pelo menos 1 especialidade")
        Set<
                @NotNull(message = "ID da especialidade não pode ser nulo")
                @Positive(message = "ID da especialidade deve ser positivo")
                        Long
                > specializationsIds
) {
}