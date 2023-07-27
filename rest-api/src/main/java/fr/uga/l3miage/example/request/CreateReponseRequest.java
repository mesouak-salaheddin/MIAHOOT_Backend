package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité reponse")
@Getter
public class CreateReponseRequest {

    @Schema(description = "label de l'objet reponse", example = "label reponse")
    private String label;

    @Schema(description = "valeur de validité de l'objet reponse", example = "true")
    private boolean estCorrecte;
}
