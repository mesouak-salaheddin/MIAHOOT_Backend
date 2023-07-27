package fr.uga.l3miage.example.exception.rest;

import fr.uga.l3miage.example.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FirebaseIdAlreadyExistsRestException extends RuntimeException {

    private final String firebaseId;

    public FirebaseIdAlreadyExistsRestException(String message, String firebaseId) {
        super(message);
        this.firebaseId = firebaseId;
    }

    public FirebaseIdAlreadyExistsRestException(String message, String firebaseId, Throwable cause) {
        super(message, cause);
        this.firebaseId = firebaseId;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    public ErrorCode getErrorCode(){return ErrorCode.FIREBASE_ID_ALREADY_USE_ERROR;}


}
