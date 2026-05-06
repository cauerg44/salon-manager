package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.ProfessionalCreateRequestDTO;
import br.com.beautycore.api.dto.request.ProfessionalPatchRequestDTO;
import br.com.beautycore.api.dto.response.ProfessionalResponseDTO;
import br.com.beautycore.api.entity.Professional;
import br.com.beautycore.api.entity.Specialty;
import br.com.beautycore.api.repository.ProfessionalRepository;
import br.com.beautycore.api.repository.SpecialtyRepository;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionalService {

    @Autowired
    private ProfessionalRepository repository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Transactional(readOnly = true)
    public List<ProfessionalResponseDTO> findAll() {
        List<Professional> list = repository.findAll();
        return list.stream()
                .map(professional -> new ProfessionalResponseDTO(professional))
                .collect(Collectors.toList());
    }

    @Transactional
    public ProfessionalResponseDTO save(ProfessionalCreateRequestDTO dto) {
        Professional entity = new Professional();

        createDtoToEntity(entity, dto);

        entity = repository.save(entity);

        return new ProfessionalResponseDTO(entity);
    }

    @Transactional
    public ProfessionalResponseDTO patch(Long id, ProfessionalPatchRequestDTO dto) {
        try {
            Professional entity = repository.getReferenceById(id);

            patchDtoToEntity(entity, dto);

            entity = repository.save(entity);

            return new ProfessionalResponseDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Profissional não encontrado");
        }
    }

    private void createDtoToEntity(Professional entity, ProfessionalCreateRequestDTO dto) {
        entity.setName(dto.name());
        entity.setIsActive(true);
        entity.setIsWorking(false);

        entity.getSpecializations().clear();
        for (long specialtyId : dto.specializationsIds()) {
            Specialty specialty = specialtyRepository.findById(specialtyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada"));
            entity.addSpecialty(specialty);
        }

        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
    }

    private void patchDtoToEntity(Professional entity, ProfessionalPatchRequestDTO dto) {
        if (dto.name() != null) {
            entity.setName(dto.name());
        }

        if (!dto.specializationsIds().isEmpty()) {
            entity.getSpecializations().clear();
            for (long specialtyId : dto.specializationsIds()) {
                Specialty specialty = specialtyRepository.findById(specialtyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada"));
                entity.addSpecialty(specialty);
            }
        }

        entity.setUpdatedAt(LocalDateTime.now());
    }
}
