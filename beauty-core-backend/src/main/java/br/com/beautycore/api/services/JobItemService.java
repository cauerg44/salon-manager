package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.JobItemCreateRequestDTO;
import br.com.beautycore.api.dto.request.JobItemPatchRequestDTO;
import br.com.beautycore.api.dto.response.JobItemResponseDTO;
import br.com.beautycore.api.entity.JobItem;
import br.com.beautycore.api.repository.JobItemRepository;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobItemService {

    @Autowired
    private JobItemRepository repository;

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
    public Void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Serviço não encontrado");
        }
        repository.deleteById(id);
        return null;
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
