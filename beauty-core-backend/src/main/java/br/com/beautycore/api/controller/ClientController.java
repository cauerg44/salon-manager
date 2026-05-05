package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.request.ClientCreateRequestDTO;
import br.com.beautycore.api.dto.request.ClientPatchRequestDTO;
import br.com.beautycore.api.dto.response.ClientResponseDTO;
import br.com.beautycore.api.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "v1/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> findAll() {
        var result = service.findAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(@Valid @RequestBody ClientCreateRequestDTO dto) {
        var result = service.save(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();

        return ResponseEntity.created(uri).body(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> patch(@PathVariable Long id, @Valid @RequestBody ClientPatchRequestDTO dto) {
        var result = service.patch(id, dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}