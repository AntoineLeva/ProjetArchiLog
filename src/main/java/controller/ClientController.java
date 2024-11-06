package controller;

import dto.ClientDTO;
import model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ClientService;
import service.ContratService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ContratService contratService;

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable UUID id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        //contratService.deleteClientContrats(id); // Supprimer aussi les contrats liés
    }
}
