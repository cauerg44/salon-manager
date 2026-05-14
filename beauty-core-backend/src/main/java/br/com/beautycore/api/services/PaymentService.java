package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.PaymentCreateRequestDTO;
import br.com.beautycore.api.dto.response.PaymentResponseDTO;
import br.com.beautycore.api.entity.Appointment;
import br.com.beautycore.api.entity.Payment;
import br.com.beautycore.api.enums.AppointmentStatus;
import br.com.beautycore.api.repository.AppointmentRepository;
import br.com.beautycore.api.repository.PaymentRepository;
import br.com.beautycore.api.services.exception.DomainException;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository repository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public PaymentResponseDTO createPayment(PaymentCreateRequestDTO dto) {
        Appointment appointment = appointmentRepository.findById(dto.appointmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado"));

        createPaymentValidationRules(appointment, dto);

        Payment payment = new Payment();

        payment.setAppointment(appointment);
        payment.setPaymentMethod(dto.paymentMethod());
        payment.setAmountPaid(dto.amount());
        payment.setPaidAt(LocalDateTime.now());

        appointment.getPayments().add(payment);
        appointmentRepository.save(appointment);

        Payment paymentCreated = repository.save(payment);
        return new PaymentResponseDTO(paymentCreated);
    }

    private void createPaymentValidationRules(Appointment appointment, PaymentCreateRequestDTO dto) {

        if (appointment.getAppointmentStatus() == AppointmentStatus.CANCELED) {
            throw new DomainException("É possível somente associar pagamento com atendimentos não cancelados.");
        }

        if (appointment.getIsPaid()) {
            throw new DomainException("Atendimento já pago");
        }

        BigDecimal remaining = appointment.getTotalValue().subtract(dto.amount());

        // 1. If clients pays appointment total price:
        if (dto.amount().compareTo(appointment.getTotalValue()) == 0) {
            appointment.setIsPaid(true);
            appointment.setRemainingValue(remaining);
            appointment.setUpdatedAt(LocalDateTime.now());
            return;
        }

        // 2. If client pays partial:
        if (dto.amount().compareTo(appointment.getTotalValue()) < 0) {
            appointment.setIsPaid(false);
            appointment.setRemainingValue(remaining);
            appointment.setUpdatedAt(LocalDateTime.now());
            return;
        }

        // 3. If client pays more than appointment total price;
        if (dto.amount().compareTo(appointment.getTotalValue()) > 0) {
            appointment.setIsPaid(true);
            appointment.setRemainingValue(BigDecimal.ZERO);
            appointment.getClient().setCredit(remaining.abs());
            appointment.setUpdatedAt(LocalDateTime.now());
        }
    }
}