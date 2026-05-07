package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.AppointmentCreateRequestDTO;
import br.com.beautycore.api.dto.request.AppointmentPatchRequestDTO;
import br.com.beautycore.api.dto.response.AppointmentResponseDTO;
import br.com.beautycore.api.entity.*;
import br.com.beautycore.api.enums.AppointmentStatus;
import br.com.beautycore.api.repository.*;
import br.com.beautycore.api.services.exception.DomainException;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AppointmentService {

    private static final LocalDateTime NOW = LocalDateTime.now();

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private JobItemRepository jobItemRepository;

    @Autowired
    private PaymentService paymentService;

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
        Appointment entity = new Appointment();

        createDtoToEntity(dto, entity);

        return new AppointmentResponseDTO(entity);
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
        appointment.setUpdatedAt(NOW);

        repository.save(appointment);

        return new AppointmentResponseDTO(appointment);
    }

    @Transactional
    public AppointmentResponseDTO updateAppointmentServices(Long id, AppointmentPatchRequestDTO dto) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado"));

        if (appointment.getAppointmentStatus() == AppointmentStatus.WAITING || appointment.getAppointmentStatus() == AppointmentStatus.IN_PROGRESS) {
            BigDecimal sum = BigDecimal.ZERO;

            appointment.getServices().clear();
            for (long serviceId : dto.servicesIds()) {
                JobItem service = jobItemRepository.findById(serviceId)
                        .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));

                sum = sum.add(service.getBasePrice());

                appointment.getServices().add(new AppointmentServiceEntity(appointment, service, service.getBasePrice(), BigDecimal.ZERO));
            }

            appointment.setTotalValue(sum);
            appointment.setRemainingValue(sum);
            appointment.setUpdatedAt(NOW);

            repository.save(appointment);

            return new AppointmentResponseDTO(appointment);

        } else throw new DomainException("Apenas atendimentos com cliente em espera ou em atendimento podem editar os serviços desejados");
    }

    @Transactional
    public AppointmentResponseDTO cancelAppointment(Long id) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado"));

        if (appointment.getAppointmentStatus() != AppointmentStatus.WAITING) {
            throw new DomainException("É possível cancelar somente os atendimentos em espera");
        }

        appointment.setAppointmentStatus(AppointmentStatus.CANCELED);

        appointment.setUpdatedAt(NOW);

        return new AppointmentResponseDTO(appointment);
    }

//    @Transactional
//    public AppointmentResponseDTO finishAppointment(Long id) {
//         paymentService.addPayment(id);
//    }

    private void createDtoToEntity(AppointmentCreateRequestDTO dto, Appointment entity) {
        Professional professional = professionalRepository.findById(dto.professionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));

        Client client = clientRepository.findById(dto.clientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        if (client.getInAppointment()) {
            throw new DomainException("Cliente se encontra em outro atendimento no momento.");
        }

        entity.setProfessional(professional);

        client.setInAppointment(false);
        entity.setClient(client);

        entity.setAppointmentStatus(AppointmentStatus.WAITING);

        entity.getServices().clear();
        BigDecimal sum = BigDecimal.ZERO;

        for (long serviceId : dto.servicesIds()) {
            JobItem service = jobItemRepository.findById(serviceId)
                    .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));

            sum = sum.add(service.getBasePrice());

            entity.getServices().add(new AppointmentServiceEntity(entity, service, service.getBasePrice(), BigDecimal.ZERO));
        }

        entity.setTotalValue(sum);
        entity.setRemainingValue(sum);

        entity.setIsPaid(false);

        entity.setCreatedAt(NOW);
        entity.setUpdatedAt(NOW);
        entity.setFinishedAt(null);

        repository.save(entity);
    }
}