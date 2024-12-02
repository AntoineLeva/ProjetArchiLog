package com.imt.projet.architecture.service;

import com.imt.projet.architecture.dto.ClientDTO;
import com.imt.projet.architecture.dto.ContratDTO;
import com.imt.projet.architecture.model.Contrat;
import com.imt.projet.architecture.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContratService {

    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private ClientService clientService; // Service pour récupérer les informations des clients

    public List<ContratDTO> getAllContrats() {
        return contratRepository.findAll().stream()
                .map(contrat -> {
                    ClientDTO clientDTO = null;
                    if (contrat.getClientId() != null) {
                        clientDTO = clientService.getClientById(contrat.getClientId());
                    }
                    return ContratDTO.fromEntity(contrat, clientDTO);
                })
                .collect(Collectors.toList());
    }

    public ContratDTO getContratById(UUID id) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrat introuvable"));

        ClientDTO clientDTO = null;
        if (contrat.getClientId() != null) {
            clientDTO = clientService.getClientById(contrat.getClientId());
        }

        return ContratDTO.fromEntity(contrat, clientDTO);
    }

    public List<ContratDTO> getContratsByClientId(UUID clientId) {
        return contratRepository.findByClientId(clientId).stream()
                .map(contrat -> {
                    ClientDTO clientDTO = clientService.getClientById(clientId);
                    return ContratDTO.fromEntity(contrat, clientDTO);
                })
                .collect(Collectors.toList());
    }

    public ContratDTO createContrat(ContratDTO dto) {
        Contrat contrat = dto.toEntity();
        contrat.setId(UUID.randomUUID());

        if (contrat.getDate() == null) {
            contrat.setDate(LocalDateTime.now());
        }

        Contrat savedContrat = contratRepository.save(contrat);

        ClientDTO clientDTO = clientService.getClientById(contrat.getClientId());
        return ContratDTO.fromEntity(savedContrat, clientDTO);
    }

    public ContratDTO updateContrat(UUID id, ContratDTO updatedDto) {
        Contrat existingContrat = contratRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrat introuvable"));

        existingContrat.setNom(updatedDto.getNom());
        existingContrat.setDate(updatedDto.getDate() != null ? updatedDto.getDate() : existingContrat.getDate());
        existingContrat.setClientId(updatedDto.getClientId());

        Contrat updatedContrat = contratRepository.save(existingContrat);

        ClientDTO clientDTO = null;
        if (updatedContrat.getClientId() != null) {
            clientDTO = clientService.getClientById(updatedContrat.getClientId());
        }

        return ContratDTO.fromEntity(updatedContrat, clientDTO);
    }

    public void deleteContrat(UUID id) {
        contratRepository.deleteById(id);
    }
}
