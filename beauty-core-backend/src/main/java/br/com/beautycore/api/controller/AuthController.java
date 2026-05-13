package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.request.LoginRequestDTO;
import br.com.beautycore.api.dto.request.RegisterRequestDTO;
import br.com.beautycore.api.dto.response.ProfessionalResponseDTO;
import br.com.beautycore.api.dto.response.TokenResponseDTO;
import br.com.beautycore.api.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping(value = "/register")
    public ResponseEntity<ProfessionalResponseDTO> register(@Valid @RequestBody RegisterRequestDTO dto) {
        var result = service.register(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenResponseDTO> register(@Valid @RequestBody LoginRequestDTO dto) {
        var token = service.login(dto);
        return ResponseEntity.ok(token);

    }
}