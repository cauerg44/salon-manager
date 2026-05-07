package br.com.beautycore.api.dto.response;

import br.com.beautycore.api.entity.Client;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClientResponseDTO(
        Long id,
        String name,
        String phone,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthDate,

        BigDecimal credit,
        Boolean inAppointment
) {
    public ClientResponseDTO(Client entity) {
        this(
            entity.getId(),
            entity.getName(),
            entity.getPhone(),
            entity.getBirthDate(),
            entity.getCredit(),
            entity.getInAppointment()
        );
    }
}