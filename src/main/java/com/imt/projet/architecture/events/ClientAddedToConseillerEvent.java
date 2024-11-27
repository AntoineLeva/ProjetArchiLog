package com.imt.projet.architecture.events;


import java.util.UUID;

public class ClientAddedToConseillerEvent {

    private final UUID clientId;
    private final UUID conseillerId;

    public ClientAddedToConseillerEvent(UUID clientId, UUID conseillerId) {
        this.clientId = clientId;
        this.conseillerId = conseillerId;
    }

    public UUID getClientId() {
        return clientId;
    }

    public UUID getConseillerId() {
        return conseillerId;
    }
}
