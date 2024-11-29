package com.imt.projet.architecture.dto;

import com.imt.projet.architecture.model.Contrat;

import java.time.LocalDateTime;
import java.util.UUID;

public class ContratDTO {

    private UUID id;
    private String nom;
    private LocalDateTime date;
    private UUID clientId;
    private ClientDTO client; // Informations du client

    // Getters et Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public UUID getClientId() { return clientId; }
    public void setClientId(UUID clientId) { this.clientId = clientId; }

    public ClientDTO getClient() { return client; }
    public void setClient(ClientDTO client) { this.client = client; }

    // Conversion Entité <-> DTO
    public static ContratDTO fromEntity(Contrat contrat, ClientDTO clientDTO) {
        if (contrat == null) {
            return null; // Gérer le cas où l'entité Contrat est null
        }
        ContratDTO dto = new ContratDTO();
        dto.setId(contrat.getId());
        dto.setNom(contrat.getNom());
        dto.setDate(contrat.getDate());
        dto.setClientId(contrat.getClientId());
        dto.setClient(clientDTO); // Assigner les informations du client
        return dto;
    }

    public Contrat toEntity() {
        Contrat contrat = new Contrat();
        contrat.setId(this.id);
        contrat.setNom(this.nom);
        contrat.setDate(this.date);
        contrat.setClientId(this.clientId);
        return contrat;
    }
}
