package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.rest.NotFoundByStringRestException;
import fr.uga.l3miage.example.exception.rest.NotFoundRestException;

import fr.uga.l3miage.example.exception.technical.NotFoundException;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Utilisateur;
import fr.uga.l3miage.example.repository.MiahootRepository;
import fr.uga.l3miage.example.response.MiahootDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MiahootComponent {
    private final MiahootRepository miahootRepository;
    private final MiahootMapper miahootMapper;

    public ResponseEntity<Long> create(final Miahoot miahoot){
        try {

        } catch (NotFoundByStringRestException ex) {
            throw new NotFoundByStringRestException(String.format("Aucun utilisateur n'a été trouvé pour l'id firebase donné", "debug"), "debug");
        }
        Miahoot miahootCree = miahootRepository.save(miahoot);
        return ResponseEntity.status(HttpStatus.CREATED).body(miahootCree.getId());


    }

    public List<Miahoot> findAll(){
        return miahootRepository.findAll();
    }

    public Miahoot findById(Long id) throws NotFoundException {
        return miahootRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Aucun miahoot n'a été trouvé pour l'id [%d]", id), id));
    }

    public void deleteById(Long id) {
        try {
            Miahoot m = findById(id);
            for(Utilisateur u : m.getConcepteurs()) {
                u.supprMiahootConcu(m);
                u.supprMiahootPresente(m);

            }
        } catch (NotFoundException ex) {
            throw new NotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), id, ex);
        }
        miahootRepository.deleteById(id);
    }

    public void updateMiahoot(final Long id, final MiahootDTO miahoot) throws  NotFoundException {

            Miahoot actualMiahoot = miahootRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(String.format("Aucune entité n'a été trouvée pour l'id [%s]",id), id));
            miahootMapper.mergeTestEntity(actualMiahoot, miahoot);
            miahootRepository.save(actualMiahoot);
    }
}
