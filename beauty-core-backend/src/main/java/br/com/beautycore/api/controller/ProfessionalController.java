package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.request.ProfessionalCreateRequestDTO;
import br.com.beautycore.api.dto.request.ProfessionalPatchRequestDTO;
import br.com.beautycore.api.dto.response.ProfessionalResponseDTO;
import br.com.beautycore.api.services.ProfessionalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "v1/professionals")
public class ProfessionalController {

    @Autowired
    private ProfessionalService service;

    @GetMapping
    public ResponseEntity<List<ProfessionalResponseDTO>> findAll() {
        var result = service.findAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ProfessionalResponseDTO> createNewProfessional(@Valid @RequestBody ProfessionalCreateRequestDTO dto) {
        var result = service.save(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();

        return ResponseEntity.created(uri).body(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProfessionalResponseDTO> patch(@PathVariable Long id, @Valid @RequestBody ProfessionalPatchRequestDTO dto) {
        var result = service.patch(id, dto);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ProfessionalResponseDTO> deactivate(@PathVariable Long id) {
        var result = service.deactivate(id);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ProfessionalResponseDTO> activate(@PathVariable Long id) {
        var result = service.activate(id);
        return ResponseEntity.ok(result);
    }
}