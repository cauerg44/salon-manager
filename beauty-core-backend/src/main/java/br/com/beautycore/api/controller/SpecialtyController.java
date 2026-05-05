package br.com.beautycore.api.controller;

import br.com.beautycore.api.dto.response.SpecialtyResponseDTO;
import br.com.beautycore.api.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/specializations")
public class SpecialtyController {

    @Autowired
    private SpecialtyService service;

    @GetMapping
    public ResponseEntity<List<SpecialtyResponseDTO>> findAllSpecializations() {
        var result = service.findAll();
        return ResponseEntity.ok(result);
    }
}