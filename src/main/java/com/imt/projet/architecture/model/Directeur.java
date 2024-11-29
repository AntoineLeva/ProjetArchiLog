package com.imt.projet.architecture.model;

import java.util.List;
import java.util.UUID;

public class Directeur {
    private UUID id;
    private String nom;
    private String tel;
    private List<Conseiller> conseillers;

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
