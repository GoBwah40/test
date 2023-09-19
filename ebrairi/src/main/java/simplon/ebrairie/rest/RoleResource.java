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
import simplon.ebrairie.model.RoleDTO;
import simplon.ebrairie.service.RoleService;


@RestController
@RequestMapping(value = "/api/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleResource {

    private final RoleService roleService;

    public RoleResource(final RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{idrole}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable(name = "idrole") final Integer idrole) {
        return ResponseEntity.ok(roleService.get(idrole));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createRole(@RequestBody @Valid final RoleDTO roleDTO) {
        final Integer createdIdrole = roleService.create(roleDTO);
        return new ResponseEntity<>(createdIdrole, HttpStatus.CREATED);
    }

    @PutMapping("/{idrole}")
    public ResponseEntity<Integer> updateRole(@PathVariable(name = "idrole") final Integer idrole,
            @RequestBody @Valid final RoleDTO roleDTO) {
        roleService.update(idrole, roleDTO);
        return ResponseEntity.ok(idrole);
    }

    @DeleteMapping("/{idrole}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteRole(@PathVariable(name = "idrole") final Integer idrole) {
        roleService.delete(idrole);
        return ResponseEntity.noContent().build();
    }

}
