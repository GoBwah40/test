package simplon.ebrairie.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import simplon.ebrairie.domain.Ressource;
import simplon.ebrairie.domain.Users;
import simplon.ebrairie.domain.Wishlist;
import simplon.ebrairie.model.WishlistDTO;
import simplon.ebrairie.repos.RessourceRepository;
import simplon.ebrairie.repos.UsersRepository;
import simplon.ebrairie.repos.WishlistRepository;
import simplon.ebrairie.util.NotFoundException;


@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UsersRepository usersRepository;
    private final RessourceRepository ressourceRepository;

    public WishlistService(final WishlistRepository wishlistRepository,
            final UsersRepository usersRepository, final RessourceRepository ressourceRepository) {
        this.wishlistRepository = wishlistRepository;
        this.usersRepository = usersRepository;
        this.ressourceRepository = ressourceRepository;
    }

    public List<WishlistDTO> findAll() {
        final List<Wishlist> wishlists = wishlistRepository.findAll(Sort.by("idWishlist"));
        return wishlists.stream()
                .map(wishlist -> mapToDTO(wishlist, new WishlistDTO()))
                .toList();
    }

    public WishlistDTO get(final Integer idWishlist) {
        return wishlistRepository.findById(idWishlist)
                .map(wishlist -> mapToDTO(wishlist, new WishlistDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final WishlistDTO wishlistDTO) {
        final Wishlist wishlist = new Wishlist();
        mapToEntity(wishlistDTO, wishlist);
        return wishlistRepository.save(wishlist).getIdWishlist();
    }

    public void update(final Integer idWishlist, final WishlistDTO wishlistDTO) {
        final Wishlist wishlist = wishlistRepository.findById(idWishlist)
                .orElseThrow(NotFoundException::new);
        mapToEntity(wishlistDTO, wishlist);
        wishlistRepository.save(wishlist);
    }

    public void delete(final Integer idWishlist) {
        wishlistRepository.deleteById(idWishlist);
    }

    private WishlistDTO mapToDTO(final Wishlist wishlist, final WishlistDTO wishlistDTO) {
        wishlistDTO.setIdWishlist(wishlist.getIdWishlist());
        wishlistDTO.setCreated(wishlist.getCreated());
        wishlistDTO.setUsersIdUsers(wishlist.getUsersIdUsers() == null ? null : wishlist.getUsersIdUsers().getIdUsers());
        wishlistDTO.setRessourceIdRessource(wishlist.getRessourceIdRessource() == null ? null : wishlist.getRessourceIdRessource().getIdRessource());
        return wishlistDTO;
    }

    private Wishlist mapToEntity(final WishlistDTO wishlistDTO, final Wishlist wishlist) {
        wishlist.setCreated(wishlistDTO.getCreated());
        final Users usersIdUsers = wishlistDTO.getUsersIdUsers() == null ? null : usersRepository.findById(wishlistDTO.getUsersIdUsers())
                .orElseThrow(() -> new NotFoundException("usersIdUsers not found"));
        wishlist.setUsersIdUsers(usersIdUsers);
        final Ressource ressourceIdRessource = wishlistDTO.getRessourceIdRessource() == null ? null : ressourceRepository.findById(wishlistDTO.getRessourceIdRessource())
                .orElseThrow(() -> new NotFoundException("ressourceIdRessource not found"));
        wishlist.setRessourceIdRessource(ressourceIdRessource);
        return wishlist;
    }

}
