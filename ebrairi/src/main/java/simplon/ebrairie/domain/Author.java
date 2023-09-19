package simplon.ebrairie.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Author {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAuthor;

    @Column(nullable = false, length = 45)
    private String fisrtname;

    @Column(nullable = false, length = 45)
    private String lastanme;

    @Column(length = 450)
    private String bio;

    @OneToMany(mappedBy = "authorIdAuthor")
    private Set<Ressource> ressources;

}
