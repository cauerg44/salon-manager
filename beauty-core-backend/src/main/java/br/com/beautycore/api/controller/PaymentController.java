package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.request.PaymentCreateRequestDTO;
import br.com.beautycore.api.dto.response.PaymentResponseDTO;
import br.com.beautycore.api.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/{appointmentId}")
    public ResponseEntity<PaymentResponseDTO> createPayment(@PathVariable Long appointmentId, @Valid @RequestBody PaymentCreateRequestDTO dto) {
        var result = service.createPayment(appointmentId, dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();

        return ResponseEntity.created(uri).body(result);
    }
}