package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.request.ProfessionalPatchRequestDTO;
import br.com.beautycore.api.dto.response.ProfessionalResponseDTO;
import br.com.beautycore.api.services.ProfessionalService;
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

import java.util.List;

@RestController
@RequestMapping(value = "v1/professionals")
@RequiredArgsConstructor
@Tag(
name = "Professionals",
description = "Endpoints for managing professionals, including listing, searching, profile retrieval, status management, and profile updates."
)
public class ProfessionalController {

    private final ProfessionalService service;

    @Operation(
            summary = "List professionals",
            description = "Returns a paginated list of professionals. Results can be filtered by name and sorted using pageable parameters.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ProfessionalResponseDTO>> findAll(Pageable pageable, @RequestParam(name = "name", defaultValue = "") String name) {
        var result = service.findAll(pageable, name);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Filter professionals by status",
            description = "Returns all professionals filtered by their active status.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @GetMapping(value = "/is-active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProfessionalResponseDTO>> findAllByActivity(@RequestParam Boolean active) {
        var result = service.findAllByActivity(active);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Get professional by ID",
            description = "Returns the details of a specific professional based on the provided identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfessionalResponseDTO> findById(@PathVariable Long id) {
        var result = service.findById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Get current professional profile",
            description = "Returns the profile information of the authenticated professional.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfessionalResponseDTO> findProfessionalLogged() {
        var result = service.getMe();
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Update professional information",
            description = "Updates one or more fields of an existing professional using partial update semantics.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "422", description = "Unprocessable entity")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfessionalResponseDTO> patch(@PathVariable Long id, @Valid @RequestBody ProfessionalPatchRequestDTO dto) {
        var result = service.patch(id, dto);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Deactivate professional",
            description = "Marks a professional as inactive",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "/{id}/deactivate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfessionalResponseDTO> deactivate(@PathVariable Long id) {
        var result = service.deactivate(id);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Activate professional",
            description = "Marks a professional as active",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "/{id}/activate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfessionalResponseDTO> activate(@PathVariable Long id) {
        var result = service.activate(id);
        return ResponseEntity.ok(result);
    }
}