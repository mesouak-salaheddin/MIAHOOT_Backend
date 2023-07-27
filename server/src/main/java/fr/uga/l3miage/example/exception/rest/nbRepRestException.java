package fr.uga.l3miage.example.exception.rest;


import fr.uga.l3miage.example.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class nbRepRestException extends RuntimeException{
    private final Long id;

    public nbRepRestException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public nbRepRestException(String message, Long id, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.I_AM_A_TEAPOT;
    }

    public ErrorCode getErrorCode(){return ErrorCode.TOO_MANY_TRUE_ANSWERS;}
}
