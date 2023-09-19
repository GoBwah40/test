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
import simplon.ebrairie.model.LocationDTO;
import simplon.ebrairie.service.LocationService;


@RestController
@RequestMapping(value = "/api/locations", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationResource {

    private final LocationService locationService;

    public LocationResource(final LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        return ResponseEntity.ok(locationService.findAll());
    }

    @GetMapping("/{idLocation}")
    public ResponseEntity<LocationDTO> getLocation(
            @PathVariable(name = "idLocation") final Integer idLocation) {
        return ResponseEntity.ok(locationService.get(idLocation));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createLocation(
            @RequestBody @Valid final LocationDTO locationDTO) {
        final Integer createdIdLocation = locationService.create(locationDTO);
        return new ResponseEntity<>(createdIdLocation, HttpStatus.CREATED);
    }

    @PutMapping("/{idLocation}")
    public ResponseEntity<Integer> updateLocation(
            @PathVariable(name = "idLocation") final Integer idLocation,
            @RequestBody @Valid final LocationDTO locationDTO) {
        locationService.update(idLocation, locationDTO);
        return ResponseEntity.ok(idLocation);
    }

    @DeleteMapping("/{idLocation}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteLocation(
            @PathVariable(name = "idLocation") final Integer idLocation) {
        locationService.delete(idLocation);
        return ResponseEntity.noContent().build();
    }

}
