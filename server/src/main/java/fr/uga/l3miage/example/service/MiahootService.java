package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.MiahootComponent;
import fr.uga.l3miage.example.component.UtilisateurComponent;
import fr.uga.l3miage.example.exception.rest.*;
import fr.uga.l3miage.example.exception.technical.*;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Utilisateur;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.MiahootDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MiahootService {

    private static final String ERROR_DETECTED = "Une erreur lors de la création de l'entité TestConfigWithProperties à été détecté.";
    private final MiahootComponent miahootComponent;
    private final UtilisateurComponent utilisateurComponent;
    private final UtilisateurService utilisateurService;
    private final MiahootMapper miahootMapper;

    public ResponseEntity<Long> createMiahoot(final CreateMiahootRequest createMiahootRequest) {
        Miahoot newMiahootEntity = miahootMapper.toEntity(createMiahootRequest);
        ResponseEntity<Long> reponse = miahootComponent.create(newMiahootEntity);
        //bind le concepteur correspondant au firebase à id au nouveau miahoot
        utilisateurService.joinMiahootConcepteur(newMiahootEntity.getId(), createMiahootRequest.getFirebaseId());

        return reponse;
    }


    public List<MiahootDTO> findAll(){
        return miahootComponent.findAll().stream().map(miahootMapper::toDto).collect(Collectors.toList());
    }

    public MiahootDTO findById(Long id){
        try{
            return miahootMapper.toDto(miahootComponent.findById(id));
        } catch (NotFoundException ex) {
            throw new NotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), id, ex);
        }
    }

    public void deleteById(Long id) {
        miahootComponent.deleteById(id);
    }

    public void update(final Long id, final MiahootDTO miahootDTO) {
        // Use the ID from the URL if the ID in the DTO object is different from url
        Long dtoId = miahootDTO.getId();
        if (dtoId == null || !dtoId.equals(id)) {
            miahootDTO.setId(id);
        }
        try {
            miahootComponent.updateMiahoot(id,miahootDTO);
        } catch (NotFoundException e) {
            throw new NotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", e.getMessage()), id, e);
        }
    }

    public List<MiahootDTO> findMiahootConcusByUser(String firebaseId) {
        return findMiahootByUser(firebaseId,"conçu");
    }

    public List<MiahootDTO> findMiahootPresentesByUser(String firebaseId) {
        return findMiahootByUser(firebaseId,"présenté");
    }

    private List<MiahootDTO> findMiahootByUser(String firebaseId, String statut) {
        try {
            Utilisateur user = utilisateurComponent.findByFirebaseId(firebaseId);
            List<Miahoot> miahootList;
            switch (statut) {
                case "présenté":
                    miahootList = user.getMiahootsPresentes();
                    break;
                case "conçu":
                    miahootList = user.getMiahootsConcus();
                    break;
                default:
                    throw new IllegalArgumentException("Statut du miahoot invalide");
            }
            return  miahootList.stream().map(miahootMapper::toDto).collect(Collectors.toList());
        } catch (NotFoundByStringException e) {
            throw new NotFoundByStringRestException(String.format("Impossible de charger l'entité. Raison : [%s]", e.getMessage()), firebaseId, e);
        }
    }

}
