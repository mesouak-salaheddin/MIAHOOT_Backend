package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Utilisateur")
public class UtilisateurDTO {

    @Schema(description = "id d'Utilisateur", example = "1")
    Long id;

    @Schema(description = "nom d'Utilisateur", example = "Salaheddin")
    String username;

    @Schema(description = "firebaseId d'Utilisateur", example = "12x35e75v")
    String firebaseId;

}