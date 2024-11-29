package com.imt.projet.architecture.repository;

import com.imt.projet.architecture.model.Directeur;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DirecteurRepository extends MongoRepository<Directeur, UUID> {
}
