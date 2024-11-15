
package com.imt.projet.architecture.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "clients")
public class Client implements CompteComponent {

    @Id
    private UUID id;
    private String nom;
    private String email;
    private UUID conseillerId;

    public Client(String nom, String email, UUID conseillerId) {
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.email = email;
        this.conseillerId = conseillerId;
    }

    public Client() {}

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getConseillerId() {
        return conseillerId;
    }

    public void setConseillerId(UUID conseillerId) {
        this.conseillerId = conseillerId;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Client: " + nom + " (Email: " + email + ")");
    }
}
