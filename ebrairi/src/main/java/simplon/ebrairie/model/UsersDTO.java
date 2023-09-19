package simplon.ebrairie.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsersDTO {

    private Integer idUsers;

    @Size(max = 45)
    private String username;


    @NotNull
    @Size(max = 45)
    private String email;

    @NotNull
    @Size(max = 45)
    private String firstname;

    @NotNull
    @Size(max = 45)
    private String lastname;

    @NotNull
    private Integer roleIdrole;

}
