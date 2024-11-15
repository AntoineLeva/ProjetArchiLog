
package com.imt.projet.architecture.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "conseillers")
public class Conseiller implements CompteComponent {



    @Id
    private UUID id;
    private String nom;



    private String email;
    private List<UUID> clientsIds;

    public Conseiller(String nom, String email) {
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.email = email;
        this.clientsIds = new ArrayList<>();
    }

    public Conseiller() {}

    public void ajouterClient(UUID clientId) {
        if (!clientsIds.contains(clientId)) {
            clientsIds.add(clientId);
        }
    }

    public void retirerClient(UUID clientId) {
        clientsIds.remove(clientId);
    }

    public List<UUID> getClientsIds() {
        return clientsIds;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setClientsIds(List<UUID> clientsIds) {
        this.clientsIds = clientsIds;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Conseiller: " + nom + " (Email: " + email + ")");
        System.out.println("Clients associés:");
        for (UUID clientId : clientsIds) {
            System.out.println(" - Client ID: " + clientId);
        }
    }
}
