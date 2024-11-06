package ServiceTest;

import dto.ClientDTO;
import model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.ClientRepository;
import service.ClientService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client sampleClient;
    private ClientDTO sampleClientDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        UUID id = UUID.randomUUID();
        sampleClient = new Client("Test Nom", "test@example.com");
        sampleClient.setId(id);

        sampleClientDTO = ClientDTO.fromEntity(sampleClient);
    }

    @Test
    void testGetAllClients() {
        when(clientRepository.findAll()).thenReturn(List.of(sampleClient));

        var clients = clientService.getAllClients();

        assertEquals(1, clients.size());
        assertEquals(sampleClientDTO.getNom(), clients.get(0).getNom());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testGetClientById() {
        UUID id = sampleClient.getId();
        when(clientRepository.findById(id)).thenReturn(Optional.of(sampleClient));

        ClientDTO client = clientService.getClientById(id);

        assertEquals(sampleClientDTO.getNom(), client.getNom());
        verify(clientRepository, times(1)).findById(id);
    }

    @Test
    void testCreateClient() {
        when(clientRepository.save(any(Client.class))).thenReturn(sampleClient);

        ClientDTO createdClient = clientService.createClient(sampleClientDTO);

        assertEquals(sampleClientDTO.getNom(), createdClient.getNom());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testDeleteClient() {
        UUID id = sampleClient.getId();

        clientService.deleteClient(id);

        verify(clientRepository, times(1)).deleteById(id);
    }
}
