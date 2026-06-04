package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.ProfessionalPatchRequestDTO;
import br.com.beautycore.api.dto.response.ProfessionalResponseDTO;
import br.com.beautycore.api.entity.Professional;
import br.com.beautycore.api.entity.Specialty;
import br.com.beautycore.api.repository.ProfessionalRepository;
import br.com.beautycore.api.repository.SpecialtyRepository;
import br.com.beautycore.api.services.exception.BusinessException;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import br.com.beautycore.api.utils.CustomProfessionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<ProfessionalResponseDTO> findAll(Pageable pageable, String name) {
        Page<Professional> result = repository.searchByName(name, pageable);
        return result.map(ProfessionalResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public List<ProfessionalResponseDTO> findAllByActivity(Boolean active) {
        List<Professional> result = repository.findAllByIsActive(active);
        return result.stream().map(ProfessionalResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProfessionalResponseDTO findById(Long id) {
        Professional result = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));

        return new ProfessionalResponseDTO(result);
    }

    @Transactional(readOnly = true)
    public ProfessionalResponseDTO getMe() {
        Professional entity = authenticated();
        return new ProfessionalResponseDTO(entity);
    }

    @Transactional
    public ProfessionalResponseDTO patch(Long id, ProfessionalPatchRequestDTO dto) {
        Professional entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));

        patchDtoToEntity(entity, dto);

        Professional professionalUpdated = repository.save(entity);

        return new ProfessionalResponseDTO(professionalUpdated);
    }

    @Transactional
    public ProfessionalResponseDTO deactivate(Long id) {
        Professional professional = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));

        if (!professional.getIsActive()) {
            throw new BusinessException("Profissional já está desativado");
        }

        professional.setIsActive(false);
        professional.setUpdatedAt(LocalDateTime.now());

        Professional professionalDeactivated = repository.save(professional);

        return new ProfessionalResponseDTO(professionalDeactivated);
    }

    @Transactional
    public ProfessionalResponseDTO activate(Long id) {

        Professional professional = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));

        if (professional.getIsActive()) {
            throw new BusinessException("Profissional já está ativo");
        }

        professional.setIsActive(true);
        professional.setUpdatedAt(LocalDateTime.now());

        Professional professionalActivated = repository.save(professional);

        return new ProfessionalResponseDTO(professionalActivated);
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
