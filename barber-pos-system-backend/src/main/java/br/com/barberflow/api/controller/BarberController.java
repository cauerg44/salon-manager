package br.com.barberflow.api.controller;

import br.com.barberflow.api.dto.request.BarberRequestDTO;
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
    public ResponseEntity<BarberResponseDTO> saveNewBarber(@Valid @RequestBody BarberRequestDTO dto) {
        var result = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.id()).toUri();
        return ResponseEntity.created(uri).body(result);
    }
}