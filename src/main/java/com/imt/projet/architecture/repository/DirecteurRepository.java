package com.imt.projet.architecture.repository;

import com.imt.projet.architecture.model.Directeur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DirecteurRepository extends MongoRepository<Directeur, UUID> {
}
