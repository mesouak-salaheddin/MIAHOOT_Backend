package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.SessionComponent;
import fr.uga.l3miage.example.component.UtilisateurComponent;
import fr.uga.l3miage.example.component.PartieComponent;
import fr.uga.l3miage.example.exception.rest.NotFoundRestException;
import fr.uga.l3miage.example.exception.rest.NotFoundByStringRestException;
import fr.uga.l3miage.example.exception.technical.NotFoundByStringException;
import fr.uga.l3miage.example.exception.technical.NotFoundException;
import fr.uga.l3miage.example.mapper.SessionMapper;
import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.models.Session;
import fr.uga.l3miage.example.request.CreateSessionRequest;
import fr.uga.l3miage.example.response.SessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionService {
    private static final String ERROR_DETECTED = "Une erreur lors de la création de l'entité Session à été détecté.";
    private final SessionComponent sessionComponent;
    private final UtilisateurComponent utilisateurComponent;
    private final PartieComponent partieComponent;
    private final SessionMapper sessionMapper;

    public void createSession(final Long partieId, CreateSessionRequest request) {

        Session newSessionEntity = sessionMapper.toEntity(request);
        sessionComponent.create(newSessionEntity);
        bind(partieId, newSessionEntity);
    }


    public List<SessionDTO> findAll(){
        return sessionComponent.findAll().stream().map(sessionMapper::toDto).collect(Collectors.toList());
    }

    public List<SessionDTO> findAllByPartieId(Long partieId){
        try{
            return sessionComponent.findAllByPartieId(partieId).stream().map(sessionMapper::toDto).collect(Collectors.toList());
        } catch(NotFoundException ex){
            throw new NotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), partieId, ex);
        }
    }

    public List<SessionDTO> findAllByParticipantFirebaseId(String firebaseId){
        try{
            return sessionComponent.findAllByParticipantfirebase(firebaseId).stream().map(sessionMapper::toDto).collect(Collectors.toList());
        } catch (NotFoundByStringException ex){
            throw new NotFoundByStringRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), firebaseId, ex);

        }
    }

    public void deleteById(Long id) {
        try {
            sessionComponent.deleteById(id);
        } catch (NotFoundException ex) {
            throw new NotFoundRestException(String.format("Impossible de supprimer l'entité. Raison : [%s]", ex.getMessage()), id, ex);
        }
    }


    public SessionDTO findById(Long id) {
        try {
            return sessionMapper.toDto(sessionComponent.findById(id));
        } catch (NotFoundException ex) {
            throw new NotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()),id,ex);
        }
    }

    public void update(Long id, SessionDTO sessionDTO) {
        try {
            sessionComponent.updateSession(id,sessionDTO);
        } catch (NotFoundException e) {
            throw new NotFoundRestException(String.format("Impossible de modifier l'entité. Raison : [%s]", e.getMessage()), id, e);
        }
    }


    private void bind(Long partieId, Session session){
        try{
            Partie partie = partieComponent.findById(partieId);
            partie.addSession(session);
            session.setPartie(partie);
        } catch(NotFoundException ex){
            throw new NotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), partieId, ex);
        }
    }


}
