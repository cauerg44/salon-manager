package br.com.beautycore.api.dto.response;

import br.com.beautycore.api.entity.Payment;
import br.com.beautycore.api.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponseDTO(
        Long id,
        PaymentMethod paymentMethod,
        BigDecimal amountPaid,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime paidAt,

        AppointmentResponseDTO appointment
) {
    public PaymentResponseDTO(Payment entity) {
        this (
            entity.getId(),
            entity.getPaymentMethod(),
            entity.getAmountPaid(),
            entity.getPaidAt(),
            new AppointmentResponseDTO(entity.getAppointment())
        );
    }
}