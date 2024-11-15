
package com.imt.projet.architecture.dto;

import com.imt.projet.architecture.model.Conseiller;

import java.util.List;
import java.util.UUID;

public class ConseillerDTO {
    private UUID id;
    private String nom;
    private String email;
    private List<UUID> clientsIds;

    public ConseillerDTO() {}

    public ConseillerDTO(UUID id, String nom, String email, List<UUID> clientsIds) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.clientsIds = clientsIds;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UUID> getClientsIds() {
        return clientsIds;
    }

    public void setClientsIds(List<UUID> clientsIds) {
        this.clientsIds = clientsIds;
    }

    public static ConseillerDTO fromEntity(Conseiller conseiller) {
        return new ConseillerDTO(conseiller.getId(), conseiller.getNom(), conseiller.getEmail(), conseiller.getClientsIds());
    }

    public Conseiller toEntity() {
        Conseiller conseiller = new Conseiller();
        conseiller.setId(this.id);
        conseiller.setNom(this.nom);
        conseiller.setEmail(this.email);
        conseiller.setClientsIds(this.clientsIds);
        return conseiller;
    }
}
