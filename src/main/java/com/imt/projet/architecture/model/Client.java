package com.imt.projet.architecture.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "clients")
public class Client extends CompteUsers {

    private UUID conseillerId; // Identifiant du conseiller associé

    // Constructeur avec paramètres
    public Client(String nom, String email, UUID conseillerId) {
        super(); // Appelle le constructeur de la classe mère pour initialiser `id`
        this.setNom(nom); // Défini `nom` via la méthode héritée de CompteUsers
        this.setEmail(email); // Défini `email` via la méthode héritée de CompteUsers
        this.conseillerId = conseillerId; // Défini le conseiller
    }

    // Constructeur par défaut
    public Client() {
        super(); // Appelle le constructeur de la classe mère
    }

    // Getter et Setter pour `conseillerId`
    public UUID getConseillerId() {
        return conseillerId;
    }

    public void setConseillerId(UUID conseillerId) {
        this.conseillerId = conseillerId;
    }
}
