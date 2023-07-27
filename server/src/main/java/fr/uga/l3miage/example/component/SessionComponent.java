package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.NotFoundByStringException;
import fr.uga.l3miage.example.exception.technical.NotFoundException;
import fr.uga.l3miage.example.mapper.SessionMapper;
import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.models.Session;
import fr.uga.l3miage.example.models.Utilisateur;
import fr.uga.l3miage.example.repository.SessionRepository;
import fr.uga.l3miage.example.response.SessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SessionComponent {
    private final SessionRepository sessionRepository;
    private final PartieComponent partieComponent;
    private final SessionMapper sessionMapper;
    private final UtilisateurComponent utilisateurComponent;

    public void create(final Session session){
        sessionRepository.save(session);
    }

    public List<Session> findAll(){
        return sessionRepository.findAll();
    }

    public void deleteById(Long id) throws NotFoundException {
        Session session = findById(id);
        sessionRepository.deleteById(id);
    }

    public List<Session> findAllByPartieId(Long partieId) throws NotFoundException {
        Partie partie = partieComponent.findById(partieId);

       return sessionRepository.findAllByPartie(partie);
    }


    public List<Session> findAllByParticipantfirebase(String firebaseId) throws NotFoundByStringException {
        Utilisateur participant = utilisateurComponent.findByFirebaseId(firebaseId);

       return sessionRepository.findAllByParticipant(participant);
    }


    public Session findById(Long id) throws NotFoundException {
        return sessionRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Aucune session n'a été trouvée pour l'id [%d]", id), id));
    }

    public void updateSession(final Long id, final SessionDTO sessionDTO) throws  NotFoundException {
        Session actualSession = sessionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Aucune entité n'a été trouvée pour l'id [%s]",id), id));
        sessionMapper.mergeSession(actualSession, sessionDTO);
        sessionRepository.save(actualSession);
    }

}
