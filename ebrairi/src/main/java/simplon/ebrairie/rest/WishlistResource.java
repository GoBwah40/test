package simplon.ebrairie.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simplon.ebrairie.model.WishlistDTO;
import simplon.ebrairie.service.WishlistService;


@RestController
@RequestMapping(value = "/api/wishlists", produces = MediaType.APPLICATION_JSON_VALUE)
public class WishlistResource {

    private final WishlistService wishlistService;

    public WishlistResource(final WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public ResponseEntity<List<WishlistDTO>> getAllWishlists() {
        return ResponseEntity.ok(wishlistService.findAll());
    }

    @GetMapping("/{idWishlist}")
    public ResponseEntity<WishlistDTO> getWishlist(
            @PathVariable(name = "idWishlist") final Integer idWishlist) {
        return ResponseEntity.ok(wishlistService.get(idWishlist));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createWishlist(
            @RequestBody @Valid final WishlistDTO wishlistDTO) {
        final Integer createdIdWishlist = wishlistService.create(wishlistDTO);
        return new ResponseEntity<>(createdIdWishlist, HttpStatus.CREATED);
    }

    @PutMapping("/{idWishlist}")
    public ResponseEntity<Integer> updateWishlist(
            @PathVariable(name = "idWishlist") final Integer idWishlist,
            @RequestBody @Valid final WishlistDTO wishlistDTO) {
        wishlistService.update(idWishlist, wishlistDTO);
        return ResponseEntity.ok(idWishlist);
    }

    @DeleteMapping("/{idWishlist}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteWishlist(
            @PathVariable(name = "idWishlist") final Integer idWishlist) {
        wishlistService.delete(idWishlist);
        return ResponseEntity.noContent().build();
    }

}
