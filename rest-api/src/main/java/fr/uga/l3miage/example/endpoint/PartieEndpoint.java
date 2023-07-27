package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.response.PartieDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Partie tag") //swagger
@CrossOrigin
@RestController
public interface PartieEndpoint {
    @Operation(description = "Création d'une entité Partie") //swagger
    @ApiResponse(responseCode = "201", description = "L'entité Partie a bien été créée.") //swagger
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("miahoot/{miahootId}/partie")
    void createEntityPartie(@PathVariable final Long miahootId);

    @Operation(description = "Visualisation de toutes les entités partie") //swagger
    @ApiResponse(responseCode = "200", description = "Entités bien recherchées.") //swagger
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("partie/")
    List<PartieDTO> getAll();


    @Operation(description = "Obtention des Parties pour une Partie donnée") //swagger
    @ApiResponse(responseCode = "200", description = "Le Miahoot a bien été trouvé.") //swagger
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("miahoot/{miahootId}/parties")
    List<PartieDTO> findAllByMiahootId(@PathVariable final Long miahootId);

}
