package br.com.barberflow.api.dto.response;

import br.com.barberflow.api.entity.Attendance;
import br.com.barberflow.api.entity.Procedure;
import br.com.barberflow.api.entity.enums.AttendanceStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record AttendanceResponseDTO(
        Long id,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime createdAt,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime finishedAt,

        AttendanceStatus attendanceStatus,
        BigDecimal grossAmount,
        BarberResponseDTO barber,
        ClientResponseDTO client,
        Set<ProcedureResponseDTO> procedures
) {
    public AttendanceResponseDTO(Attendance entity) {
        this(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getFinishedAt(),
                entity.getAttendanceStatus(),
                entity.getGrossAmount(),
                new BarberResponseDTO(entity.getBarber()),
                new ClientResponseDTO(entity.getClient()),
                entity.getProcedures().stream().map(procedure -> new ProcedureResponseDTO(procedure)).collect(Collectors.toSet())
        );
    }
}
