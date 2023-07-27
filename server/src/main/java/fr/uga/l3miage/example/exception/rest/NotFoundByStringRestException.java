package fr.uga.l3miage.example.exception.rest;

import fr.uga.l3miage.example.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundByStringRestException extends RuntimeException{
    private final String search;

    public NotFoundByStringRestException(String message, String search) {
        super(message);
        this.search = search;
    }

    public NotFoundByStringRestException(String message, String search, Throwable cause) {
        super(message, cause);
        this.search = search;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public ErrorCode getErrorCode(){return ErrorCode.FIREBASEID_NOT_FOUND;}
}