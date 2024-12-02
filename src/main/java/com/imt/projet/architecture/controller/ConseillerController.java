
package com.imt.projet.architecture.controller;

import com.imt.projet.architecture.dto.ConseillerDTO;
import com.imt.projet.architecture.service.ConseillerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/conseillers")
public class ConseillerController {

    @Autowired
    private ConseillerService conseillerService;

    @GetMapping
    public List<ConseillerDTO> getAllConseillers() {
        return conseillerService.getAllConseillers();
    }

    @PostMapping
    public ConseillerDTO addConseiller(@RequestBody ConseillerDTO conseillerDTO) {
        return conseillerService.addConseiller(conseillerDTO);
    }

    @PostMapping("/{conseillerId}/ajouter-client/{clientId}")
    public void ajouterClient(@PathVariable UUID conseillerId, @PathVariable UUID clientId) {
        conseillerService.ajouterClient(conseillerId, clientId);
    }

    @DeleteMapping("/{conseillerId}/retirer-client/{clientId}")
    public void retirerClient(@PathVariable UUID conseillerId, @PathVariable UUID clientId) {
        conseillerService.retirerClient(conseillerId, clientId);
    }

    @PostMapping("/creer-auto")
    public ConseillerDTO creerConseillerAuto() {
        return conseillerService.creerConseillerAuto();
    }

}
