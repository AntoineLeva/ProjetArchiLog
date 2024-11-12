package com.imt.projet.architecture.controller;

import com.imt.projet.architecture.controller.ClientController;
import com.imt.projet.architecture.dto.ClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.imt.projet.architecture.service.ClientService;
import com.imt.projet.architecture.service.ContratService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @Mock
    private ContratService contratService;

    @InjectMocks
    private ClientController clientController;

    private ClientDTO sampleClientDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleClientDTO = new ClientDTO();
        sampleClientDTO.setId(UUID.randomUUID());
        sampleClientDTO.setNom("Test Nom");
        sampleClientDTO.setEmail("test@example.com");
    }

    @Test
    void testGetAllClients() {
        when(clientService.getAllClients()).thenReturn(List.of(sampleClientDTO));

        List<ClientDTO> clients = clientController.getAllClients();

        assertEquals(1, clients.size());
        assertEquals(sampleClientDTO.getNom(), clients.get(0).getNom());
        verify(clientService, times(1)).getAllClients();
    }

    @Test
    void testGetClientById() {
        UUID id = sampleClientDTO.getId();
        when(clientService.getClientById(id)).thenReturn(sampleClientDTO);

        ClientDTO client = clientController.getClientById(id);

        assertEquals(sampleClientDTO.getNom(), client.getNom());
        verify(clientService, times(1)).getClientById(id);
    }

    @Test
    void testCreateClient() {
        when(clientService.createClient(sampleClientDTO)).thenReturn(sampleClientDTO);

        ClientDTO createdClient = clientController.createClient(sampleClientDTO);

        assertEquals(sampleClientDTO.getNom(), createdClient.getNom());
        verify(clientService, times(1)).createClient(sampleClientDTO);
    }

    @Test
    void testDeleteClient() {
        UUID id = sampleClientDTO.getId();

        clientController.deleteClient(id);

        verify(clientService, times(1)).deleteClient(id);
        //verify(contratService, times(1)).deleteClientContrats(id);
    }
}
