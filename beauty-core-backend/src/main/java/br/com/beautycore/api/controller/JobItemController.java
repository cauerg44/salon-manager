package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.request.JobItemCreateRequestDTO;
import br.com.beautycore.api.dto.request.JobItemPatchRequestDTO;
import br.com.beautycore.api.dto.response.JobItemResponseDTO;
import br.com.beautycore.api.services.JobItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "v1/services")
public class JobItemController {

    @Autowired
    private JobItemService service;

    @GetMapping
    public ResponseEntity<List<JobItemResponseDTO>> findAll() {
        var result = service.findAll();
        return ResponseEntity.ok(result);
    }

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

    @PatchMapping("/{id}")
    public ResponseEntity<JobItemResponseDTO> patch(@PathVariable Long id, @Valid @RequestBody JobItemPatchRequestDTO dto) {
        var result = service.patch(id, dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}