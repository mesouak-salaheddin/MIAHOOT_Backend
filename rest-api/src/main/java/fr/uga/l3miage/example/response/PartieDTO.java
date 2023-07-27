package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Partie")
public class PartieDTO {

    @Schema(description = "id de la partie", example = "1")
    Long id;

    @Schema(description = "date de la partie")
    private Date date;

    @Schema(description = "id du miahoot associé", example = "1")
    Long miahootId;

    @Schema(description = "est ce que la partie est active", example = "false")
    Boolean isActive;

}
