package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.AppointmentCreateRequestDTO;
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
    private AppointmentServiceRepository appointmentServiceRepository;

    @Transactional(readOnly = true)
    public Page<AppointmentResponseDTO> findAll(Pageable pageable) {
        Page<Appointment> list = repository.findAll(pageable);
        return list.map(appointment -> new AppointmentResponseDTO(appointment));
    }

    @Transactional
    public AppointmentResponseDTO createAppointment(AppointmentCreateRequestDTO dto) {
        Appointment entity = new Appointment();
        AppointmentServiceEntity appointmentServiceEntity = new AppointmentServiceEntity();

        createDtoToEntity(dto, entity, appointmentServiceEntity);

        return new AppointmentResponseDTO(entity);
    }

    private void createDtoToEntity(AppointmentCreateRequestDTO dto, Appointment entity, AppointmentServiceEntity appointmentServiceEntity) {
        Professional professional = professionalRepository.findById(dto.professionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));

        Client client = clientRepository.findById(dto.professionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        if (professional.getIsWorking() == true) {
            throw new DomainException("O profissional se encontra em outro atendimento");
        }

        professional.setIsWorking(true);
        entity.setProfessional(professional);
        entity.setClient(client);

        entity.setAppointmentStatus(AppointmentStatus.WAITING);

        entity.getServices().clear();

        BigDecimal sum = BigDecimal.ZERO;

        for (long serviceId : dto.servicesIds()) {
            JobItem service = jobItemRepository.findById(serviceId)
                    .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));

            sum = sum.add(service.getBasePrice());

            appointmentServiceEntity.setService(service);
            appointmentServiceEntity.setDiscount(BigDecimal.ZERO);

            appointmentServiceEntity = appointmentServiceRepository.save(appointmentServiceEntity);
        }

        entity.setTotalValue(sum);
        entity.setRemainingValue(BigDecimal.ZERO);

        entity.setIsPaid(false);

        entity.setCreatedAt(NOW);
        entity.setUpdatedAt(NOW);
        entity.setFinishedAt(null);
    }
}