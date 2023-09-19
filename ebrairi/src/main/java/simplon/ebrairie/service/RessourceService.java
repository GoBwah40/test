package simplon.ebrairie.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import simplon.ebrairie.domain.Author;
import simplon.ebrairie.domain.Location;
import simplon.ebrairie.domain.Ressource;
import simplon.ebrairie.domain.Type;
import simplon.ebrairie.model.RessourceDTO;
import simplon.ebrairie.repos.AuthorRepository;
import simplon.ebrairie.repos.LocationRepository;
import simplon.ebrairie.repos.RessourceRepository;
import simplon.ebrairie.repos.TypeRepository;
import simplon.ebrairie.util.NotFoundException;


@Service
public class RessourceService {

    private final RessourceRepository ressourceRepository;
    private final AuthorRepository authorRepository;
    private final TypeRepository typeRepository;
    private final LocationRepository locationRepository;

    public RessourceService(final RessourceRepository ressourceRepository,
            final AuthorRepository authorRepository, final TypeRepository typeRepository,
            final LocationRepository locationRepository) {
        this.ressourceRepository = ressourceRepository;
        this.authorRepository = authorRepository;
        this.typeRepository = typeRepository;
        this.locationRepository = locationRepository;
    }

    public List<RessourceDTO> findAll() {
        final List<Ressource> ressources = ressourceRepository.findAll(Sort.by("idRessource"));
        return ressources.stream()
                .map(ressource -> mapToDTO(ressource, new RessourceDTO()))
                .toList();
    }

    public RessourceDTO get(final Integer idRessource) {
        return ressourceRepository.findById(idRessource)
                .map(ressource -> mapToDTO(ressource, new RessourceDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final RessourceDTO ressourceDTO) {
        final Ressource ressource = new Ressource();
        mapToEntity(ressourceDTO, ressource);
        return ressourceRepository.save(ressource).getIdRessource();
    }

    public void update(final Integer idRessource, final RessourceDTO ressourceDTO) {
        final Ressource ressource = ressourceRepository.findById(idRessource)
                .orElseThrow(NotFoundException::new);
        mapToEntity(ressourceDTO, ressource);
        ressourceRepository.save(ressource);
    }

    public void delete(final Integer idRessource) {
        ressourceRepository.deleteById(idRessource);
    }

    private RessourceDTO mapToDTO(final Ressource ressource, final RessourceDTO ressourceDTO) {
        ressourceDTO.setIdRessource(ressource.getIdRessource());
        ressourceDTO.setTitle(ressource.getTitle());
        ressourceDTO.setDescription(ressource.getDescription());
        ressourceDTO.setQuantity(ressource.getQuantity());
        ressourceDTO.setAuthorIdAuthor(ressource.getAuthorIdAuthor() == null ? null : ressource.getAuthorIdAuthor().getIdAuthor());
        ressourceDTO.setTypeIdType(ressource.getTypeIdType() == null ? null : ressource.getTypeIdType().getIdType());
        ressourceDTO.setLocationIdLocation1(ressource.getLocationIdLocation1() == null ? null : ressource.getLocationIdLocation1().getIdLocation());
        return ressourceDTO;
    }

    private Ressource mapToEntity(final RessourceDTO ressourceDTO, final Ressource ressource) {
        ressource.setTitle(ressourceDTO.getTitle());
        ressource.setDescription(ressourceDTO.getDescription());
        ressource.setQuantity(ressourceDTO.getQuantity());
        final Author authorIdAuthor = ressourceDTO.getAuthorIdAuthor() == null ? null : authorRepository.findById(ressourceDTO.getAuthorIdAuthor())
                .orElseThrow(() -> new NotFoundException("authorIdAuthor not found"));
        ressource.setAuthorIdAuthor(authorIdAuthor);
        final Type typeIdType = ressourceDTO.getTypeIdType() == null ? null : typeRepository.findById(ressourceDTO.getTypeIdType())
                .orElseThrow(() -> new NotFoundException("typeIdType not found"));
        ressource.setTypeIdType(typeIdType);
        final Location locationIdLocation1 = ressourceDTO.getLocationIdLocation1() == null ? null : locationRepository.findById(ressourceDTO.getLocationIdLocation1())
                .orElseThrow(() -> new NotFoundException("locationIdLocation1 not found"));
        ressource.setLocationIdLocation1(locationIdLocation1);
        return ressource;
    }

}
