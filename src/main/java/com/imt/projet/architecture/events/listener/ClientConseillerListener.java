package com.imt.projet.architecture.events.listener;

import com.imt.projet.architecture.events.ClientAddedToConseillerEvent;
import com.imt.projet.architecture.events.ClientConseillerRemovedEvent;
import com.imt.projet.architecture.model.Client;
import com.imt.projet.architecture.model.Conseiller;
import com.imt.projet.architecture.repository.ConseillerRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static com.imt.projet.architecture.repository.ClientRepository.clientRepository;

@Component
public class ClientConseillerListener {

    private final ConseillerRepository conseillerRepository;

    public ClientConseillerListener(ConseillerRepository conseillerRepository) {
        this.conseillerRepository = conseillerRepository;
    }

    @EventListener
    public void handleClientConseillerRemovedEvent(ClientConseillerRemovedEvent event) {
        UUID conseillerId = event.getConseillerId();
        UUID clientId = event.getClientId();

        Optional<Conseiller> conseillerOpt = conseillerRepository.findById(conseillerId);

        if (conseillerOpt.isPresent()) {
            Conseiller conseiller = conseillerOpt.get();
            conseiller.retirerClient(clientId);
            conseillerRepository.save(conseiller);
            System.out.println("Client " + clientId + " retiré de la liste du conseiller " + conseillerId);
        }
    }




    // Gestion de l'événement : Ajouter un client à la liste des clients d'un conseiller
    @EventListener
    public void handleClientAddedToConseillerEvent(ClientAddedToConseillerEvent event) {
        UUID clientId = event.getClientId();
        UUID conseillerId = event.getConseillerId();

        Optional<Conseiller> conseillerOpt = conseillerRepository.findById(conseillerId);
        Optional<Client> clientOpt = clientRepository.findById(clientId);

        if (conseillerOpt.isPresent() && clientOpt.isPresent()) {
            Conseiller conseiller = conseillerOpt.get();
            Client client = clientOpt.get();

            // Ajouter le client à la liste des clients du conseiller
            conseiller.ajouterClient(client.getId());
            conseillerRepository.save(conseiller);

            System.out.println("Client " + client.getNom() + " ajouté à la liste du conseiller " + conseiller.getNom());
        } else {
            System.err.println("Erreur : Client ou conseiller introuvable.");
        }
    }
}
