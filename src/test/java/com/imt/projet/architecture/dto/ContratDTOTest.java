package com.imt.projet.architecture.dto;

import com.imt.projet.architecture.model.Contrat;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ContratDTOTest {

    @Test
    void testFromEntity() {
        // Préparation des données
        UUID contratId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();

        Contrat contrat = new Contrat();
        contrat.setId(contratId);
        contrat.setNom("Test Contrat");
        contrat.setDate(LocalDateTime.now());
        contrat.setClientId(clientId);

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(clientId);
        clientDTO.setNom("Test Nom");
        clientDTO.setEmail("test@example.com");

        // Exécution de la méthode
        ContratDTO contratDTO = ContratDTO.fromEntity(contrat, clientDTO);

        // Assertions
        assertNotNull(contratDTO);
        assertEquals(contrat.getId(), contratDTO.getId());
        assertEquals(contrat.getNom(), contratDTO.getNom());
        assertEquals(contrat.getDate(), contratDTO.getDate());
        assertEquals(contrat.getClientId(), contratDTO.getClientId());
        assertEquals(clientDTO, contratDTO.getClient());
    }

    @Test
    void testFromEntity_NullContrat() {
        // Exécution de la méthode avec un contrat null
        ContratDTO contratDTO = ContratDTO.fromEntity(null, null);

        // Assertion
        assertNull(contratDTO);
    }

    @Test
    void testToEntity() {
        // Préparation des données
        UUID contratId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();

        ContratDTO contratDTO = new ContratDTO();
        contratDTO.setId(contratId);
        contratDTO.setNom("Test Contrat");
        contratDTO.setDate(LocalDateTime.now());
        contratDTO.setClientId(clientId);

        // Exécution de la méthode
        Contrat contrat = contratDTO.toEntity();

        // Assertions
        assertNotNull(contrat);
        assertEquals(contratDTO.getId(), contrat.getId());
        assertEquals(contratDTO.getNom(), contrat.getNom());
        assertEquals(contratDTO.getDate(), contrat.getDate());
        assertEquals(contratDTO.getClientId(), contrat.getClientId());
    }

    @Test
    void testFromEntity_WithNullClientDTO() {
        // Préparation des données
        UUID contratId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();

        Contrat contrat = new Contrat();
        contrat.setId(contratId);
        contrat.setNom("Test Contrat");
        contrat.setDate(LocalDateTime.now());
        contrat.setClientId(clientId);

        // Exécution de la méthode avec un ClientDTO null
        ContratDTO contratDTO = ContratDTO.fromEntity(contrat, null);

        // Assertions
        assertNotNull(contratDTO);
        assertEquals(contrat.getId(), contratDTO.getId());
        assertEquals(contrat.getNom(), contratDTO.getNom());
        assertEquals(contrat.getDate(), contratDTO.getDate());
        assertEquals(contrat.getClientId(), contratDTO.getClientId());
        assertNull(contratDTO.getClient()); // Le ClientDTO est null
    }
}
