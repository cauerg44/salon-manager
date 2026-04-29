package br.com.barberflow.api.controller;

import br.com.barberflow.api.dto.response.ProcedureResponseDTO;
import br.com.barberflow.api.services.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/procedures")
public class ProcedureController {

    @Autowired
    private ProcedureService service;

    @GetMapping
    public ResponseEntity<List<ProcedureResponseDTO>> findAllProcedures() {
        var result = service.findAll();
        return ResponseEntity.ok(result);
    }
}