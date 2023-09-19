package simplon.ebrairie.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import simplon.ebrairie.domain.Role;
import simplon.ebrairie.domain.Users;
import simplon.ebrairie.model.UsersDTO;
import simplon.ebrairie.repos.RoleRepository;
import simplon.ebrairie.repos.UsersRepository;
import simplon.ebrairie.util.NotFoundException;


@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;

    public UsersService(final UsersRepository usersRepository,
            final RoleRepository roleRepository) {
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
    }

    public List<UsersDTO> findAll() {
        final List<Users> userss = usersRepository.findAll(Sort.by("idUsers"));
        return userss.stream()
                .map(users -> mapToDTO(users, new UsersDTO()))
                .toList();
    }

    public UsersDTO get(final Integer idUsers) {
        return usersRepository.findById(idUsers)
                .map(users -> mapToDTO(users, new UsersDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final UsersDTO usersDTO) {
        final Users users = new Users();
        mapToEntity(usersDTO, users);
        return usersRepository.save(users).getIdUsers();
    }

    public void update(final Integer idUsers, final UsersDTO usersDTO) {
        final Users users = usersRepository.findById(idUsers)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usersDTO, users);
        usersRepository.save(users);
    }

    public void delete(final Integer idUsers) {
        usersRepository.deleteById(idUsers);
    }

    private UsersDTO mapToDTO(final Users users, final UsersDTO usersDTO) {
        usersDTO.setIdUsers(users.getIdUsers());
        usersDTO.setUsername(users.getUsername());
        usersDTO.setPassword(users.getPassword());
        usersDTO.setEmail(users.getEmail());
        usersDTO.setFirstname(users.getFirstname());
        usersDTO.setLastname(users.getLastname());
        usersDTO.setRoleIdrole(users.getRoleIdrole() == null ? null : users.getRoleIdrole().getIdrole());
        return usersDTO;
    }

    private Users mapToEntity(final UsersDTO usersDTO, final Users users) {
        users.setUsername(usersDTO.getUsername());
        users.setPassword(usersDTO.getPassword());
        users.setEmail(usersDTO.getEmail());
        users.setFirstname(usersDTO.getFirstname());
        users.setLastname(usersDTO.getLastname());
        final Role roleIdrole = usersDTO.getRoleIdrole() == null ? null : roleRepository.findById(usersDTO.getRoleIdrole())
                .orElseThrow(() -> new NotFoundException("roleIdrole not found"));
        users.setRoleIdrole(roleIdrole);
        return users;
    }

    public boolean usernameExists(final String username) {
        return usersRepository.existsByUsernameIgnoreCase(username);
    }

}
