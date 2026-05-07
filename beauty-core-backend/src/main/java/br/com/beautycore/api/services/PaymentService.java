package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.PaymentCreateRequestDTO;
import br.com.beautycore.api.entity.Appointment;
import br.com.beautycore.api.repository.AppointmentRepository;
import br.com.beautycore.api.repository.PaymentRepository;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PaymentService {

    private static final LocalDateTime NOW = LocalDateTime.now();

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    protected void addPayment(Long appointmentId, PaymentCreateRequestDTO dto) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado"));

        // totalValue = 20
        // amountPaid = 20
        // remaining = 0

        BigDecimal remaining = dto.amount().subtract(appointment.getTotalValue());

        // 1. If client pays total value of appointment
        if (remaining.compareTo(BigDecimal.ZERO) == 0) {
            appointment.setTotalValue(BigDecimal.ZERO);
            appointment.setRemainingValue(BigDecimal.ZERO);
            appointment.setIsPaid(true);
            appointment.setUpdatedAt(NOW);
        }

        // 2. If client pays partial
        if (remaining.compareTo(BigDecimal.ZERO) > 0 && remaining.compareTo(appointment.getTotalValue()) < 0) {
            appointment.setRemainingValue(remaining);
            appointment.setUpdatedAt(NOW);
        }
    }
}