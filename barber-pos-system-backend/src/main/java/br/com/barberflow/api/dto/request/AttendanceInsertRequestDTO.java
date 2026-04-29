package br.com.barberflow.api.dto.request;

public record AttendanceInsertRequestDTO(
        Long barberId,
        Long clientId,
        Long[] proceduresIds
) {
}
