package com.imt.projet.architecture.events.listener;

import com.imt.projet.architecture.events.ClientConseillerAssignedEvent;
import com.imt.projet.architecture.model.Client;
import com.imt.projet.architecture.model.Conseiller;
import com.imt.projet.architecture.repository.ClientRepository;
import com.imt.projet.architecture.repository.ConseillerRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Component
public class ClientAssignmentListener {

    private final ClientRepository clientRepository;
    private final ConseillerRepository conseillerRepository;

    public ClientAssignmentListener(ClientRepository clientRepository, ConseillerRepository conseillerRepository) {
        this.clientRepository = clientRepository;
        this.conseillerRepository = conseillerRepository;
    }

    @EventListener
    public void handleClientConseillerAssignedEvent(ClientConseillerAssignedEvent event) {
        UUID clientId = event.getClientId();
        Optional<Client> clientOpt = clientRepository.findById(clientId);

        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            UUID conseillerRetireId = client.getConseillerId(); // ID du conseiller à exclure

            // Étape 1 : Récupérer tous les conseillers existants, sauf celui à exclure
            List<Conseiller> conseillers = conseillerRepository.findAll()
                    .stream()
                    .filter(conseiller -> !conseiller.getId().equals(conseillerRetireId)) // Exclure l'ID du conseiller retiré
                    .toList();

            if (!conseillers.isEmpty()) {
                // Étape 2 : Sélectionner un conseiller aléatoire
                Conseiller conseillerAleatoire = conseillers.get(new Random().nextInt(conseillers.size()));

                // Étape 3 : Assigner le conseiller au client
                client.setConseillerId(conseillerAleatoire.getId());

                // Étape 4 : Mettre à jour les relations
                conseillerAleatoire.ajouterClient(client.getId());
                conseillerRepository.save(conseillerAleatoire);
                clientRepository.save(client);

                System.out.println("Un conseiller aléatoire (" + conseillerAleatoire.getNom() + ") a été assigné au client " + clientId);
            } else {
                System.err.println("Aucun conseiller disponible pour assigner au client " + clientId + " après exclusion.");
            }
        } else {
            System.err.println("Client avec l'ID " + clientId + " introuvable.");
        }
    }
}

