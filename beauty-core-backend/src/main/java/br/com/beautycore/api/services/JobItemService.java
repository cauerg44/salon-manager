package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.JobItemCreateRequestDTO;
import br.com.beautycore.api.dto.request.JobItemPatchRequestDTO;
import br.com.beautycore.api.dto.response.JobItemResponseDTO;
import br.com.beautycore.api.entity.Appointment;
import br.com.beautycore.api.entity.AppointmentServiceEntity;
import br.com.beautycore.api.entity.JobItem;
import br.com.beautycore.api.repository.JobItemRepository;
import br.com.beautycore.api.services.exception.DatabaseException;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JobItemService {

    private final JobItemRepository repository;

    @Transactional(readOnly = true)
    public List<JobItemResponseDTO> findAll() {
        List<JobItem> list = repository.findAll();
        return list.stream()
                .map(service -> new JobItemResponseDTO(service.getId(), service.getName(), service.getBasePrice()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public JobItemResponseDTO findById(Long id) {
        JobItem service = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));

        return new JobItemResponseDTO(service.getId(), service.getName(), service.getBasePrice());
    }

    @Transactional
    public JobItemResponseDTO save(JobItemCreateRequestDTO dto) {
        JobItem entity = repository.save(JobItem.builder()
                .name(dto.name())
                .basePrice(dto.basePrice())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());

        return new JobItemResponseDTO(entity.getId(), entity.getName(), entity.getBasePrice());
    }

    @Transactional
    public JobItemResponseDTO patch(Long id, JobItemPatchRequestDTO dto) {
        JobItem entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));

        patchDtoToEntity(entity, dto);
        entity.setUpdatedAt(LocalDateTime.now());

        JobItem serviceUpdated = repository.save(entity);

        return new JobItemResponseDTO(serviceUpdated.getId(), serviceUpdated.getName(), serviceUpdated.getBasePrice());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Serviço não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    protected BigDecimal addServicesInAppointment(Appointment entity, Set<Long> servicesIds) {
        BigDecimal sum = BigDecimal.ZERO;

        for (long serviceId : servicesIds) {
            JobItem service = repository.findById(serviceId)
                    .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));

            sum = sum.add(service.getBasePrice());
            entity.getServices().add(new AppointmentServiceEntity(entity, service, service.getBasePrice()));
        }

        return sum;
    }

    private void patchDtoToEntity(JobItem entity, JobItemPatchRequestDTO dto) {
        if (dto.name() != null) {
            entity.setName(dto.name());
        }

        if (dto.basePrice() != null) {
            entity.setBasePrice(dto.basePrice());
        }
    }
}
