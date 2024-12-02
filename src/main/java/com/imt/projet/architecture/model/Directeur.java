package com.imt.projet.architecture.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "directeurs")
public class Directeur {
    @Id
    private UUID id;
    private String nom;
    private String tel;
    private List<Conseiller> conseillers;

    public Directeur() {
        this.id = UUID.randomUUID();
        this.conseillers = new ArrayList<>();
    }

    public Directeur(UUID id, String nom, String tel, List<Conseiller> conseillers) {
        this.id = id != null ? id : UUID.randomUUID();
        this.nom = nom;
        this.tel = tel;
        this.conseillers = conseillers != null ? conseillers : new ArrayList<>();
    }

    // Getters et Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<Conseiller> getConseillers() {
        return conseillers;
    }

    public void setConseillers(List<Conseiller> conseillers) {
        this.conseillers = conseillers;
    }
}
