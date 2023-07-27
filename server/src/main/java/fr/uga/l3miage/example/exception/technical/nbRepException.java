package fr.uga.l3miage.example.exception.technical;

import lombok.Getter;

@Getter
public class nbRepException extends Exception{
    private final Long id;

    public nbRepException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public nbRepException(String message, Long id, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

}
