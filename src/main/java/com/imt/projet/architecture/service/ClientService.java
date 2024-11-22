package com.imt.projet.architecture.service;

import com.imt.projet.architecture.dto.ClientDTO;
import com.imt.projet.architecture.events.ClientConseillerAssignedEvent;
import com.imt.projet.architecture.events.ClientConseillerRemovedEvent;
import com.imt.projet.architecture.model.Client;
import com.imt.projet.architecture.repository.ClientRepository;
import com.imt.projet.architecture.repository.ConseillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ConseillerRepository conseillerRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher; // Permet de publier des événements

    // Récupérer tous les clients
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

    // Récupérer un client par son ID
    public ClientDTO getClientById(UUID id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable"));
        return ClientDTO.fromEntity(client, null); // Ajoutez le nom du conseiller si nécessaire
    }

    // Créer un client
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = clientDTO.toEntity();
        client.setId(UUID.randomUUID());
        return ClientDTO.fromEntity(clientRepository.save(client), null);
    }

    // Supprimer le conseiller du client, publier deux événements pour gérer la suppression et la réaffectation
    public void retirerConseillerEtReassigner(UUID clientId) {
        // Étape 1 : Récupérer le client
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable"));

        UUID conseillerId = client.getConseillerId(); // Identifiant du conseiller actuel

        // Étape 2 : Supprimer le conseiller associé
        if (conseillerId != null) {
            client.setConseillerId(null);
            clientRepository.save(client);

            // Publier un événement pour signaler que le client doit être retiré de la liste du conseiller
            eventPublisher.publishEvent(new ClientConseillerRemovedEvent(clientId, conseillerId));
            System.out.println("Événement publié : suppression du client " + clientId + " du conseiller " + conseillerId);
        }

        // Étape 3 : Publier un événement pour assigner un conseiller aléatoire
        eventPublisher.publishEvent(new ClientConseillerAssignedEvent(clientId));
        System.out.println("Événement publié : assignation d'un nouveau conseiller au client " + clientId);
    }

    // Supprimer un client
    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }

    // Affecter un conseiller spécifique à un client
    public void affecterConseiller(UUID clientId, UUID conseillerId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable"));
        client.setConseillerId(conseillerId);
        clientRepository.save(client);
    }
}
