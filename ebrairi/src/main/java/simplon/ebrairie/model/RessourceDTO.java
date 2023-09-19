package simplon.ebrairie.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RessourceDTO {

    private Integer idRessource;

    @NotNull
    @Size(max = 45)
    private String title;

    @NotNull
    @Size(max = 450)
    private String description;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer authorIdAuthor;

    @NotNull
    private Integer typeIdType;

    @NotNull
    private Integer locationIdLocation1;

}
