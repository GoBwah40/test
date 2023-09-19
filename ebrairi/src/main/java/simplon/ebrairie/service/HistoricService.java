package simplon.ebrairie.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import simplon.ebrairie.domain.Historic;
import simplon.ebrairie.domain.Ressource;
import simplon.ebrairie.domain.Users;
import simplon.ebrairie.model.HistoricDTO;
import simplon.ebrairie.repos.HistoricRepository;
import simplon.ebrairie.repos.RessourceRepository;
import simplon.ebrairie.repos.UsersRepository;
import simplon.ebrairie.util.NotFoundException;


@Service
public class HistoricService {

    private final HistoricRepository historicRepository;
    private final UsersRepository usersRepository;
    private final RessourceRepository ressourceRepository;

    public HistoricService(final HistoricRepository historicRepository,
            final UsersRepository usersRepository, final RessourceRepository ressourceRepository) {
        this.historicRepository = historicRepository;
        this.usersRepository = usersRepository;
        this.ressourceRepository = ressourceRepository;
    }

    public List<HistoricDTO> findAll() {
        final List<Historic> historics = historicRepository.findAll(Sort.by("idHistoric"));
        return historics.stream()
                .map(historic -> mapToDTO(historic, new HistoricDTO()))
                .toList();
    }

    public HistoricDTO get(final Integer idHistoric) {
        return historicRepository.findById(idHistoric)
                .map(historic -> mapToDTO(historic, new HistoricDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final HistoricDTO historicDTO) {
        final Historic historic = new Historic();
        mapToEntity(historicDTO, historic);
        return historicRepository.save(historic).getIdHistoric();
    }

    public void update(final Integer idHistoric, final HistoricDTO historicDTO) {
        final Historic historic = historicRepository.findById(idHistoric)
                .orElseThrow(NotFoundException::new);
        mapToEntity(historicDTO, historic);
        historicRepository.save(historic);
    }

    public void delete(final Integer idHistoric) {
        historicRepository.deleteById(idHistoric);
    }

    private HistoricDTO mapToDTO(final Historic historic, final HistoricDTO historicDTO) {
        historicDTO.setIdHistoric(historic.getIdHistoric());
        historicDTO.setDatestart(historic.getDatestart());
        historicDTO.setDateend(historic.getDateend());
        historicDTO.setUsersIdUsers(historic.getUsersIdUsers() == null ? null : historic.getUsersIdUsers().getIdUsers());
        historicDTO.setRessourceIdRessource(historic.getRessourceIdRessource() == null ? null : historic.getRessourceIdRessource().getIdRessource());
        return historicDTO;
    }

    private Historic mapToEntity(final HistoricDTO historicDTO, final Historic historic) {
        historic.setDatestart(historicDTO.getDatestart());
        historic.setDateend(historicDTO.getDateend());
        final Users usersIdUsers = historicDTO.getUsersIdUsers() == null ? null : usersRepository.findById(historicDTO.getUsersIdUsers())
                .orElseThrow(() -> new NotFoundException("usersIdUsers not found"));
        historic.setUsersIdUsers(usersIdUsers);
        final Ressource ressourceIdRessource = historicDTO.getRessourceIdRessource() == null ? null : ressourceRepository.findById(historicDTO.getRessourceIdRessource())
                .orElseThrow(() -> new NotFoundException("ressourceIdRessource not found"));
        historic.setRessourceIdRessource(ressourceIdRessource);
        return historic;
    }

}
