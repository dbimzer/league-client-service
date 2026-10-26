package com.example.client_service.service;

import com.example.client_service.entity.Client;
import com.example.client_service.enums.ClientStatus;
import com.example.client_service.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(Client client) {
        client.setId(null);

        if (client.getFullName() == null || client.getFullName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client full_name is required");
        }

        if (client.getStatus() == null) {
            client.setStatus(ClientStatus.ACTIVE);
        }

        return clientRepository.save(client);
    }

    public Client getClientById(Long id) {

        Optional<Client> client = clientRepository.findById(id);

        if (client.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with id: " + id);
        }

        return client.get();
    }

    public Client updateClient(Long id, Client updatedClient) {

        Client client = getClientById(id);

        if (updatedClient.getFullName() == null || updatedClient.getFullName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client full_name is required");
        }

        if (updatedClient.getStatus() != null) {
            client.setStatus(updatedClient.getStatus());
        }

        client.setFullName(updatedClient.getFullName());
        client.setGender(updatedClient.getGender());

        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {

        Client client = getClientById(id);

        clientRepository.delete(client);
    }
}
