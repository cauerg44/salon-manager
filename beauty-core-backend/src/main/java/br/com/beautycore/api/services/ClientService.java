package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.request.ClientCreateRequestDTO;
import br.com.beautycore.api.dto.request.ClientPatchRequestDTO;
import br.com.beautycore.api.dto.response.ClientResponseDTO;
import br.com.beautycore.api.entity.Client;
import br.com.beautycore.api.repository.ClientRepository;
import br.com.beautycore.api.services.exception.DatabaseException;
import br.com.beautycore.api.services.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientResponseDTO> findAll(String name, Pageable pageable) {
        Page<Client> result = repository.searchByName(name, pageable);
        return result.map(ClientResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public List<ClientResponseDTO> findAllClientsNotInAppointment() {
        List<Client> result = repository.findAllClientsNotInAppointment();
        return result.stream().map(ClientResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientResponseDTO findById(Long id) {
        Client result = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        return new ClientResponseDTO(result);
    }

    @Transactional
    public ClientResponseDTO save(ClientCreateRequestDTO dto) {
        Client newClient = repository.save(Client.builder()
                .name(dto.name())
                .phone(dto.phone())
                .birthDate(dto.birthDate())
                .credit(BigDecimal.ZERO)
                .inAppointment(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());

        return new ClientResponseDTO(newClient);
    }

    @Transactional
    public ClientResponseDTO patch(Long id, ClientPatchRequestDTO dto) {
        Client entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        patchDtoToEntity(entity, dto);

        Client clientUpdated = repository.save(entity);

        return new ClientResponseDTO(clientUpdated);
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
