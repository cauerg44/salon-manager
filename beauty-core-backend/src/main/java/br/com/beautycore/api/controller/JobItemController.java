package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.request.JobItemCreateRequestDTO;
import br.com.beautycore.api.dto.request.JobItemPatchRequestDTO;
import br.com.beautycore.api.dto.response.JobItemResponseDTO;
import br.com.beautycore.api.services.JobItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "v1/services")
@RequiredArgsConstructor
public class JobItemController {

    private final JobItemService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<JobItemResponseDTO>> findAll() {
        var result = service.findAll();
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<JobItemResponseDTO> create(@Valid @RequestBody JobItemCreateRequestDTO dto) {
        var result = service.save(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();

        return ResponseEntity.created(uri).body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<JobItemResponseDTO> patch(@PathVariable Long id, @Valid @RequestBody JobItemPatchRequestDTO dto) {
        var result = service.patch(id, dto);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}