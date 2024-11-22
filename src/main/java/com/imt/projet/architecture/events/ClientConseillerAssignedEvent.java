package com.imt.projet.architecture.events;

import java.util.UUID;

public class ClientConseillerAssignedEvent {
    private final UUID clientId;

    public ClientConseillerAssignedEvent(UUID clientId) {
        this.clientId = clientId;
    }

    public UUID getClientId() {
        return clientId;
    }
}
