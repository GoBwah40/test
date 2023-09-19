package simplon.ebrairie.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import simplon.ebrairie.domain.Location;
import simplon.ebrairie.model.LocationDTO;
import simplon.ebrairie.repos.LocationRepository;
import simplon.ebrairie.util.NotFoundException;


@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(final LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationDTO> findAll() {
        final List<Location> locations = locationRepository.findAll(Sort.by("idLocation"));
        return locations.stream()
                .map(location -> mapToDTO(location, new LocationDTO()))
                .toList();
    }

    public LocationDTO get(final Integer idLocation) {
        return locationRepository.findById(idLocation)
                .map(location -> mapToDTO(location, new LocationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final LocationDTO locationDTO) {
        final Location location = new Location();
        mapToEntity(locationDTO, location);
        return locationRepository.save(location).getIdLocation();
    }

    public void update(final Integer idLocation, final LocationDTO locationDTO) {
        final Location location = locationRepository.findById(idLocation)
                .orElseThrow(NotFoundException::new);
        mapToEntity(locationDTO, location);
        locationRepository.save(location);
    }

    public void delete(final Integer idLocation) {
        locationRepository.deleteById(idLocation);
    }

    private LocationDTO mapToDTO(final Location location, final LocationDTO locationDTO) {
        locationDTO.setIdLocation(location.getIdLocation());
        locationDTO.setRow(location.getRow());
        locationDTO.setFloor(location.getFloor());
        return locationDTO;
    }

    private Location mapToEntity(final LocationDTO locationDTO, final Location location) {
        location.setRow(locationDTO.getRow());
        location.setFloor(locationDTO.getFloor());
        return location;
    }

}
