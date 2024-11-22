package com.imt.projet.architecture.model;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class CompteUsers {

    @Id
    private UUID id;     // Identifiant unique pour chaque utilisateur
    private String nom;  // Nom de l'utilisateur
    private String email; // Email de l'utilisateur

    // Constructeur par défaut
    public CompteUsers() {
        this.id = UUID.randomUUID(); // Génère un UUID unique pour chaque instance
    }

    // Getters et Setters
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
}
