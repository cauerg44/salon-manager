package br.com.beautycore.api.dto.response;

import br.com.beautycore.api.entity.Professional;
import br.com.beautycore.api.entity.Role;

import java.util.Set;
import java.util.stream.Collectors;

public record ProfessionalResponseDTO(
        Long id,
        String name,
        String email,
        Boolean isActive,
        Boolean isWorking,
        Set<SpecialtyResponseDTO> specializations,
        Set<Role> roles
) {
    public ProfessionalResponseDTO(Professional entity) {
        this(
            entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getIsActive(),
                entity.getIsWorking(),
                entity.getSpecializations()
                        .stream()
                        .map(specialty -> new SpecialtyResponseDTO(specialty.getId(), specialty.getName()))
                        .collect(Collectors.toSet()),
                entity.getRoles()
        );
    }
}