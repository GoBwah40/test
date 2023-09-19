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
import simplon.ebrairie.model.UsersDTO;
import simplon.ebrairie.service.UsersService;


@RestController
@RequestMapping(value = "/api/userss", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersResource {

    private final UsersService usersService;

    public UsersResource(final UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<UsersDTO>> getAllUserss() {
        return ResponseEntity.ok(usersService.findAll());
    }

    @GetMapping("/{idUsers}")
    public ResponseEntity<UsersDTO> getUsers(
            @PathVariable(name = "idUsers") final Integer idUsers) {
        return ResponseEntity.ok(usersService.get(idUsers));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createUsers(@RequestBody @Valid final UsersDTO usersDTO) {
        final Integer createdIdUsers = usersService.create(usersDTO);
        return new ResponseEntity<>(createdIdUsers, HttpStatus.CREATED);
    }

    @PutMapping("/{idUsers}")
    public ResponseEntity<Integer> updateUsers(
            @PathVariable(name = "idUsers") final Integer idUsers,
            @RequestBody @Valid final UsersDTO usersDTO) {
        usersService.update(idUsers, usersDTO);
        return ResponseEntity.ok(idUsers);
    }

    @DeleteMapping("/{idUsers}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUsers(@PathVariable(name = "idUsers") final Integer idUsers) {
        usersService.delete(idUsers);
        return ResponseEntity.noContent().build();
    }

}
