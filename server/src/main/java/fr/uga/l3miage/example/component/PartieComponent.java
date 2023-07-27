package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.NotFoundException;
import fr.uga.l3miage.example.models.*;
import fr.uga.l3miage.example.repository.PartieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PartieComponent {
    private final PartieRepository partieRepository;
    private final MiahootComponent miahootComponent;

    public void create(final Partie partie){
        partieRepository.save(partie);
    }

    public List<Partie> findAll(){
        return partieRepository.findAll();
    }

    public Partie findById(Long id) throws NotFoundException {
        return partieRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Aucune partie n'a été trouvée pour l'id [%d]", id), id));
    }

    public List<Partie> findAllByMiahootId(Long miahootId) throws NotFoundException {
        Miahoot miahoot = miahootComponent.findById(miahootId);

        return partieRepository.findAllByMiahoot(miahoot);
    }

}
