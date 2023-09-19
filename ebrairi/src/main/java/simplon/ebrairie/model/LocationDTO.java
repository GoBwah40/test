package simplon.ebrairie.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LocationDTO {

    private Integer idLocation;

    @NotNull
    private Integer row;

    @NotNull
    private Integer floor;

}
