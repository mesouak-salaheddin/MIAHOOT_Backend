package fr.uga.l3miage.example.exception.technical;

import lombok.Getter;

@Getter
public class NotFoundException extends Exception {
    //exception thrown when an entity wasn't find using its id
    private final Long id;

    public NotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public NotFoundException(String message, Long id, Throwable cause) {
        super(message, cause);
        this.id = id;
    }
}