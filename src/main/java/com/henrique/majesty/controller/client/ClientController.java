package com.henrique.majesty.controller.client;

import com.henrique.majesty.dto.client.ClientDetailsDto;
import com.henrique.majesty.dto.client.ClientDto;
import com.henrique.majesty.service.client.ClientService;
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
    public ResponseEntity<ClientDetailsDto> createClient(@RequestBody @Valid ClientDto clientDto) {
        ClientDetailsDto client = clientService.save(clientDto);
        return ResponseEntity.status(201).body(client);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDetailsDto>> list(@PageableDefault Pageable pageable) {
        Page<ClientDetailsDto> clients = clientService.list(pageable);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDetailsDto> findById(@PathVariable Long id) {
        ClientDetailsDto client = clientService.get(id);
        return ResponseEntity.ok(client);
    }
}
