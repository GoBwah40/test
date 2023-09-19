package simplon.ebrairie.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HistoricDTO {

    private Integer idHistoric;

    private LocalDate datestart;

    private LocalDate dateend;

    @NotNull
    private Integer users;

    @NotNull
    private Integer ressource;

}
