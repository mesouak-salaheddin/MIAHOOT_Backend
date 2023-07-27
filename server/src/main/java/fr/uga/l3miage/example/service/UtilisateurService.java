package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.MiahootComponent;
import fr.uga.l3miage.example.component.ReponseComponent;
import fr.uga.l3miage.example.component.UtilisateurComponent;
import fr.uga.l3miage.example.exception.rest.FirebaseIdAlreadyExistsRestException;
import fr.uga.l3miage.example.exception.rest.NotFoundByStringRestException;
import fr.uga.l3miage.example.exception.rest.NotFoundRestException;
import fr.uga.l3miage.example.exception.technical.FirebaseIdAlreadyExistsException;
import fr.uga.l3miage.example.exception.technical.NotFoundByStringException;
import fr.uga.l3miage.example.exception.technical.NotFoundException;
import fr.uga.l3miage.example.mapper.UtilisateurMapper;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Utilisateur;
import fr.uga.l3miage.example.request.CreateUtilisateurRequest;
import fr.uga.l3miage.example.response.UtilisateurDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UtilisateurService {

    private static final String ERROR_DETECTED = "Une erreur lors de la création de l'entité TestConfigWithProperties à été détecté.";
    private final UtilisateurComponent utilisateurComponent;
    private final ReponseComponent reponseComponent;
    private final MiahootComponent miahootComponent;
    private final UtilisateurMapper utilisateurMapper;

    public void createUtilisateur(final CreateUtilisateurRequest createUtilisateurRequest){
        Utilisateur newUtilisateurEntity = utilisateurMapper.toEntity(createUtilisateurRequest);
        try {
            utilisateurComponent.create(newUtilisateurEntity);
        } catch (FirebaseIdAlreadyExistsException e) {
            throw new FirebaseIdAlreadyExistsRestException(ERROR_DETECTED,newUtilisateurEntity.getFirebaseId(),e);
        }

    }

    public List<UtilisateurDTO> findAll(){
        return utilisateurComponent.findAll().stream().map(utilisateurMapper::toDto).collect(Collectors.toList());
    }

    public UtilisateurDTO findById(Long id){
        try{
            return utilisateurMapper.toDto(utilisateurComponent.findById(id));
        } catch (NotFoundException ex) {
            throw new NotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), id, ex);
        }
    }

    public void update(final Long id, final UtilisateurDTO utilisateurDTO) {
        try {
            utilisateurComponent.updateUtilisateur(id,utilisateurDTO);
        } catch (NotFoundException e) {
            throw new NotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", e.getMessage()), id, e);
        }
    }

    public void joinMiahootPresentateur(Long miahootId, String userFirebaseId) {
        bindMiahoot(miahootId, userFirebaseId, "presentateur");
    }
    public void joinMiahootConcepteur(Long miahootId, String userFirebaseId) {
        bindMiahoot(miahootId, userFirebaseId, "concepteur");
    }


    public List<UtilisateurDTO> findPresentateursByMiahoot(Long miahootId) {
        return findUtilisateursByMiahoot(miahootId, "presentateur");
    }

    public List<UtilisateurDTO> findConcepteursByMiahoot(Long miahootId) {
        return findUtilisateursByMiahoot(miahootId, "concepteur");
    }

    private List<UtilisateurDTO> findUtilisateursByMiahoot(Long miahootId, String role){
        try{
            Miahoot miahoot = miahootComponent.findById(miahootId);
            List<Utilisateur> userList;
            switch (role) {
                case "presentateur":
                    userList = miahoot.getPresentateurs();
                    break;
                case "concepteur":
                    userList = miahoot.getConcepteurs();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid role");
            }
            return userList.stream().map(utilisateurMapper::toDto).collect(Collectors.toList());
        } catch(NotFoundException ex){
            throw new NotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), miahootId, ex);
        }
    }

    public void bindMiahoot(Long miahootId, String userFirebaseId, String role) {
        try {
            Utilisateur utilisateur = utilisateurComponent.findByFirebaseId(userFirebaseId);
            Miahoot miahoot = miahootComponent.findById(miahootId);
            switch (role) {
                case "presentateur":
                    utilisateur.addMiahootPresente(miahoot);
                    miahoot.addPresentateur(utilisateur);
                    break;
                case "concepteur":
                    utilisateur.addMiahootConcu(miahoot);
                    miahoot.addConcepteur(utilisateur);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid role");
            }
        } catch (NotFoundException e) {
            throw new NotFoundRestException(String.format("Impossible de charger la réponse. Raison : [%s]", e.getMessage()), miahootId, e);
        } catch (NotFoundByStringException e) {
            throw new NotFoundByStringRestException(String.format("Impossible de charger l'entité. Raison : [%s]", e.getMessage()), userFirebaseId, e);
        }
    }
}
