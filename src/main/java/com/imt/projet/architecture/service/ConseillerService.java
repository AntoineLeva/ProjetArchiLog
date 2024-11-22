
package com.imt.projet.architecture.service;

import com.imt.projet.architecture.dto.ConseillerDTO;
import com.imt.projet.architecture.model.Conseiller;
import com.imt.projet.architecture.repository.ConseillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConseillerService {

    @Autowired
    private ConseillerRepository conseillerRepository;

    @Autowired
    private ClientService clientService;

    public List<ConseillerDTO> getAllConseillers() {
        return conseillerRepository.findAll().stream()
                .map(ConseillerDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public ConseillerDTO addConseiller(ConseillerDTO conseillerDTO) {
        Conseiller conseiller = conseillerDTO.toEntity();
        return ConseillerDTO.fromEntity(conseillerRepository.save(conseiller));
    }

    public void ajouterClient(UUID conseillerId, UUID clientId) {
        Conseiller conseiller = conseillerRepository.findById(conseillerId)
                .orElseThrow(() -> new IllegalArgumentException("Conseiller introuvable"));
        conseiller.ajouterClient(clientId);
        conseillerRepository.save(conseiller);
        clientService.affecterConseiller(clientId, conseillerId);
    }

    public void retirerClient(UUID conseillerId, UUID clientId) {
        Conseiller conseiller = conseillerRepository.findById(conseillerId)
                .orElseThrow(() -> new IllegalArgumentException("Conseiller introuvable"));
        conseiller.retirerClient(clientId);
        conseillerRepository.save(conseiller);
        //clientService.retirerConseiller(clientId);
    }
}
