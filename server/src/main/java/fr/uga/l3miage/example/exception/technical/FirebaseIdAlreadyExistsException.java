package fr.uga.l3miage.example.exception.technical;

import lombok.Getter;

@Getter
public class FirebaseIdAlreadyExistsException extends Exception {
    private final String firebaseId;

    public FirebaseIdAlreadyExistsException(String message, String firebaseId) {
        super(message);
        this.firebaseId = firebaseId;
    }

    public FirebaseIdAlreadyExistsException(String message, String firebaseId, Throwable cause) {
        super(message, cause);
        this.firebaseId = firebaseId;
    }

}
