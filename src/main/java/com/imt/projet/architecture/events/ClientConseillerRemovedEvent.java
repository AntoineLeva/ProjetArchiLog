package com.imt.projet.architecture.events;

import java.util.UUID;

public class ClientConseillerRemovedEvent {
    private final UUID clientId;
    private final UUID conseillerId;

    public ClientConseillerRemovedEvent(UUID clientId, UUID conseillerId) {
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
