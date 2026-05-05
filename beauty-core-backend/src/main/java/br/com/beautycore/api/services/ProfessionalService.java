package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.ProfessionalCreateRequestDTO;
import br.com.beautycore.api.dto.request.ProfessionalPatchRequestDTO;
import br.com.beautycore.api.dto.response.ProfessionalResponseDTO;
import br.com.beautycore.api.entity.Professional;
import br.com.beautycore.api.repository.ProfessionalRepository;
import br.com.beautycore.api.services.exception.DatabaseException;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionalService {

    @Autowired
    private ProfessionalRepository repository;

    @Transactional(readOnly = true)
    public List<ProfessionalResponseDTO> findAll() {
        List<Professional> list = repository.findAll();
        return list.stream()
                .map(Professional -> new ProfessionalResponseDTO(Professional))
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(ProfessionalCreateRequestDTO dto) {
        Professional entity = new Professional();

        createDtoToEntity(entity, dto);

        entity = repository.save(entity);

        // return new ProfessionalResponseDTO(entity);
    }

    @Transactional
    public void patch(Long id, ProfessionalPatchRequestDTO dto) {
        try {
            Professional entity = repository.getReferenceById(id);

            patchDtoToEntity(entity, dto);

            entity.setUpdatedAt(LocalDateTime.now());
            entity = repository.save(entity);

            // return new ProfessionalResponseDTO(entity);

        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Professionale não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Professionale não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void createDtoToEntity(Professional entity, ProfessionalCreateRequestDTO dto) {

    }

    private void patchDtoToEntity(Professional entity, ProfessionalPatchRequestDTO dto) {

    }
}
