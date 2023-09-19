package simplon.ebrairie.model;

import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import simplon.ebrairie.domain.Ressource;


@Getter
@Setter
public class AuthorDTO {

    private Integer idAuthor;

    @NotNull
    @Size(max = 45)
    private String fisrtname;

    @NotNull
    @Size(max = 45)
    private String lastanme;

    @Size(max = 450)
    private String bio;
    
    private Set<Ressource> ressources;

}
