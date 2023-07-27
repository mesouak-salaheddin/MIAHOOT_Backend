package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.SessionEndpoint;
import fr.uga.l3miage.example.request.CreateSessionRequest;
import fr.uga.l3miage.example.response.SessionDTO;
import fr.uga.l3miage.example.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SessionController implements SessionEndpoint {
    private final SessionService sessionService;

    @Override
    public void createEntitySession(final Long partieId, final CreateSessionRequest request) {
        sessionService.createSession(partieId, request);
    }

    @Override
    public List<SessionDTO> getAll() {
        return sessionService.findAll();
    }

    @Override
    public void deleteSession(Long id) {
        sessionService.deleteById(id);
    }

    public List<SessionDTO> findAllByPartieId(Long partieId) {
        return sessionService.findAllByPartieId(partieId);
    }

    public List<SessionDTO> findAllByParticipantFirebaseId(String firebaseId){
        return sessionService.findAllByParticipantFirebaseId(firebaseId);
    }

    @Override
    public SessionDTO findById(Long sessionId) {
        return sessionService.findById(sessionId);
    }

    @Override
    public void updateSession(Long id, SessionDTO sessionDTO) {
        sessionService.update(id,sessionDTO);
    }

}
