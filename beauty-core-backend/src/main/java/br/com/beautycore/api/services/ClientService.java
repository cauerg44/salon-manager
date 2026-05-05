package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.ClientCreateRequestDTO;
import br.com.beautycore.api.dto.request.ClientPatchRequestDTO;
import br.com.beautycore.api.dto.response.ClientResponseDTO;
import br.com.beautycore.api.entity.Client;
import br.com.beautycore.api.repository.ClientRepository;
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
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public List<ClientResponseDTO> findAll() {
        List<Client> list = repository.findAll();
        return list.stream()
                .map(client -> new ClientResponseDTO(client))
                .collect(Collectors.toList());
    }

    @Transactional
    public ClientResponseDTO save(ClientCreateRequestDTO dto) {
        Client entity = new Client();

        createDtoToEntity(entity, dto);

        entity = repository.save(entity);

        return new ClientResponseDTO(entity);
    }

    @Transactional
    public ClientResponseDTO patch(Long id, ClientPatchRequestDTO dto) {
        try {
            Client entity = repository.getReferenceById(id);

            patchDtoToEntity(entity, dto);

            entity.setUpdatedAt(LocalDateTime.now());
            entity = repository.save(entity);

            return new ClientResponseDTO(entity);

        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void createDtoToEntity(Client entity, ClientCreateRequestDTO dto) {
        entity.setName(dto.name());
        entity.setPhone(dto.phone());
        entity.setBirthDate(dto.birthDate());
        entity.setCredit(BigDecimal.ZERO);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
    }

    private void patchDtoToEntity(Client entity, ClientPatchRequestDTO dto) {
        if (dto.name() != null) {
            entity.setName(dto.name());
        }

        if (dto.phone() != null) {
            entity.setPhone(dto.phone());
        }

        if (dto.birthDate() != null) {
            entity.setBirthDate(dto.birthDate());
        }

        if (dto.credit() != null) {
            entity.setCredit(dto.credit());
        }
    }
}
