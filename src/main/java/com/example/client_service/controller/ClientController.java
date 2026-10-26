package com.example.client_service.controller;

import com.example.client_service.entity.Client;
import com.example.client_service.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody @Valid Client client) {
        return clientService.createClient(client);
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody @Valid Client updatedClient) {
        return clientService.updateClient(id, updatedClient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
