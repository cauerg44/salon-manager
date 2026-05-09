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
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
                .map(item -> new JobItemResponseDTO(item.getId(), item.getName(), item.getBasePrice()))
                .collect(Collectors.toList());
    }

    @Transactional
    public JobItemResponseDTO save(JobItemCreateRequestDTO dto) {
        JobItem entity = new JobItem();
        entity.setName(dto.name());
        entity.setBasePrice(dto.basePrice());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        entity = repository.save(entity);

        return new JobItemResponseDTO(entity.getId(), entity.getName(), entity.getBasePrice());
    }

    @Transactional
    public JobItemResponseDTO patch(Long id, JobItemPatchRequestDTO dto) {
        try {
            JobItem entity = repository.getReferenceById(id);

            patchDtoToEntity(entity, dto);

            entity.setUpdatedAt(LocalDateTime.now());
            entity = repository.save(entity);

            return new JobItemResponseDTO(entity.getId(), entity.getName(), entity.getBasePrice());

        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Serviço não encontrado");
        }
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

    public BigDecimal addServices(Appointment entity, Set<Long> servicesIds) {

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
