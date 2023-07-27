package fr.uga.l3miage.example.exception.technical;

import fr.uga.l3miage.example.models.Reponse;
import lombok.Getter;

@Getter

public class IsNotReponseException extends Exception {
    private final Reponse reponseEntity;

    public IsNotReponseException(String message, Reponse reponseEntity) {
        super(message);
        this.reponseEntity = reponseEntity;
    }

    public IsNotReponseException(String message, Reponse reponseEntity, Throwable cause) {
        super(message, cause);
        this.reponseEntity = reponseEntity;
    }
}
