package simplon.ebrairie.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import simplon.ebrairie.domain.Users;


public interface UsersRepository extends JpaRepository<Users, Integer> {

    boolean existsByUsernameIgnoreCase(String username);

}
