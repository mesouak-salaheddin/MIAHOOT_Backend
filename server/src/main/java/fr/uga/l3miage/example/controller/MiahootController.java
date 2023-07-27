package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.MiahootEndpoint;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.MiahootDTO;
import fr.uga.l3miage.example.service.MiahootService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MiahootController implements MiahootEndpoint {
    private final MiahootService miahootService;

    @Override
    public ResponseEntity<Long> createEntityMiahoot(@Valid @RequestBody final CreateMiahootRequest request) {
        ResponseEntity<Long> response = miahootService.createMiahoot(request);
        return response;
    }


    @Override
    public List<MiahootDTO> getAll() {
        return miahootService.findAll();
    }

    @Override
    public MiahootDTO findById(Long id) {
        return miahootService.findById(id);
    }

    @Override
    public void deleteMiahoot(Long id) {
        miahootService.deleteById(id);
    }

    @Override
    public void updateMiahoot(Long id, MiahootDTO miahootDTO) {

        miahootService.update(id,miahootDTO);
    }

    @Override
    public List<MiahootDTO> findMiahootsPresentesByUser(String userFirebaseId) {
        return miahootService.findMiahootPresentesByUser(userFirebaseId);
    }

    @Override
    public List<MiahootDTO> findMiahootsConcusByUser(String userFirebaseId) {
        return miahootService.findMiahootConcusByUser(userFirebaseId);
    }

}

