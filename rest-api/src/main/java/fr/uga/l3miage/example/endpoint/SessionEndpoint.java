package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.error.NotFoundErrorResponse;
import fr.uga.l3miage.example.error.TestEntityNotDeletedErrorResponse;
import fr.uga.l3miage.example.request.CreateSessionRequest;
import fr.uga.l3miage.example.response.SessionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Session tag") //swagger
@CrossOrigin
@RestController
public interface SessionEndpoint {
    @Operation(description = "Création d'une entité Session") //swagger
    @ApiResponse(responseCode = "201", description = "L'entité Session a bien été créée.") //swagger
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("partie/{partieId}/session")
    void createEntitySession(@PathVariable Long partieId, @Valid @RequestBody final CreateSessionRequest request);

    @Operation(description = "Affiche tous les sessions existants")
    @ApiResponse(responseCode = "200", description = "Entités sessions trouvées")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("session/")
    List<SessionDTO> getAll();

    @Operation(description = "Obtention des Sessions pour une Partie donnée") //swagger
    @ApiResponse(responseCode = "200", description = "La Partie a bien été trouvé.") //swagger
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("partie/{partieId}/sessions")
    List<SessionDTO> findAllByPartieId(@PathVariable final Long partieId);

    @Operation(description = "Obtention des Sessions pour un Participant donné") //swagger
    @ApiResponse(responseCode = "200", description = "Les sessions ont bien été trouvé.") //swagger
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("utilisateur/{firebaseId}/sessions")
    List<SessionDTO> findAllByParticipantFirebaseId(@PathVariable final String firebaseId);


    @Operation(description = "Affiche la Session correspondant à l'id donné")
    @ApiResponse(responseCode = "200", description = "Entité Session trouvée")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("session/{sessionId}")
    SessionDTO findById(@PathVariable Long sessionId);

    @Operation(description = "Mise à jour d'une entité Session")
    @ApiResponse(responseCode = "202", description = "L'entité à bien été mis à jour")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("session/{id}")
    void updateSession(@PathVariable final Long id,@RequestBody final SessionDTO sessionDTO);

    @Operation(description = "Suppression d'une entité Session en bd")
    @ApiResponse(responseCode = "200", description = "Le session a bien été supprimé")
    @ApiResponse(responseCode = "418", description = "Renvoie une erreur 418 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = TestEntityNotDeletedErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("session/{id}")
    void deleteSession(@PathVariable Long id);
}