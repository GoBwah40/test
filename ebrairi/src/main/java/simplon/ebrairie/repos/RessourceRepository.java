package simplon.ebrairie.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import simplon.ebrairie.domain.Ressource;


public interface RessourceRepository extends JpaRepository<Ressource, Integer> {
}
