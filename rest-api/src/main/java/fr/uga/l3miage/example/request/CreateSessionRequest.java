package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité session")
@Getter
public class CreateSessionRequest {

    @Schema(description = "correspond à la description de la session",example = "session du groupe 8")
    private String description;

    @Schema(description = "correspond à l'id du partie de la session", example="1")
    private Long partieId;

}
