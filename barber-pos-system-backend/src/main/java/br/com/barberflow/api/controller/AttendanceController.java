package br.com.barberflow.api.controller;

import br.com.barberflow.api.dto.request.AttendanceInsertRequestDTO;
import br.com.barberflow.api.dto.response.AttendanceResponseDTO;
import br.com.barberflow.api.services.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/v1/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    @PostMapping
    public ResponseEntity<AttendanceResponseDTO> createAttendance(@Valid @RequestBody AttendanceInsertRequestDTO dto) {
        var result = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.id()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PatchMapping(value = "/{id}/start")
    public ResponseEntity<AttendanceResponseDTO> startAttendance(@PathVariable Long id) {
        var result = service.start(id);
        return ResponseEntity.ok(result);
    }
}