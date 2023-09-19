package simplon.ebrairie.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simplon.ebrairie.model.RessourceDTO;
import simplon.ebrairie.service.RessourceService;


@RestController
@RequestMapping(value = "/api/ressources", produces = MediaType.APPLICATION_JSON_VALUE)
public class RessourceResource {

    private final RessourceService ressourceService;

    public RessourceResource(final RessourceService ressourceService) {
        this.ressourceService = ressourceService;
    }

    @GetMapping
    public ResponseEntity<List<RessourceDTO>> getAllRessources() {
        return ResponseEntity.ok(ressourceService.findAll());
    }

    @GetMapping("/{idRessource}")
    public ResponseEntity<RessourceDTO> getRessource(
            @PathVariable(name = "idRessource") final Integer idRessource) {
        return ResponseEntity.ok(ressourceService.get(idRessource));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createRessource(
            @RequestBody @Valid final RessourceDTO ressourceDTO) {
        final Integer createdIdRessource = ressourceService.create(ressourceDTO);
        return new ResponseEntity<>(createdIdRessource, HttpStatus.CREATED);
    }

    @PutMapping("/{idRessource}")
    public ResponseEntity<Integer> updateRessource(
            @PathVariable(name = "idRessource") final Integer idRessource,
            @RequestBody @Valid final RessourceDTO ressourceDTO) {
        ressourceService.update(idRessource, ressourceDTO);
        return ResponseEntity.ok(idRessource);
    }

    @DeleteMapping("/{idRessource}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteRessource(
            @PathVariable(name = "idRessource") final Integer idRessource) {
        ressourceService.delete(idRessource);
        return ResponseEntity.noContent().build();
    }

}
