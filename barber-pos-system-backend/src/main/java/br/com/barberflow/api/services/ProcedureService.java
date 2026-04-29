package br.com.barberflow.api.services;

import br.com.barberflow.api.dto.request.ProcedureRequestDTO;
import br.com.barberflow.api.dto.response.ProcedureResponseDTO;
import br.com.barberflow.api.entity.Procedure;
import br.com.barberflow.api.repository.ProcedureRepository;
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
    public ProcedureResponseDTO insert(ProcedureRequestDTO dto) {
        Procedure entity = new Procedure();

        entity.setName(dto.name());
        entity.setBasePrice(dto.basePrice());

        entity = repository.save(entity);
        return new ProcedureResponseDTO(entity);
    }
}
