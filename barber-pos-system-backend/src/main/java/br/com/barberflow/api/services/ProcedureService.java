package br.com.barberflow.api.services;

import br.com.barberflow.api.dto.request.ProcedureInsertRequestDTO;
import br.com.barberflow.api.dto.request.ProcedurePatchRequestDTO;
import br.com.barberflow.api.dto.response.ProcedureResponseDTO;
import br.com.barberflow.api.entity.Procedure;
import br.com.barberflow.api.repository.ProcedureRepository;
import br.com.barberflow.api.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcedureService {

    @Autowired
    private ProcedureRepository repository;

    @Transactional(readOnly = true)
    public List<ProcedureResponseDTO> findAll() {
        List<Procedure> list = repository.findAll();
        return list.stream().map(procedure -> new ProcedureResponseDTO(procedure)).collect(Collectors.toList());
    }

    @Transactional
    public ProcedureResponseDTO insert(ProcedureInsertRequestDTO dto) {
        Procedure entity = new Procedure();

        entity.setName(dto.name());
        entity.setBasePrice(dto.basePrice());

        entity = repository.save(entity);
        return new ProcedureResponseDTO(entity);
    }

    @Transactional
    public ProcedureResponseDTO patch(Long id, ProcedurePatchRequestDTO dto) {
        try {
            Procedure entity = repository.getReferenceById(id);

            patchDtoToEntity(entity, dto);

            entity = repository.save(entity);
            return new ProcedureResponseDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Serviço ou procedimento não encontrado com id: " + id);
        }
    }

    private void patchDtoToEntity(Procedure entity, ProcedurePatchRequestDTO dto) {
        if (dto.name() != null) {
            entity.setName(dto.name());
        }
        if (dto.basePrice() != null) {
            entity.setBasePrice(dto.basePrice());
        }
    }
}
