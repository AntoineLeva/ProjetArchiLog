package com.imt.projet.architecture.dto;

import com.imt.projet.architecture.model.Conseiller;
import com.imt.projet.architecture.model.Directeur;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DirecteurDTO {
    private UUID id;
    private String nom;
    private String tel;
    private List<ConseillerDTO> conseillers;

    public DirecteurDTO() {}

    public DirecteurDTO(UUID id, String nom, String tel, List<ConseillerDTO> conseillers) {
        this.id = id;
        this.nom = nom;
        this.tel = tel;
        this.conseillers = conseillers;
    }

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

    public List<ConseillerDTO> getConseillers() {
        return conseillers;
    }

    public void setConseillers(List<ConseillerDTO> conseillers) {
        this.conseillers = conseillers;
    }

    public static DirecteurDTO fromEntity(Directeur directeur) {
        List<ConseillerDTO> conseillerDTOs = directeur.getConseillers().stream()
                .map(ConseillerDTO::fromEntity)
                .collect(Collectors.toList());
        return new DirecteurDTO(directeur.getId(), directeur.getNom(), directeur.getTel(), conseillerDTOs);
    }

    public Directeur toEntity() {
        Directeur directeur = new Directeur();
        directeur.setId(this.id);
        directeur.setNom(this.nom);
        directeur.setTel(this.tel);
        List<Conseiller> conseillers = this.conseillers.stream()
                .map(ConseillerDTO::toEntity)
                .collect(Collectors.toList());
        directeur.setConseillers(conseillers);
        return directeur;
    }
}
