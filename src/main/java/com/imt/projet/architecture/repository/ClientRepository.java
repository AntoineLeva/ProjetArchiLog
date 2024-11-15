package com.imt.projet.architecture.repository;

import com.imt.projet.architecture.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends MongoRepository<Client, UUID> {

    @Autowired
    ClientRepository clientRepository = null;

    public default void initialiserConseillerId() {
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            if (client.getConseillerId() == null) {
                client.setConseillerId(null); // S'assurer que le champ existe
                clientRepository.save(client);
            }
        }
    }

    
}



