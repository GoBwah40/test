package simplon.ebrairie.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import simplon.ebrairie.domain.Historic;


public interface HistoricRepository extends JpaRepository<Historic, Integer> {
}
