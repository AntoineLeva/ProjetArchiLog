package com.imt.projet.architecture.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "contrats")
public class Contrat {

    @Id
    private UUID id;
    private String nom;
    private LocalDateTime date;
    private UUID clientId;

    public Contrat() {
        this.date = LocalDateTime.now(); // Définit la date et l'heure actuelles par défaut
    }

    public Contrat(String nom, UUID clientId) {
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.date = LocalDateTime.now(); // Définit la date et l'heure actuelles
        this.clientId = clientId;
    }

    // Getters et Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public UUID getClientId() { return clientId; }
    public void setClientId(UUID clientId) { this.clientId = clientId; }
}
