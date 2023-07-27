package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Question")
public class QuestionDTO {
    @Schema(description = "id de la question", example = "1")
    Long id;

    @Schema(description = "correspond au label de la question",example = "comment on springboot ?")
    String label;

    @Schema(description = "correspond à l'id du miahoot de la question", example="1")
    Long miahootId;

    @Schema(description = "liste des réponses à la question")
    List<QuestionReponseDTO> reponses;

}
