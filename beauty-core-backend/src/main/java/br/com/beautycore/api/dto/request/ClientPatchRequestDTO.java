package br.com.beautycore.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ClientPatchRequestDTO(

        @Size(max = 30, message = "Nome deve ter no máximo 100 caracteres")
        String name,

        @Pattern(regexp = "\\d{10,11}", message = "Telefone inválido")
        String phone,

        @Past(message = "Data de nascimento deve estar no passado")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthDate,

        @PositiveOrZero(message = "Crédito não pode ser negativo")
        @Digits(integer = 10, fraction = 2, message = "Crédito deve ter no máximo 2 casas decimais")
        BigDecimal credit
) {
}