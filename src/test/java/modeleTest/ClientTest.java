package modeleTest;

import model.Client;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    @Test
    public void testClientCreation() {
        // Création d'une instance de Client
        String nom = "Dupont";
        String email = "dupont@example.com";
        Client client = new Client(nom, email);

        // Vérification que les informations sont correctement enregistrées
        assertNotNull(client.getId(), "L'ID ne doit pas être nul");
        assertEquals(nom, client.getNom(), "Le nom doit correspondre au nom fourni");
        assertEquals(email, client.getEmail(), "L'email doit correspondre à l'email fourni");

        // Affichage des informations du client
        System.out.println("Client ID: " + client.getId());
        System.out.println("Nom: " + client.getNom());
        System.out.println("Email: " + client.getEmail());
    }
}
