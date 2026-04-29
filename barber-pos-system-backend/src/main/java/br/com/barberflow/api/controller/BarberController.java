package br.com.barberflow.api.controller;

import br.com.barberflow.api.dto.request.BarberInsertRequestDTO;
import br.com.barberflow.api.dto.request.BarberPatchRequestDTO;
import br.com.barberflow.api.dto.response.BarberResponseDTO;
import br.com.barberflow.api.services.BarberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "v1/barbers")
public class BarberController {

    @Autowired
    private BarberService service;

    @GetMapping
    public ResponseEntity<List<BarberResponseDTO>> findAllBarbers() {
        var result = service.findAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<BarberResponseDTO> saveNewBarber(@Valid @RequestBody BarberInsertRequestDTO dto) {
        var result = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.id()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<BarberResponseDTO> patch(@PathVariable Long id, @Valid @RequestBody BarberPatchRequestDTO dto) {
        var result = service.patch(id, dto);
        return ResponseEntity.ok(result);
    }

    @PatchMapping(value = "/{id}/deactivate")
    public ResponseEntity<BarberResponseDTO> deactivateBarber(@PathVariable Long id) {
        var result = service.deactivate(id);
        return ResponseEntity.ok(result);
    }

    @PatchMapping(value = "/{id}/activate")
    public ResponseEntity<BarberResponseDTO> activateBarber(@PathVariable Long id) {
        var result = service.activate(id);
        return ResponseEntity.ok(result);
    }
}