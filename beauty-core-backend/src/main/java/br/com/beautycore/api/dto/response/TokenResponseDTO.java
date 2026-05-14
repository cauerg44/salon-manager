package br.com.beautycore.api.dto.response;

public record TokenResponseDTO(
        String token,
        long expiration
) {
}
