package br.com.barberflow.api.controller;

import br.com.barberflow.api.dto.response.BarberResponseDTO;
import br.com.barberflow.api.services.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/barbers")
public class BarberController {

    @Autowired
    private BarberService service;

    public ResponseEntity<List<BarberResponseDTO>> findAllBarbers() {
        var result = service.findAll();
        return ResponseEntity.ok(result);
    }
}