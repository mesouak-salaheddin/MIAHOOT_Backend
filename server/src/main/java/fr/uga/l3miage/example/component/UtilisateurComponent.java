package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.rest.NotFoundByStringRestException;

import fr.uga.l3miage.example.exception.technical.*;
import fr.uga.l3miage.example.mapper.UtilisateurMapper;
import fr.uga.l3miage.example.models.*;
import fr.uga.l3miage.example.repository.UtilisateurRepository;
import fr.uga.l3miage.example.response.UtilisateurDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UtilisateurComponent {
    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;


    public void create(final Utilisateur utilisateur) throws FirebaseIdAlreadyExistsException {
        if(utilisateurRepository.findByFirebaseId(utilisateur.getFirebaseId()).isPresent()) {
            throw new FirebaseIdAlreadyExistsException(String.format("L'id firebase %s existe déjà en BD.", utilisateur.getFirebaseId()), utilisateur.getFirebaseId());        }
        utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> findAll(){
        return utilisateurRepository.findAll();
    }

    public Utilisateur findById(Long id) throws NotFoundException {
        return utilisateurRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Aucun utilisateur n'a été trouvé pour l'id [%d]", id), id));
    }

    public Utilisateur findByFirebaseId(String firebaseId) throws NotFoundByStringException {
        return utilisateurRepository.findByFirebaseId(firebaseId).orElseThrow(() -> new NotFoundByStringRestException(String.format("Aucun utilisateur n'a été trouvé pour l'id firebase [%s]", firebaseId), firebaseId));
    }

    public void updateUtilisateur(final Long id, final UtilisateurDTO utilisateurDTO) throws  NotFoundException {

        Utilisateur actualUtilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Aucune entité n'a été trouvée pour l'id [%s]",id), id));
        utilisateurMapper.mergeTestEntity(actualUtilisateur, utilisateurDTO);
        utilisateurRepository.save(actualUtilisateur);
    }

}
