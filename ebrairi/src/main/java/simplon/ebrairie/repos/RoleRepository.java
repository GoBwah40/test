package simplon.ebrairie.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import simplon.ebrairie.domain.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {
}
