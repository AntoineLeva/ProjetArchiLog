package com.imt.projet.architecture.repository;

import com.imt.projet.architecture.model.Conseiller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConseillerRepository extends MongoRepository<Conseiller, UUID> {
}
