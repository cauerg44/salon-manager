package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.request.PaymentCreateRequestDTO;
import br.com.beautycore.api.dto.response.PaymentResponseDTO;
import br.com.beautycore.api.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
name = "Payments",
description = "Endpoints for processing and managing appointment payments."
)
public class PaymentController {

    private final PaymentService service;

    @Operation(
            summary = "Create payment",
            description = "Creates a payment for a specific appointment and returns the payment details after successful processing.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "404", description = "Appointment not found"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "422", description = "Unprocessable entity")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/{appointmentId}")
    public ResponseEntity<PaymentResponseDTO> addPayment(@PathVariable Long appointmentId, @Valid @RequestBody PaymentCreateRequestDTO dto) {
        var result = service.addPayment(appointmentId, dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();

        return ResponseEntity.created(uri).body(result);
    }
}