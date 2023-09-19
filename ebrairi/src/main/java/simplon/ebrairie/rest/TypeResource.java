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
import simplon.ebrairie.model.TypeDTO;
import simplon.ebrairie.service.TypeService;


@RestController
@RequestMapping(value = "/api/types", produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeResource {

    private final TypeService typeService;

    public TypeResource(final TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public ResponseEntity<List<TypeDTO>> getAllTypes() {
        return ResponseEntity.ok(typeService.findAll());
    }

    @GetMapping("/{idType}")
    public ResponseEntity<TypeDTO> getType(@PathVariable(name = "idType") final Integer idType) {
        return ResponseEntity.ok(typeService.get(idType));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createType(@RequestBody @Valid final TypeDTO typeDTO) {
        final Integer createdIdType = typeService.create(typeDTO);
        return new ResponseEntity<>(createdIdType, HttpStatus.CREATED);
    }

    @PutMapping("/{idType}")
    public ResponseEntity<Integer> updateType(@PathVariable(name = "idType") final Integer idType,
            @RequestBody @Valid final TypeDTO typeDTO) {
        typeService.update(idType, typeDTO);
        return ResponseEntity.ok(idType);
    }

    @DeleteMapping("/{idType}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteType(@PathVariable(name = "idType") final Integer idType) {
        typeService.delete(idType);
        return ResponseEntity.noContent().build();
    }

}
