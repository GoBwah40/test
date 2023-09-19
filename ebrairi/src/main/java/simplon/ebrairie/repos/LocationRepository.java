package simplon.ebrairie.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import simplon.ebrairie.domain.Location;


public interface LocationRepository extends JpaRepository<Location, Integer> {
}
