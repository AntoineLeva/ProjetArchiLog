package com.imt.projet.architecture.repository;

import com.imt.projet.architecture.model.Contrat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContratRepository extends MongoRepository<Contrat, UUID> {

    List<Contrat> findByClientId(UUID clientId);
}
