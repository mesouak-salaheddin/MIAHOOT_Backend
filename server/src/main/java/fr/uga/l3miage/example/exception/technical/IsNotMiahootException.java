package fr.uga.l3miage.example.exception.technical;

import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.TestEntity;
import lombok.Getter;

@Getter
public class IsNotMiahootException extends Exception{
    private final Miahoot miahootEntity;

    public IsNotMiahootException(String message, Miahoot miahootEntity) {
        super(message);
        this.miahootEntity = miahootEntity;
    }

    public IsNotMiahootException(String message, Miahoot miahootEntity, Throwable cause) {
        super(message, cause);
        this.miahootEntity = miahootEntity;
    }
}
