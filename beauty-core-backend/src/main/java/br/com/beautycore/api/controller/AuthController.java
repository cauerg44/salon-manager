package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.request.LoginRequestDTO;
import br.com.beautycore.api.dto.request.RegisterRequestDTO;
import br.com.beautycore.api.dto.response.ProfessionalResponseDTO;
import br.com.beautycore.api.dto.response.TokenResponseDTO;
import br.com.beautycore.api.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/v1/auth")
@RequiredArgsConstructor
@Tag(
name = "Authentication",
description = "Endpoints responsible for user authentication and account registration."
)
public class AuthController {

    private final AuthService service;

    @Operation(
            summary = "Register user",
            description = "Creates a new professional account and returns the registered user information.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "409", description = "User already exists"),
                    @ApiResponse(responseCode = "422", description = "Unprocessable entity")
            }
    )
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfessionalResponseDTO> register(@Valid @RequestBody RegisterRequestDTO dto) {
        var result = service.register(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();

        return ResponseEntity.created(uri).body(result);
    }

    @Operation(
            summary = "Authenticate user",
            description = "Authenticates a user using valid credentials and returns an access token.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "422", description = "Unprocessable entity")
            }
    )
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        var token = service.login(dto);
        return ResponseEntity.ok(token);
    }
}