package fr.uga.l3miage.example.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité question")
public class CreateQuestionRequest {
    @Schema(description = "label de l'objet question", example = "question test")
    private String label;

}