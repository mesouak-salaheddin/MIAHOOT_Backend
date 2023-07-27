package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.NotFoundException;
import fr.uga.l3miage.example.mapper.ReponseMapper;
import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.models.Session;
import fr.uga.l3miage.example.repository.ReponseRepository;
import fr.uga.l3miage.example.response.ReponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReponseComponent {
    private final ReponseRepository reponseRepository;
    private final QuestionComponent questionComponent;
    private final SessionComponent sessionComponent;
    private final ReponseMapper reponseMapper;

    public ResponseEntity<Long> create(final Reponse reponse){

        Reponse rep = reponseRepository.save(reponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(rep.getId());

    }

    public List<Reponse> findAll(){
        return reponseRepository.findAll();
    }



    public void deleteById(Long id) throws NotFoundException {
        Reponse reponse = findById(id);
        reponseRepository.deleteById(id);
    }

    public List<Reponse> findAllByQuestionId(Long questionId) throws NotFoundException {
        Question question = questionComponent.findById(questionId);

        return reponseRepository.findAllByQuestion(question);
    }

    public Reponse findById(Long id) throws NotFoundException {
        return reponseRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Aucune réponse n'a été trouvée pour l'id [%d]", id), id));
    }

     public void updateReponse(final Long id, final ReponseDTO reponseDTO) throws  NotFoundException {
        Reponse actualReponse = reponseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Aucune entité n'a été trouvée pour l'id [%s]",id), id));
        reponseMapper.mergeReponse(actualReponse, reponseDTO);
        reponseRepository.save(actualReponse);
    }


    public List<Reponse> findAllBySessionId(Long sessionId) throws NotFoundException {
        Session session = sessionComponent.findById(sessionId);
       return reponseRepository.findAllBySession(session);
    }

}
