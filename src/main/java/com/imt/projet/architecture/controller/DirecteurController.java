package com.imt.projet.architecture.controller;

import com.imt.projet.architecture.dto.DirecteurDTO;
import com.imt.projet.architecture.dto.ConseillerDTO;
import com.imt.projet.architecture.service.DirecteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/directeurs")
public class DirecteurController {

    @Autowired
    private DirecteurService directeurService;

    @GetMapping
    public List<DirecteurDTO> getAllDirecteurs() {
        return directeurService.getAllDirecteurs();
    }

    @PostMapping
    public DirecteurDTO addDirecteur(@RequestBody DirecteurDTO directeurDTO) {
        return directeurService.addDirecteur(directeurDTO);
    }

    @PostMapping("/{directeurId}/ajouter-conseiller")
    public void ajouterConseiller(@PathVariable UUID directeurId, @RequestBody ConseillerDTO conseillerDTO) {
        directeurService.ajouterConseiller(directeurId, conseillerDTO);
    }

    @DeleteMapping("/{directeurId}/retirer-conseiller/{conseillerId}")
    public void retirerConseiller(@PathVariable UUID directeurId, @PathVariable UUID conseillerId) {
        directeurService.retirerConseiller(directeurId, conseillerId);
    }

    @GetMapping("/{directeurId}/conseillers")
    public List<ConseillerDTO> getConseillers(@PathVariable UUID directeurId) {
        return directeurService.getConseillers(directeurId);
    }
}
