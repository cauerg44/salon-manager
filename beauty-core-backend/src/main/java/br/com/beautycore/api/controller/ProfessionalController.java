package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.request.ProfessionalPatchRequestDTO;
import br.com.beautycore.api.dto.response.ProfessionalResponseDTO;
import br.com.beautycore.api.services.ProfessionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/professionals")
@RequiredArgsConstructor
public class ProfessionalController {

    private final ProfessionalService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<Page<ProfessionalResponseDTO>> findAll(Pageable pageable, @RequestParam(name = "name", defaultValue = "") String name) {
        var result = service.findAll(pageable, name);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/me")
    public ResponseEntity<ProfessionalResponseDTO> findProfessionalLogged() {
        var result = service.getMe();
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<ProfessionalResponseDTO> patch(@PathVariable Long id, @Valid @RequestBody ProfessionalPatchRequestDTO dto) {
        var result = service.patch(id, dto);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ProfessionalResponseDTO> deactivate(@PathVariable Long id) {
        var result = service.deactivate(id);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/activate")
    public ResponseEntity<ProfessionalResponseDTO> activate(@PathVariable Long id) {
        var result = service.activate(id);
        return ResponseEntity.ok(result);
    }
}