package br.com.barberflow.api.services;

import br.com.barberflow.api.dto.response.ClientResponseDTO;
import br.com.barberflow.api.entity.Client;
import br.com.barberflow.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientResponseDTO> findAll(String name, Pageable pageable) {
        Page<Client> clients = repository.searchByClientName(name, pageable);
        return clients.map(client -> new ClientResponseDTO(client));
    }
}