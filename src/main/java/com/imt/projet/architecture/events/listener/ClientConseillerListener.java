package com.imt.projet.architecture.events.listener;

import com.imt.projet.architecture.events.ClientConseillerRemovedEvent;
import com.imt.projet.architecture.model.Conseiller;
import com.imt.projet.architecture.repository.ConseillerRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

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
}
