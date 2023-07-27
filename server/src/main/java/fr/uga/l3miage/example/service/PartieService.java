package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.MiahootComponent;
import fr.uga.l3miage.example.component.PartieComponent;
import fr.uga.l3miage.example.exception.rest.NotFoundRestException;
import fr.uga.l3miage.example.exception.technical.NotFoundException;
import fr.uga.l3miage.example.mapper.PartieMapper;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.response.PartieDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PartieService {
    private final PartieComponent partieComponent;
    private final MiahootComponent miahootComponent;
    private final PartieMapper partieMapper;
    public void create(Long miahootId) {
        Partie newPartieEntity = partieMapper.toEntity(miahootId);
        partieComponent.create(newPartieEntity);
        bind(miahootId, newPartieEntity);
    }

    public List<PartieDTO> findAll(){
        return partieComponent.findAll().stream().map(partieMapper::toDto).collect(Collectors.toList());
    }

    private void bind(Long miahootId, Partie partie){
        try{
            Miahoot miahoot = miahootComponent.findById(miahootId);
            miahoot.addPartie(partie);
            partie.setMiahoot(miahoot);
        } catch(NotFoundException ex){
            throw new NotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), miahootId, ex);
        }
    }

    public List<PartieDTO> findAllByMiahootId(Long miahootId){
        try{
            return partieComponent.findAllByMiahootId(miahootId).stream().map(partieMapper::toDto).collect(Collectors.toList());
        } catch(NotFoundException ex){
            throw new NotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), miahootId, ex);
        }
    }
}
