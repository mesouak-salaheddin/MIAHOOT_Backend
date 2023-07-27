package fr.uga.l3miage.example.exception.technical;


import fr.uga.l3miage.example.models.Question;
import lombok.Getter;

@Getter
public class IsNotQuestionException extends Exception {
    private final Question question;


    public IsNotQuestionException(String message, Question questionEntity) {
        super(message);
        this.question = questionEntity;
    }

    public IsNotQuestionException(String message, Question questionEntity, Throwable cause) {
        super(message,cause);
        this.question = questionEntity;

    }

}
