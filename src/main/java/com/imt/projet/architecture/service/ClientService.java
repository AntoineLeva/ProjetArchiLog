package com.imt.projet.architecture.service;

import com.imt.projet.architecture.dto.ClientDTO;
import com.imt.projet.architecture.model.Client;
import com.imt.projet.architecture.repository.ClientRepository;
import com.imt.projet.architecture.repository.ConseillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ConseillerRepository conseillerRepository; // Ajout pour accéder aux conseillers

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(client -> {
                    // Récupérer le nom du conseiller si conseillerId est non null
                    String nomConseiller = null;
                    if (client.getConseillerId() != null) {
                        nomConseiller = conseillerRepository.findById(client.getConseillerId())
                                .map(conseiller -> conseiller.getNom())
                                .orElse(null);
                    }
                    return ClientDTO.fromEntity(client, nomConseiller);
                })
                .collect(Collectors.toList());
    }

    public ClientDTO getClientById(UUID id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable"));
        return ClientDTO.fromEntity(client, null); // Ajoutez le nom du conseiller si nécessaire
    }

    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = clientDTO.toEntity();
        client.setId(UUID.randomUUID());
        return ClientDTO.fromEntity(clientRepository.save(client), null);
    }

    public void retirerConseiller(UUID clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable"));
        client.setConseillerId(null);
        clientRepository.save(client);
    }

    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }

    public void affecterConseiller(UUID clientId, UUID conseillerId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable"));
        client.setConseillerId(conseillerId);
        clientRepository.save(client);
    }
}
