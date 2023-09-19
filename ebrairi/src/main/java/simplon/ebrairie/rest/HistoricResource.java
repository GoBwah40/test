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
import simplon.ebrairie.model.HistoricDTO;
import simplon.ebrairie.service.HistoricService;


@RestController
@RequestMapping(value = "/api/historics", produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoricResource {

    private final HistoricService historicService;

    public HistoricResource(final HistoricService historicService) {
        this.historicService = historicService;
    }

    @GetMapping
    public ResponseEntity<List<HistoricDTO>> getAllHistorics() {
        return ResponseEntity.ok(historicService.findAll());
    }

    @GetMapping("/{idHistoric}")
    public ResponseEntity<HistoricDTO> getHistoric(
            @PathVariable(name = "idHistoric") final Integer idHistoric) {
        return ResponseEntity.ok(historicService.get(idHistoric));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createHistoric(
            @RequestBody @Valid final HistoricDTO historicDTO) {
        final Integer createdIdHistoric = historicService.create(historicDTO);
        return new ResponseEntity<>(createdIdHistoric, HttpStatus.CREATED);
    }

    @PutMapping("/{idHistoric}")
    public ResponseEntity<Integer> updateHistoric(
            @PathVariable(name = "idHistoric") final Integer idHistoric,
            @RequestBody @Valid final HistoricDTO historicDTO) {
        historicService.update(idHistoric, historicDTO);
        return ResponseEntity.ok(idHistoric);
    }

    @DeleteMapping("/{idHistoric}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteHistoric(
            @PathVariable(name = "idHistoric") final Integer idHistoric) {
        historicService.delete(idHistoric);
        return ResponseEntity.noContent().build();
    }

}
