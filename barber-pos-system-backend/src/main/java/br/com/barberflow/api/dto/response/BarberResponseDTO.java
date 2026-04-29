package br.com.barberflow.api.dto.response;

import br.com.barberflow.api.entity.Barber;

public record BarberResponseDTO(
        Long id,
        String name,
        Boolean isActive
) {
    public BarberResponseDTO(Barber entity) {
        this(
            entity.getId(),
            entity.getName(),
            entity.getActive()
        );
    }
}