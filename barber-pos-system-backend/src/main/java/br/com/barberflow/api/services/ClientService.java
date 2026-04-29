package br.com.barberflow.api.services;

import br.com.barberflow.api.dto.request.ClientInsertRequestDTO;
import br.com.barberflow.api.dto.request.ClientPatchRequestDTO;
import br.com.barberflow.api.dto.response.ClientResponseDTO;
import br.com.barberflow.api.entity.Client;
import br.com.barberflow.api.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientResponseDTO> findAll(String name, Pageable pageable) {
        Page<Client> clients = repository.searchByClientName(name, pageable);
        return clients.map(client -> new ClientResponseDTO(client));
    }

    @Transactional
    public ClientResponseDTO insert(ClientInsertRequestDTO dto) {
        Client entity = new Client();

        entity.setName(dto.name());
        entity.setPhone(dto.phone());
        entity.setBirthDate(dto.birthDate());

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
            throw new ResolutionException("Cliente não encontrado.");
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
    }
}