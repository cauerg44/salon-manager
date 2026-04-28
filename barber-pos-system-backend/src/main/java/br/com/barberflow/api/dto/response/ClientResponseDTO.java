package br.com.barberflow.api.dto.response;

import br.com.barberflow.api.entity.Client;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ClientResponseDTO(
        Long id,
        String name,
        String phone,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate birthDate
) {
    public ClientResponseDTO(Client entity) {
        this(
            entity.getId(),
            entity.getName(),
            entity.getPhone(),
            entity.getBirthDate()
        );
    }
}