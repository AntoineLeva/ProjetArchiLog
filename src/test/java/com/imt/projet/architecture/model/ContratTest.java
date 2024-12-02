package com.imt.projet.architecture.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ContratTest {

    @Test
    public void testContratCreation() {
        // Création d'une instance de Contrat
        UUID clientId = UUID.randomUUID();
        String nom = "Contrat Assurance";
        LocalDateTime date = LocalDateTime.now();
        Contrat contrat = new Contrat();
        contrat.setId(UUID.randomUUID());
        contrat.setNom(nom);
        contrat.setDate(date);
        contrat.setClientId(clientId);

        // Vérification que les informations sont correctement enregistrées
        assertNotNull(contrat.getId(), "L'ID du contrat ne doit pas être nul");
        assertEquals(nom, contrat.getNom(), "Le nom doit correspondre au nom fourni");
        assertEquals(date, contrat.getDate(), "La date doit correspondre à la date fournie");
        assertEquals(clientId, contrat.getClientId(), "Le clientId doit correspondre au clientId fourni");

        // Affichage des informations du contrat
        System.out.println("Contrat ID: " + contrat.getId());
        System.out.println("Nom: " + contrat.getNom());
        System.out.println("Date: " + contrat.getDate());
        System.out.println("Client ID: " + contrat.getClientId());
    }
}
