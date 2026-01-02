package com.henrique.majesty.controller;

import com.henrique.majesty.dto.ClientDto;
import com.henrique.majesty.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody @Valid ClientDto clientDto) {
        ClientDto client = clientService.save(clientDto);
        return ResponseEntity.status(201).body(client);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDto>> list(@PageableDefault Pageable pageable) {
        Page<ClientDto> clients = clientService.list(pageable);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        ClientDto client = clientService.get(id);
        return ResponseEntity.ok(client);
    }
}
