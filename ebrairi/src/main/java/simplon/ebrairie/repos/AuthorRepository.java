package simplon.ebrairie.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import simplon.ebrairie.domain.Author;


public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
