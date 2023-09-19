package simplon.ebrairie.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import simplon.ebrairie.domain.Type;


public interface TypeRepository extends JpaRepository<Type, Integer> {

    boolean existsByTypeLabelIgnoreCase(String typeLabel);

}
