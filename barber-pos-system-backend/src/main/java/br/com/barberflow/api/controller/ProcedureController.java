package br.com.barberflow.api.controller;

import br.com.barberflow.api.dto.request.ProcedureInsertRequestDTO;
import br.com.barberflow.api.dto.request.ProcedurePatchRequestDTO;
import br.com.barberflow.api.dto.response.ProcedureResponseDTO;
import br.com.barberflow.api.services.ProcedureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<ProcedureResponseDTO> saveNewProcedure(@Valid @RequestBody ProcedureInsertRequestDTO dto) {
        var result = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.id()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ProcedureResponseDTO> updateProcedure(@PathVariable Long id, @Valid @RequestBody ProcedurePatchRequestDTO dto) {
        var result = service.patch(id, dto);
        return ResponseEntity.ok(result);
    }
}