package fr.uga.l3miage.example.endpoint;


import fr.uga.l3miage.example.error.NotFoundErrorResponse;
import fr.uga.l3miage.example.error.TestEntityNotDeletedErrorResponse;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.ReponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Reponse tag") //swagger
@CrossOrigin
@RestController
@RequestMapping
public interface ReponseEndpoint {
    @Operation(description = "Création d'une entité Reponse") //swagger
    @ApiResponse(responseCode = "201", description = "L'entité Reponse a bien été créée.") //swagger
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("question/{questionId}/reponse")
    ResponseEntity<Long> createEntityReponse(@PathVariable Long questionId, @Valid @RequestBody final CreateReponseRequest request);

    @Operation(description = "Affiche tous les reponses existants")
    @ApiResponse(responseCode = "200", description = "Entités reponses trouvées")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("reponse/")
    List<ReponseDTO> getAll();


    @Operation(description = "Suppression d'une entité reponse en bd")
    @ApiResponse(responseCode = "200", description = "La réponse a bien été supprimée")
    @ApiResponse(responseCode = "418", description = "Renvoie une erreur 418 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = TestEntityNotDeletedErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("reponse/{id}")
    void deleteReponse(@PathVariable Long id);

    @Operation(description = "Obtention des Reponses pour une Question donnée") //swagger
    @ApiResponse(responseCode = "200", description = "La question a bien été trouvé.") //swagger
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("question/{questionId}/reponses")
    List<ReponseDTO> findAllByQuestionId(@PathVariable final Long questionId);

    @Operation(description = "Obtention des Reponses pour une Session donnée") //swagger
    @ApiResponse(responseCode = "200", description = "La session a bien été trouvé.") //swagger
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("session/{sessionId}/reponses")
    List<ReponseDTO> findAllBySessionId(@PathVariable final Long sessionId);

    @Operation(description = "Affiche la reponse correspondant à l'id donné")
    @ApiResponse(responseCode = "200", description = "Entité réponse trouvée")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("reponse/{reponseId}")
    ReponseDTO findById(@PathVariable Long id);


    @Operation(description = "Mise à jour d'une entité réponse")
    @ApiResponse(responseCode = "202", description = "L'entité réponse à bien été mis à jour")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("reponse/{id}")
    void updateReponse(@PathVariable final Long id,@RequestBody final ReponseDTO reponseDTO);

}

