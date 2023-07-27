package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.PartieEndpoint;
import fr.uga.l3miage.example.response.PartieDTO;
import fr.uga.l3miage.example.service.PartieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PartieController implements PartieEndpoint {
    private final PartieService partieService;

    @Override
    public void createEntityPartie(Long miahootId) {
        partieService.create(miahootId);
    }

    @Override
    public List<PartieDTO> getAll() {
       return partieService.findAll();
    }

    public List<PartieDTO> findAllByMiahootId(Long miahootId) {
        return partieService.findAllByMiahootId(miahootId);
    }
}
