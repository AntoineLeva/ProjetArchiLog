package service;

import dto.ClientDTO;
import model.Client;
import repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(ClientDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public ClientDTO getClientById(UUID id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client ID " + id + " not found"));
        return ClientDTO.fromEntity(client);
    }

    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = clientDTO.toEntity();
        client.setId(UUID.randomUUID());
        Client savedClient = clientRepository.save(client);
        return ClientDTO.fromEntity(savedClient);
    }

    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }
}
