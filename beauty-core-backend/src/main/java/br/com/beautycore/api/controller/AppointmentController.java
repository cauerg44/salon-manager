package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.response.AppointmentResponseDTO;
import br.com.beautycore.api.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
