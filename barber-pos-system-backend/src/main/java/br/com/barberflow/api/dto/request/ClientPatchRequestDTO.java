package br.com.barberflow.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClientPatchRequestDTO(

        @Size(max = 50, message = "Nome do cliente deve ter no máximo 50 caracteres")
        String name,

        @Pattern(regexp = "\\d{11}", message = "Telefone deve conter exatamente 11 dígitos numéricos")
        String phone,

        @JsonFormat(pattern = "dd/MM/yyyy")
        @Past(message = "Data de nascimento do cliente deve ser no passado")
        LocalDate birthDate
) {
}
