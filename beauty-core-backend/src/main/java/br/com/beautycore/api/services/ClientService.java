package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.ClientCreateRequestDTO;
import br.com.beautycore.api.dto.request.ClientPatchRequestDTO;
import br.com.beautycore.api.dto.response.ClientResponseDTO;
import br.com.beautycore.api.entity.Client;
import br.com.beautycore.api.repository.ClientRepository;
import br.com.beautycore.api.services.exception.DatabaseException;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientResponseDTO> findAll(String name, Pageable pageable) {
        Page<Client> result = repository.searchByName(name, pageable);
        return result.map(client -> new ClientResponseDTO(client));
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
        entity.setInAppointment(false);

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

        entity.setUpdatedAt(LocalDateTime.now());
    }
}
