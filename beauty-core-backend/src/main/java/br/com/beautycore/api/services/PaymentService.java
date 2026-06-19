package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.PaymentCreateRequestDTO;
import br.com.beautycore.api.dto.response.PaymentResponseDTO;
import br.com.beautycore.api.entity.Appointment;
import br.com.beautycore.api.entity.Payment;
import br.com.beautycore.api.enums.AppointmentStatus;
import br.com.beautycore.api.repository.AppointmentRepository;
import br.com.beautycore.api.repository.PaymentRepository;
import br.com.beautycore.api.services.exception.BusinessException;
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
    public PaymentResponseDTO addPayment(Long appointmentId, PaymentCreateRequestDTO dto) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado"));

        paymentValidationRules(appointment, dto);

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

    private void paymentValidationRules(Appointment appointment, PaymentCreateRequestDTO dto) {

        if (appointment.getAppointmentStatus() == AppointmentStatus.CANCELED) {
            throw new BusinessException("É possível somente associar pagamento com atendimentos não cancelados.");
        }

        if (appointment.getIsPaid()) {
            throw new BusinessException("Atendimento já pago");
        }

        if (dto.amount().compareTo(appointment.getTotalValue()) > 0) {
            throw new BusinessException("O pagamento não pode ser maior do que o preço do atendimento");
        }

        BigDecimal remaining = appointment.getRemainingValue().subtract(dto.amount());

        // When clients pays appointment total price:
        if (dto.amount().compareTo(appointment.getRemainingValue()) == 0) {
            appointment.setIsPaid(true);
            appointment.setRemainingValue(BigDecimal.ZERO);
            appointment.setUpdatedAt(LocalDateTime.now());
            return;
        }

        // When client pays partial:
        if (dto.amount().compareTo(appointment.getRemainingValue()) < 0) {
            appointment.setIsPaid(false);
            appointment.setRemainingValue(remaining);
            appointment.setUpdatedAt(LocalDateTime.now());
        }
    }
}