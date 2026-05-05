package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.request.SpecialtyCreateRequestDTO;
import br.com.beautycore.api.dto.response.SpecialtyResponseDTO;
import br.com.beautycore.api.services.SpecialtyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "v1/specializations")
public class SpecialtyController {

    @Autowired
    private SpecialtyService service;

    @GetMapping
    public ResponseEntity<List<SpecialtyResponseDTO>> findAllSpecializations() {
        var result = service.findAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<SpecialtyResponseDTO> createNewSpecialty(@Valid @RequestBody SpecialtyCreateRequestDTO dto) {
        var result = service.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.id()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<SpecialtyResponseDTO> updateSpecialty(@PathVariable Long id, @Valid @RequestBody SpecialtyCreateRequestDTO dto) {
        var result = service.patch(id, dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SpecialtyResponseDTO> updateSpecialty(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}