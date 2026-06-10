package br.com.beautycore.api.dto.response;

import br.com.beautycore.api.entity.Appointment;
import br.com.beautycore.api.enums.AppointmentStatus;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public record AppointmentResponseDTO(
        Long id,
        ProfessionalResponseDTO professional,
        ClientResponseDTO client,
        AppointmentStatus appointmentStatus,
        Set<JobItemResponseDTO> services,
        BigDecimal discount,
        BigDecimal totalValue,
        BigDecimal remainingValue,
        Boolean isPaid
) {
    public AppointmentResponseDTO(Appointment entity) {
        this(
                entity.getId(),
                new ProfessionalResponseDTO(entity.getProfessional()),
                new ClientResponseDTO(entity.getClient()),
                entity.getAppointmentStatus(),
                entity.getServices()
                        .stream()
                        .map(item -> new JobItemResponseDTO(
                                item.getService().getId(),
                                item.getService().getName(),
                                item.getService().getBasePrice()
                        ))
                        .collect(Collectors.toSet()),
                entity.getDiscount(),
                entity.getTotalValue(),
                entity.getRemainingValue(),
                entity.getIsPaid()
        );
    }
}