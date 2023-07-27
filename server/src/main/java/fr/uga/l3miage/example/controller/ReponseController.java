package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.ReponseEndpoint;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.ReponseDTO;
import fr.uga.l3miage.example.service.ReponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReponseController implements ReponseEndpoint {
    private final ReponseService reponseService;

    @Override
    public ResponseEntity<Long> createEntityReponse(final Long questionId, final CreateReponseRequest request) {
        ResponseEntity<Long> response = reponseService.createReponse(questionId, request);
        return response;
    }

    @Override
    public List<ReponseDTO> getAll() {
        return reponseService.findAll();
    }

    @Override
    public void deleteReponse(Long id) {
        reponseService.deleteById(id);
    }

    public List<ReponseDTO> findAllByQuestionId(Long questionId) {
        return reponseService.findAllByQuestionId(questionId);
    }

    public List<ReponseDTO> findAllBySessionId(Long sessionId) {
        return reponseService.findAllBySessionId(sessionId);
    }

    @Override
    public ReponseDTO findById(Long reponseId) {
        return reponseService.findById(reponseId);
    }

    @Override
    public void updateReponse(Long id, ReponseDTO reponseDTO) {
        reponseService.update(id,reponseDTO);
    }
/*
    @Override
    public List<ReponseDTO> getUtilisateurReponsesByMiahoot(Long miahootId, String userFirebaseId) {
        return reponseService.getUtilisateurReponsesByMiahoot(miahootId, userFirebaseId);
    }


 */
}
