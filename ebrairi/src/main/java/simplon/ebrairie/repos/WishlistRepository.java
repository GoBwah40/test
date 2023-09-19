package simplon.ebrairie.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import simplon.ebrairie.domain.Wishlist;


public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
}
