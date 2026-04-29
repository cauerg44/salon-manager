package br.com.barberflow.api.dto.response;

import br.com.barberflow.api.entity.Attendance;
import br.com.barberflow.api.entity.enums.AttendanceStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AttendanceResponseDTO(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime finishedAt,
        AttendanceStatus attendanceStatus,
        BigDecimal grossAmount,
        BarberResponseDTO barber,
        ClientResponseDTO client
) {
    public AttendanceResponseDTO(Attendance entity) {
        this(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getFinishedAt(),
                entity.getAttendanceStatus(),
                entity.getGrossAmount(),
                new BarberResponseDTO(entity.getBarber()),
                new ClientResponseDTO(entity.getClient())
        );
    }
}
