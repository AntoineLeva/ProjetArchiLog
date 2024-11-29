package com.imt.projet.architecture.service;

import com.imt.projet.architecture.dto.DirecteurDTO;
import com.imt.projet.architecture.dto.ConseillerDTO;
import com.imt.projet.architecture.model.Directeur;
import com.imt.projet.architecture.model.Conseiller;
import com.imt.projet.architecture.repository.DirecteurRepository;
import com.imt.projet.architecture.repository.ConseillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DirecteurService {

    @Autowired
    private DirecteurRepository directeurRepository;

    @Autowired
    private ConseillerRepository conseillerRepository;

    public List<DirecteurDTO> getAllDirecteurs() {
        return directeurRepository.findAll().stream()
                .map(DirecteurDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public DirecteurDTO addDirecteur(DirecteurDTO directeurDTO) {
        Directeur directeur = directeurDTO.toEntity();
        return DirecteurDTO.fromEntity(directeurRepository.save(directeur));
    }

    public void ajouterConseiller(UUID directeurId, ConseillerDTO conseillerDTO) {
        Directeur directeur = directeurRepository.findById(directeurId)
                .orElseThrow(() -> new IllegalArgumentException("Directeur introuvable"));
        Conseiller conseiller = conseillerDTO.toEntity();
        conseiller = conseillerRepository.save(conseiller); // Sauvegarde du conseiller
        directeur.getConseillers().add(conseiller);
        directeurRepository.save(directeur);
    }

    public void ajouterConseillers(UUID directeurId, List<ConseillerDTO> conseillersDTO) {
        Directeur directeur = directeurRepository.findById(directeurId)
                .orElseThrow(() -> new IllegalArgumentException("Directeur introuvable"));

        List<Conseiller> conseillers = conseillersDTO.stream() .map(ConseillerDTO::toEntity)
                .collect(Collectors.toList());
        // Sauvegarde chaque conseiller et assigne les ID générés
        List<Conseiller> savedConseillers = conseillerRepository.saveAll(conseillers);
        directeur.getConseillers().addAll(savedConseillers); directeurRepository.save(directeur); }

    public void retirerConseiller(UUID directeurId, UUID conseillerId) {
        Directeur directeur = directeurRepository.findById(directeurId)
                .orElseThrow(() -> new IllegalArgumentException("Directeur introuvable"));
        Conseiller conseiller = conseillerRepository.findById(conseillerId)
                .orElseThrow(() -> new IllegalArgumentException("Conseiller introuvable"));
        directeur.getConseillers().remove(conseiller);
        directeurRepository.save(directeur);
    }

    public List<ConseillerDTO> getConseillers(UUID directeurId) {
        Directeur directeur = directeurRepository.findById(directeurId)
                .orElseThrow(() -> new IllegalArgumentException("Directeur introuvable"));
        return directeur.getConseillers().stream()
                .map(ConseillerDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
