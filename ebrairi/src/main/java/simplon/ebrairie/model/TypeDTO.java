package simplon.ebrairie.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TypeDTO {

    private Integer idType;

    @NotNull
    @Size(max = 45)
    private String typeLabel;

    @NotNull
    private Integer categoryIdstyle;

}
