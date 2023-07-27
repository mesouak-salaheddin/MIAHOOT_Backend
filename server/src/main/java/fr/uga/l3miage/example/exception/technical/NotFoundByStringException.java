package fr.uga.l3miage.example.exception.technical;

import lombok.Getter;

@Getter
public class NotFoundByStringException extends Exception {
    //exception thrown when an entity wasn't find using a string
    private final String search;

    public NotFoundByStringException(String message, String search) {
        super(message);
        this.search = search;
    }

    public NotFoundByStringException(String message, String search, Throwable cause) {
        super(message, cause);
        this.search = search;
    }
}