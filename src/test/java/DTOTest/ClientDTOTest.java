package DTOTest;

import dto.ClientDTO;
import model.Client;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClientDTOTest {

    @Test
    void testFromEntity() {
        UUID id = UUID.randomUUID();
        Client client = new Client("Test Nom", "test@example.com");
        client.setId(id);

        ClientDTO clientDTO = ClientDTO.fromEntity(client);

        assertEquals(client.getId(), clientDTO.getId());
        assertEquals(client.getNom(), clientDTO.getNom());
        assertEquals(client.getEmail(), clientDTO.getEmail());
    }

    @Test
    void testToEntity() {
        UUID id = UUID.randomUUID();
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(id);
        clientDTO.setNom("Test Nom");
        clientDTO.setEmail("test@example.com");

        Client client = clientDTO.toEntity();

        assertEquals(clientDTO.getId(), client.getId());
        assertEquals(clientDTO.getNom(), client.getNom());
        assertEquals(clientDTO.getEmail(), client.getEmail());
    }

    @Test
    void testFromEntity_NullClient() {
        ClientDTO clientDTO = ClientDTO.fromEntity(null);

        assertNull(clientDTO);
    }

    @Test
    void testToEntity_NullFields() {
        ClientDTO clientDTO = new ClientDTO();

        Client client = clientDTO.toEntity();

        assertNull(client.getId());
        assertNull(client.getNom());
        assertNull(client.getEmail());
    }
}

