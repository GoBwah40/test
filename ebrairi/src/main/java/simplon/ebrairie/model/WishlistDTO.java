package simplon.ebrairie.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WishlistDTO {

    private Integer idWishlist;

    @NotNull
    private LocalDate created;

    @NotNull
    private Integer usersIdUsers;

    @NotNull
    private Integer ressourceIdRessource;

}
