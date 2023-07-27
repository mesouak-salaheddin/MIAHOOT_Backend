package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité miahoot")
public class CreateUtilisateurRequest {

    @Schema(description = "nom d'Utilisateur", example = "s_msk20")
    private String username;

    @Schema(description = "identifiant du FireBase ", example = "UL83MSwD74UZlsVUa5otTFNs0zc2")
    private String firebaseId;

}
