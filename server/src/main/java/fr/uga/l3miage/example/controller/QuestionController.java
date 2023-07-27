package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.QuestionEndpoint;
import fr.uga.l3miage.example.response.QuestionDTO;
import fr.uga.l3miage.example.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController implements QuestionEndpoint {
    private final QuestionService questionService;

    @Override
    public ResponseEntity<Long> createEntityQuestion(final Long miahootId, final String label) {
        ResponseEntity<Long> response = questionService.createQuestion(label, miahootId);
        return response;
    }

    @Override
    public List<QuestionDTO> getAll() {
        return questionService.findAll();
    }

    @Override
    public List<QuestionDTO> findAllByMiahootId(Long miahootId) {
        return questionService.findAllByMiahootId(miahootId);
    }

    @Override
    public QuestionDTO findById(Long questionId) {
        return questionService.findById(questionId);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionService.deleteById(id);
    }



    @Override
    public void updateQuestionLabel(Long id, String label) {
        questionService.updateLabel(id, label);
    }
}
