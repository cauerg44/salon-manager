package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.request.ClientCreateRequestDTO;
import br.com.beautycore.api.dto.request.ClientPatchRequestDTO;
import br.com.beautycore.api.dto.response.ClientResponseDTO;
import br.com.beautycore.api.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "v1/clients")
@RequiredArgsConstructor
@Tag(
name = "Clients",
description = "Endpoints for managing clients, including registration, retrieval, updates, deletion."
)
public class ClientController {

    private final ClientService service;

    @Operation(
            summary = "List clients",
            description = "Returns a paginated list of clients. Results can be filtered by name and sorted using pageable parameters.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ClientResponseDTO>> findAllPaged(@RequestParam(name = "name", defaultValue = "") String name, Pageable pageable) {
        var result = service.findAll(name, pageable);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "List clients without appointments",
            description = "Returns all clients who are not currently associated with any appointment.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/not-in-appointment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientResponseDTO>> findAllClientsNotInAppointment() {
        var result = service.findAllClientsNotInAppointment();
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Get client by ID",
            description = "Returns the details of a specific client based on the provided identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id) {
        var result = service.findById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Create client",
            description = "Creates a new client and returns the created resource.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "422", description = "Unprocessable entity")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponseDTO> create(@Valid @RequestBody ClientCreateRequestDTO dto) {
        var result = service.save(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();

        return ResponseEntity.created(uri).body(result);
    }

    @Operation(
            summary = "Update client",
            description = "Updates one or more fields of an existing client using partial update semantics.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "422", description = "Unprocessable entity")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponseDTO> patch(@PathVariable Long id, @Valid @RequestBody ClientPatchRequestDTO dto) {
        var result = service.patch(id, dto);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Delete client",
            description = "Permanently removes a client from the system.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No content"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}