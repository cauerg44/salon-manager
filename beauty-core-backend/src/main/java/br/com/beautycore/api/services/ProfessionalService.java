package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.ProfessionalPatchRequestDTO;
import br.com.beautycore.api.dto.response.ProfessionalResponseDTO;
import br.com.beautycore.api.entity.Professional;
import br.com.beautycore.api.entity.Specialty;
import br.com.beautycore.api.repository.ProfessionalRepository;
import br.com.beautycore.api.repository.SpecialtyRepository;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import br.com.beautycore.api.utils.CustomProfessionalUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProfessionalService {

    private final ProfessionalRepository repository;
    private final SpecialtyRepository specialtyRepository;

    private final CustomProfessionalUtil customProfessionalUtil;

    @Transactional(readOnly = true)
    public List<ProfessionalResponseDTO> findAll() {
        List<Professional> list = repository.findAll();
        return list.stream()
                .map(professional -> new ProfessionalResponseDTO(professional))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProfessionalResponseDTO getMe() {
        Professional entity = authenticated();
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

    @Transactional
    public ProfessionalResponseDTO deactivate(Long id) {
        try {
            Professional entity = repository.getReferenceById(id);

            entity.setIsActive(false);
            entity.setIsWorking(false);
            entity.setUpdatedAt(LocalDateTime.now());

            entity = repository.save(entity);

            return new ProfessionalResponseDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Profissional não encontrado");
        }
    }

    @Transactional
    public ProfessionalResponseDTO activate(Long id) {
        try {
            Professional entity = repository.getReferenceById(id);

            entity.setIsActive(true);
            entity.setUpdatedAt(LocalDateTime.now());

            entity = repository.save(entity);

            return new ProfessionalResponseDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Profissional não encontrado");
        }
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

    protected Professional authenticated() {
        try {
            String username = customProfessionalUtil.getLoggedUsername();
            return repository.findByEmail(username).get();
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("Invalid user");
        }
    }
}
