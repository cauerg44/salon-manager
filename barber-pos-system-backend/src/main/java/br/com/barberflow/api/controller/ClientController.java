package br.com.barberflow.api.controller;

import br.com.barberflow.api.dto.response.ClientResponseDTO;
import br.com.barberflow.api.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<Page<ClientResponseDTO>> findAllClients(@RequestParam String name, Pageable pageable) {
        var result = service.findAll(name, pageable);
        return ResponseEntity.ok(result);
    }
}