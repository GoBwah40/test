package simplon.ebrairie.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import simplon.ebrairie.domain.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
