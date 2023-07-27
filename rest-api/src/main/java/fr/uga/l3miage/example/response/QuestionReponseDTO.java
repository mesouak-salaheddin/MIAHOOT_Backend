package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "dto used to show a reponse inside a question")
public class QuestionReponseDTO {
    @Schema(description = "correspond à l'id de la réponse ",example = "1")
    Long id;

    @Schema(description = "correspond au label du reponse",example = "vrai")
    String label;

    @Schema(description = "correspond au valeur de la reponse s'elle est valide ou non",example = "true")
    boolean estCorrecte;
}
