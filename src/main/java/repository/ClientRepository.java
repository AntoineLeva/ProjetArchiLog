package repository;

import model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends MongoRepository<Client, UUID> {
    // MongoRepository fournit des méthodes CRUD basiques pour MongoDB,
    // vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
