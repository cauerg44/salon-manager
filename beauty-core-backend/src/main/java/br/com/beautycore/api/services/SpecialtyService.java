package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.SpecialtyCreateRequestDTO;
import br.com.beautycore.api.dto.request.SpecialtyPatchRequestDTO;
import br.com.beautycore.api.dto.response.SpecialtyResponseDTO;
import br.com.beautycore.api.entity.Professional;
import br.com.beautycore.api.entity.Specialty;
import br.com.beautycore.api.repository.SpecialtyRepository;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
                .map(specialtyEntity -> new SpecialtyResponseDTO(specialtyEntity.getId(), specialtyEntity.getName()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SpecialtyResponseDTO findById(Long id) {
        Specialty specialty = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada"));

        return new SpecialtyResponseDTO(specialty.getId(), specialty.getName());
    }

    @Transactional
    public SpecialtyResponseDTO save(SpecialtyCreateRequestDTO dto) {
        Specialty newSpecialty = new Specialty();

        newSpecialty.setName(dto.name());
        newSpecialty.setCreatedAt(LocalDateTime.now());

        return new SpecialtyResponseDTO(newSpecialty.getId(), newSpecialty.getName());
    }

    @Transactional
    public SpecialtyResponseDTO patch(Long id, SpecialtyPatchRequestDTO dto) {
        Specialty entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada"));

        entity.setName(dto.name());
        entity.setUpdatedAt(LocalDateTime.now());

        Specialty specialtyUpdated = repository.save(entity);

        return new SpecialtyResponseDTO(specialtyUpdated.getId(), specialtyUpdated.getName());
    }

    @Transactional
    public void delete(Long id) {
        Specialty specialty = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada"));

        for (Professional professional : specialty.getProfessionals()) {
            professional.getSpecializations().remove(specialty);
        }

        repository.delete(specialty);
    }

    protected Set<Specialty> addSpecializations(Set<Long> specializationsIds) {
        Set<Specialty> set = new HashSet<>();

        for (long specialtyId : specializationsIds) {
            Specialty specialty = repository.findById(specialtyId).orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada"));
            set.add(specialty);
        }

        return set;
    }
}