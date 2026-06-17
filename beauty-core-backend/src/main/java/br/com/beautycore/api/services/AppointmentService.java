package br.com.beautycore.api.services;

import br.com.beautycore.api.converter.AppointmentConverter;
import br.com.beautycore.api.dto.request.AppointmentCreateRequestDTO;
import br.com.beautycore.api.dto.request.AppointmentPatchRequestDTO;
import br.com.beautycore.api.dto.response.AppointmentResponseDTO;
import br.com.beautycore.api.entity.Appointment;
import br.com.beautycore.api.entity.Client;
import br.com.beautycore.api.entity.Professional;
import br.com.beautycore.api.enums.AppointmentStatus;
import br.com.beautycore.api.repository.AppointmentRepository;
import br.com.beautycore.api.repository.ClientRepository;
import br.com.beautycore.api.repository.ProfessionalRepository;
import br.com.beautycore.api.services.exception.BusinessException;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AppointmentService {

    private final AppointmentRepository repository;
    private final ProfessionalRepository professionalRepository;
    private final ClientRepository clientRepository;

    private final JobItemService jobItemService;

    @Transactional(readOnly = true)
    public Page<AppointmentResponseDTO> findAllByStatus(Pageable pageable, String appointmentStatus) {
        String status = AppointmentStatus.valueOf(appointmentStatus).name();

        Page<Appointment> page = repository.findAllByStatusAndOrderByCreatedAtDesc(pageable, status);
        return page.map(AppointmentResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<AppointmentResponseDTO> findAllAppointmentsNotPaid(Pageable pageable) {

        Page<Appointment> page = repository.findAllAppointmentsNotPaid(pageable);
        return page.map(AppointmentResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public AppointmentResponseDTO findById(Long id) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado"));
        return new AppointmentResponseDTO(appointment);
    }

    @Transactional
    public AppointmentResponseDTO save(AppointmentCreateRequestDTO dto) {
        Professional professional = professionalRepository.findById(dto.professionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));

        Client client = clientRepository.findById(dto.clientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        if (client.getInAppointment()) {
            throw new BusinessException("Cliente se encontra em outro atendimento no momento.");
        }

        Appointment entity = AppointmentConverter.createDtoToEntityConverter(professional, client);

        BigDecimal sum = jobItemService.addServicesInAppointment(entity, dto.servicesIds());

        entity.setDiscount(BigDecimal.ZERO);
        entity.setTotalValue(sum);
        entity.setRemainingValue(sum);

        Appointment createdEntity = repository.save(entity);

        return new AppointmentResponseDTO(createdEntity);
    }

    @Transactional
    public AppointmentResponseDTO finish(Long id) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado"));

        if (appointment.getAppointmentStatus() != AppointmentStatus.IN_PROGRESS) {
            throw new BusinessException("Apenas atendimentos em andamento podem ser finalizados");
        }

        appointment.setAppointmentStatus(AppointmentStatus.FINISHED);
        appointment.getClient().setInAppointment(false);
        appointment.getProfessional().setIsWorking(false);
        appointment.setUpdatedAt(LocalDateTime.now());

        Appointment appointmentFinished = repository.save(appointment);

        return new AppointmentResponseDTO(appointmentFinished);
    }

    @Transactional
    public AppointmentResponseDTO start(Long id) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado"));

        if (appointment.getAppointmentStatus() != AppointmentStatus.WAITING) {
            throw new BusinessException("Apenas atendimentos com clientes em espera podem ser iniciados");
        }

        if (!appointment.getProfessional().getIsWorking()) {
            appointment.getProfessional().setIsWorking(true);
        }

        appointment.setAppointmentStatus(AppointmentStatus.IN_PROGRESS);
        appointment.getClient().setInAppointment(true);
        appointment.setUpdatedAt(LocalDateTime.now());

        Appointment appointmentInProgress = repository.save(appointment);

        return new AppointmentResponseDTO(appointmentInProgress);
    }

    @Transactional
    public AppointmentResponseDTO cancel(Long id) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado"));

        if (appointment.getAppointmentStatus() != AppointmentStatus.WAITING) {
            throw new BusinessException("É possível cancelar somente os atendimentos em espera");
        }

        appointment.setAppointmentStatus(AppointmentStatus.CANCELED);
        appointment.setUpdatedAt(LocalDateTime.now());

        return new AppointmentResponseDTO(appointment);
    }

    @Transactional
    public AppointmentResponseDTO patch(Long id, AppointmentPatchRequestDTO dto) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado"));

        appointmentUpdateValidationRules(appointment, dto);

        appointment.setUpdatedAt(LocalDateTime.now());

        Appointment appointmentUpdated = repository.save(appointment);

        return new AppointmentResponseDTO(appointmentUpdated);
    }

    private void appointmentUpdateValidationRules(Appointment appointment, AppointmentPatchRequestDTO dto) {
        if (appointment.getAppointmentStatus() == AppointmentStatus.WAITING || appointment.getAppointmentStatus() == AppointmentStatus.IN_PROGRESS) {
            appointment.getServices().clear();
            BigDecimal sum = jobItemService.addServicesInAppointment(appointment, dto.servicesIds());

            appointment.setTotalValue(sum);
            appointment.setRemainingValue(sum);
        }

        if (appointment.getAppointmentStatus() == AppointmentStatus.FINISHED) {
            appointment.setDiscount(dto.discount());
            appointment.setTotalValue(dto.totalValue().subtract(appointment.getDiscount()));
            appointment.setRemainingValue(appointment.getTotalValue());
        }

        if (dto.totalValue() != null && dto.totalValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("O preço do atendimento deve ser positivo");
        }

        if (dto.discount() != null && dto.discount().compareTo(appointment.getTotalValue()) > 0) {
            throw new BusinessException("O desconto não pode ser maior do que o preço do atendimento");
        }
    }
}