package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Session")
public class SessionDTO {
    @Schema(description = "id du Session", example = "1")
    Long id;

    @Schema(description = "description du Session", example = "Session du groupe 8")
    String description;

    @Schema(description = "correspond à l'id de la partie de la session courante", example="1")
    Long partieId;
}
