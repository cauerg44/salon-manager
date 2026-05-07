package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.request.AppointmentCreateRequestDTO;
import br.com.beautycore.api.dto.request.AppointmentPatchRequestDTO;
import br.com.beautycore.api.dto.response.AppointmentResponseDTO;
import br.com.beautycore.api.services.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @GetMapping
    public ResponseEntity<Page<AppointmentResponseDTO>> findAllAppointments(Pageable pageable) {
        var result = service.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AppointmentResponseDTO> findById(@PathVariable Long id) {
        var result = service.findById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentCreateRequestDTO dto) {
        var result = service.createAppointment(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();

        return ResponseEntity.created(uri).body(result);
    }

    @PatchMapping(value = "/{id}/start")
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@PathVariable Long id) {
        var result = service.startAppointment(id);
        return ResponseEntity.ok(result);
    }

    @PatchMapping(value = "/{id}/services")
    public ResponseEntity<AppointmentResponseDTO> updateAppointmentServices(@PathVariable Long id, @Valid @RequestBody AppointmentPatchRequestDTO dto) {
        var result = service.updateAppointmentServices(id, dto);
        return ResponseEntity.ok(result);
    }
}
