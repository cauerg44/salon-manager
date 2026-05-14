package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.request.AppointmentCreateRequestDTO;
import br.com.beautycore.api.dto.request.AppointmentPatchRequestDTO;
import br.com.beautycore.api.dto.response.AppointmentResponseDTO;
import br.com.beautycore.api.services.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<Page<AppointmentResponseDTO>> findAllAppointments(Pageable pageable, @RequestParam Boolean status) {
        var result = service.findAllByStatus(pageable, status);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AppointmentResponseDTO> findById(@PathVariable Long id) {
        var result = service.findById(id);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentCreateRequestDTO dto) {
        var result = service.save(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();

        return ResponseEntity.created(uri).body(result);
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping(value = "/{id}/start")
    public ResponseEntity<AppointmentResponseDTO> startAppointment(@PathVariable Long id) {
        var result = service.start(id);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping(value = "/{id}/cancel")
    public ResponseEntity<AppointmentResponseDTO> cancelAppointment(@PathVariable Long id) {
        var result = service.cancel(id);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping(value = "/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentPatchRequestDTO dto) {
        var result = service.patch(id, dto);
        return ResponseEntity.ok(result);
    }
}
