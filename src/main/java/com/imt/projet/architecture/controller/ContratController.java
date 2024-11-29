package com.imt.projet.architecture.controller;

import com.imt.projet.architecture.dto.ContratDTO;
import com.imt.projet.architecture.service.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contrats")
public class ContratController {

    @Autowired
    private ContratService contratService;

    @GetMapping
    public List<ContratDTO> getAllContrats() {
        return contratService.getAllContrats();
    }

    @GetMapping("/{id}")
    public ContratDTO getContratById(@PathVariable UUID id) {
        return contratService.getContratById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<ContratDTO> getContratsByClientId(@PathVariable UUID clientId) {
        return contratService.getContratsByClientId(clientId);
    }

    @PostMapping
    public ContratDTO createContrat(@RequestBody ContratDTO dto) {
        return contratService.createContrat(dto);
    }

    @PutMapping("/{id}")
    public ContratDTO updateContrat(@PathVariable UUID id, @RequestBody ContratDTO dto) {
        return contratService.updateContrat(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteContrat(@PathVariable UUID id) {
        contratService.deleteContrat(id);
    }
}
