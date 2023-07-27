package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.NotFoundException;
import fr.uga.l3miage.example.mapper.QuestionMapper;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionComponent {
    private final QuestionRepository questionRepository;
    private final MiahootComponent miahootComponent;
    private final QuestionMapper questionMapper;

    public ResponseEntity<Long> create(final Question question){

        Question questionCree = questionRepository.save(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(questionCree.getId());


    }

    public List<Question> findAll(){
        return questionRepository.findAll();
    }

    public List<Question> findAllByMiahootId(Long miahootId) throws NotFoundException {
        Miahoot miahoot = miahootComponent.findById(miahootId);

        return questionRepository.findAllByMiahoot(miahoot);
    }

    public Question findById(Long id) throws NotFoundException {
        return questionRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Aucune Question n'a été trouvée pour l'id [%d]", id), id));
    }

    public void deleteById(Long id) throws NotFoundException {
        Question question = findById(id);
        questionRepository.deleteById(id);
    }


    public void updateLabel(final Long id, final String label) throws NotFoundException {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Aucune entité n'a été trouvée pour l'id [%s]",id), id));
        question.setLabel(label.replaceAll("\"", ""));
        questionRepository.save(question);
    }

}
