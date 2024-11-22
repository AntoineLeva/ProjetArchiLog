package com.imt.projet.architecture.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "conseillers")
public class Conseiller extends CompteUsers {

    private List<UUID> clientsIds; // Liste des identifiants des clients associés au conseiller

    // Constructeur avec paramètres
    public Conseiller(String nom, String email) {
        super(); // Appelle le constructeur de la classe mère pour initialiser `id`
        this.setNom(nom); // Défini `nom` via la méthode héritée de CompteUsers
        this.setEmail(email); // Défini `email` via la méthode héritée de CompteUsers
        this.clientsIds = new ArrayList<>(); // Initialise la liste des clients
    }

    // Constructeur par défaut
    public Conseiller() {
        super(); // Appelle le constructeur de la classe mère pour initialiser `id`
        this.clientsIds = new ArrayList<>();
    }

    // Ajoute un client à la liste
    public void ajouterClient(UUID clientId) {
        if (!clientsIds.contains(clientId)) {
            clientsIds.add(clientId);
        }
    }

    // Retire un client de la liste
    public void retirerClient(UUID clientId) {
        clientsIds.remove(clientId);
    }

    // Getter pour la liste des clients associés
    public List<UUID> getClientsIds() {
        return clientsIds;
    }

    // Setter pour la liste des clients associés
    public void setClientsIds(List<UUID> clientsIds) {
        this.clientsIds = clientsIds;
    }
}
