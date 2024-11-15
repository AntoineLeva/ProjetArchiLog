package com.imt.projet.architecture.dto;

import com.imt.projet.architecture.model.Client;
import java.util.UUID;

public class ClientDTO {

    private UUID id;
    private String nom;
    private String email;

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

    // Méthode de conversion d'un Client en ClientDTO
    public static ClientDTO fromEntity(Client client) {
        if (client == null) {
            return null;
        }
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setNom(client.getNom());
        clientDTO.setEmail(client.getEmail());
        return clientDTO;
    }

    // Méthode de conversion d'un ClientDTO en Client
    public Client toEntity() {
        Client client = new Client();
        client.setId(this.id);
        client.setNom(this.nom);
        client.setEmail(this.email);
        return client;
    }
}
