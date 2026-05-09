package br.com.beautycore.api.services;

import br.com.beautycore.api.converter.AppointmentConverter;
import br.com.beautycore.api.dto.request.AppointmentCreateRequestDTO;
import br.com.beautycore.api.dto.request.AppointmentPatchRequestDTO;
import br.com.beautycore.api.dto.response.AppointmentResponseDTO;
import br.com.beautycore.api.entity.*;
import br.com.beautycore.api.enums.AppointmentStatus;
import br.com.beautycore.api.repository.*;
import br.com.beautycore.api.services.exception.DomainException;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final JobItemRepository jobItemRepository;
    private final PaymentService paymentService;
    private final JobItemService jobItemService;

    @Transactional(readOnly = true)
    public Page<AppointmentResponseDTO> findAll(Pageable pageable) {
        Page<Appointment> list = repository.findAll(pageable);
        return list.map(appointment -> new AppointmentResponseDTO(appointment));
    }

    @Transactional(readOnly = true)
    public AppointmentResponseDTO findById(Long id) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado"));
        return new AppointmentResponseDTO(appointment);
    }

    @Transactional
    public AppointmentResponseDTO createAppointment(AppointmentCreateRequestDTO dto) {
        Professional professional = professionalRepository.findById(dto.professionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));

        Client client = clientRepository.findById(dto.clientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        if (client.getInAppointment()) {
            throw new DomainException("Cliente se encontra em outro atendimento no momento.");
        }

        Appointment entity = AppointmentConverter.createDtoToEntityConverter(professional, client);

        BigDecimal sum = jobItemService.addServices(entity, dto.servicesIds());

        entity.setDiscount(BigDecimal.ZERO);
        entity.setTotalValue(sum);
        entity.setRemainingValue(sum);

        Appointment createdEntity = repository.save(entity);

        return new AppointmentResponseDTO(createdEntity);
    }

    @Transactional
    public AppointmentResponseDTO startAppointment(Long id) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado"));

        if (appointment.getAppointmentStatus() != AppointmentStatus.WAITING) {
            throw new DomainException("Apenas atendimentos com clientes em espera podem ser iniciados");
        }

        if (!appointment.getProfessional().getIsWorking()) {
            appointment.getProfessional().setIsWorking(true);
        }

        appointment.setAppointmentStatus(AppointmentStatus.IN_PROGRESS);
        appointment.getClient().setInAppointment(true);
        appointment.setUpdatedAt(LocalDateTime.now());

        repository.save(appointment);

        return new AppointmentResponseDTO(appointment);
    }

    @Transactional
    public AppointmentResponseDTO updateAppointment(Long id, AppointmentPatchRequestDTO dto) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado"));

        if (appointment.getAppointmentStatus() == AppointmentStatus.WAITING && dto.professionalId() != null) {
            Professional professional = professionalRepository.findById(dto.professionalId())
                            .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));

            appointment.setProfessional(professional);
        }
        else throw new DomainException("É possível somente alterar o profissional se o atendimento não estiver em andamento");

        BigDecimal sum = BigDecimal.ZERO;

        if (appointment.getAppointmentStatus() == AppointmentStatus.WAITING || appointment.getAppointmentStatus() == AppointmentStatus.IN_PROGRESS) {
            appointment.getServices().clear();
            for (long serviceId : dto.servicesIds()) {
                JobItem service = jobItemRepository.findById(serviceId)
                        .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));

                sum = sum.add(service.getBasePrice());

                appointment.getServices().add(new AppointmentServiceEntity(appointment, service, service.getBasePrice()));
            }

            appointment.setTotalValue(sum);
            appointment.setRemainingValue(sum);
        }
        else throw new DomainException("Apenas atendimentos com cliente em espera ou em atendimento podem editar os serviços desejados");

        if (dto.discount() != null && dto.discount().compareTo(appointment.getTotalValue()) > 0) {
            throw new DomainException("O desconto não pode ser maior do que o preço do atendimento");
        }

        if (dto.discount() != null) {
            appointment.setDiscount(dto.discount());
            appointment.setTotalValue(appointment.getTotalValue().subtract(dto.discount()));
            appointment.setRemainingValue(appointment.getTotalValue());
        }

        appointment.setUpdatedAt(LocalDateTime.now());

        repository.save(appointment);

        return new AppointmentResponseDTO(appointment);
    }

    @Transactional
    public AppointmentResponseDTO cancelAppointment(Long id) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado"));

        if (appointment.getAppointmentStatus() != AppointmentStatus.WAITING) {
            throw new DomainException("É possível cancelar somente os atendimentos em espera");
        }

        appointment.setAppointmentStatus(AppointmentStatus.CANCELED);
        appointment.setUpdatedAt(LocalDateTime.now());

        return new AppointmentResponseDTO(appointment);
    }
}