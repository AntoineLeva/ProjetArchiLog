package com.imt.projet.architecture.controller;

import com.imt.projet.architecture.dto.ContratDTO;
import com.imt.projet.architecture.service.ContratService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ContratControllerTest {

    @Mock
    private ContratService contratService;

    @InjectMocks
    private ContratController contratController;

    private ContratDTO sampleContratDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleContratDTO = new ContratDTO();
        sampleContratDTO.setId(UUID.randomUUID());
        sampleContratDTO.setNom("Test Contrat");
        sampleContratDTO.setDate(LocalDateTime.now());
        sampleContratDTO.setClientId(UUID.randomUUID());
    }

    @Test
    void testGetAllContrats() {
        when(contratService.getAllContrats()).thenReturn(List.of(sampleContratDTO));

        List<ContratDTO> contrats = contratController.getAllContrats();

        assertEquals(1, contrats.size());
        assertEquals(sampleContratDTO.getNom(), contrats.get(0).getNom());
        verify(contratService, times(1)).getAllContrats();
    }

    @Test
    void testGetContratById() {
        UUID id = sampleContratDTO.getId();
        when(contratService.getContratById(id)).thenReturn(sampleContratDTO);

        ContratDTO contrat = contratController.getContratById(id);

        assertEquals(sampleContratDTO.getNom(), contrat.getNom());
        verify(contratService, times(1)).getContratById(id);
    }

    @Test
    void testCreateContrat() {
        when(contratService.createContrat(sampleContratDTO)).thenReturn(sampleContratDTO);

        ContratDTO createdContrat = contratController.createContrat(sampleContratDTO);

        assertEquals(sampleContratDTO.getNom(), createdContrat.getNom());
        verify(contratService, times(1)).createContrat(sampleContratDTO);
    }

    @Test
    void testUpdateContrat() {
        UUID id = sampleContratDTO.getId();
        when(contratService.updateContrat(id, sampleContratDTO)).thenReturn(sampleContratDTO);

        ContratDTO updatedContrat = contratController.updateContrat(id, sampleContratDTO);

        assertEquals(sampleContratDTO.getNom(), updatedContrat.getNom());
        verify(contratService, times(1)).updateContrat(id, sampleContratDTO);
    }

    @Test
    void testDeleteContrat() {
        UUID id = sampleContratDTO.getId();

        contratController.deleteContrat(id);

        verify(contratService, times(1)).deleteContrat(id);
    }
}
