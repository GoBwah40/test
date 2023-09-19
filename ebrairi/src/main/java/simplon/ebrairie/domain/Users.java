package simplon.ebrairie.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Users {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsers;

    @Column(unique = true, length = 45)
    private String username;

    @Column(nullable = false, length = 450)
    private String password;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(nullable = false, length = 45)
    private String firstname;

    @Column(nullable = false, length = 45)
    private String lastname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_idrole_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "usersIdUsers")
    private Set<Historic> historics;

    @OneToMany(mappedBy = "usersIdUsers")
    private Set<Wishlist> wishlists;

}
