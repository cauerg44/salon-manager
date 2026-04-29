package br.com.barberflow.api.controller;

import br.com.barberflow.api.dto.request.ClientInsertRequestDTO;
import br.com.barberflow.api.dto.request.ClientPatchRequestDTO;
import br.com.barberflow.api.dto.response.ClientResponseDTO;
import br.com.barberflow.api.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "v1/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<Page<ClientResponseDTO>> findAllClients(@RequestParam String name, Pageable pageable) {
        var result = service.findAll(name, pageable);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> saveNewClient(@Valid @RequestBody ClientInsertRequestDTO dto) {
        var result = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.id()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClientPatchRequestDTO dto) {
        var result = service.patch(id, dto);
        return ResponseEntity.ok(result);
    }
}