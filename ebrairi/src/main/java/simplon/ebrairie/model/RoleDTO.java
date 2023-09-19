package simplon.ebrairie.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RoleDTO {

    private Integer idrole;

    @NotNull
    @Size(max = 45)
    private String role;

}
