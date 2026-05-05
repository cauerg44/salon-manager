package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.response.ProfessionalResponseDTO;
import br.com.beautycore.api.services.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/professionals")
public class ProfessionalController {

    @Autowired
    private ProfessionalService service;

    @GetMapping
    public ResponseEntity<List<ProfessionalResponseDTO>> findAll() {
        var result = service.findAll();
        return ResponseEntity.ok(result);
    }
}