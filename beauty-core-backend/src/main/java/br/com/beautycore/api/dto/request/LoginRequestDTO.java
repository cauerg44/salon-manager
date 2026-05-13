package br.com.beautycore.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(

        @NotBlank
        String email,

        @NotBlank
        String password
) {
}
