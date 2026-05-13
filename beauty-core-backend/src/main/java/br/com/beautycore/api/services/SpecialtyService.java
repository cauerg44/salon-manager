package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.SpecialtyCreateRequestDTO;
import br.com.beautycore.api.dto.response.SpecialtyResponseDTO;
import br.com.beautycore.api.entity.Professional;
import br.com.beautycore.api.entity.Specialty;
import br.com.beautycore.api.repository.SpecialtyRepository;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SpecialtyService {

    private final SpecialtyRepository repository;

    @Transactional(readOnly = true)
    public List<SpecialtyResponseDTO> findAll() {
        List<Specialty> list = repository.findAll();
        return list.stream()
                .map(item -> new SpecialtyResponseDTO(item.getId(), item.getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public SpecialtyResponseDTO save(SpecialtyCreateRequestDTO dto) {
        Specialty entity = new Specialty();
        entity.setName(dto.name());
        entity = repository.save(entity);

        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        return new SpecialtyResponseDTO(entity.getId(), entity.getName());
    }

    @Transactional
    public SpecialtyResponseDTO patch(Long id, SpecialtyCreateRequestDTO dto) {
        try {
            Specialty entity = repository.getReferenceById(id);

            entity.setName(dto.name());
            entity = repository.save(entity);
            entity.setUpdatedAt(LocalDateTime.now());

            return new SpecialtyResponseDTO(entity.getId(), entity.getName());
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Especialidade não encontrada");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Especialidade não encontrada");
        }
        repository.deleteById(id);
    }

    protected Set<Specialty> getSpecialtiesByIds(Set<Long> specializationsIds) {
        Set<Specialty> set = new HashSet<>();

        for (long specialtyId : specializationsIds) {
            Specialty specialty = repository.findById(specialtyId).orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada"));
            set.add(specialty);
        }

        return set;
    }
}
