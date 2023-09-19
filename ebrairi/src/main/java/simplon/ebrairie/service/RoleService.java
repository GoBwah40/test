package simplon.ebrairie.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import simplon.ebrairie.domain.Role;
import simplon.ebrairie.model.RoleDTO;
import simplon.ebrairie.repos.RoleRepository;
import simplon.ebrairie.util.NotFoundException;


@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDTO> findAll() {
        final List<Role> roles = roleRepository.findAll(Sort.by("idrole"));
        return roles.stream()
                .map(role -> mapToDTO(role, new RoleDTO()))
                .toList();
    }

    public RoleDTO get(final Integer idrole) {
        return roleRepository.findById(idrole)
                .map(role -> mapToDTO(role, new RoleDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final RoleDTO roleDTO) {
        final Role role = new Role();
        mapToEntity(roleDTO, role);
        return roleRepository.save(role).getIdrole();
    }

    public void update(final Integer idrole, final RoleDTO roleDTO) {
        final Role role = roleRepository.findById(idrole)
                .orElseThrow(NotFoundException::new);
        mapToEntity(roleDTO, role);
        roleRepository.save(role);
    }

    public void delete(final Integer idrole) {
        roleRepository.deleteById(idrole);
    }

    private RoleDTO mapToDTO(final Role role, final RoleDTO roleDTO) {
        roleDTO.setIdrole(role.getIdrole());
        roleDTO.setRole(role.getRole());
        return roleDTO;
    }

    private Role mapToEntity(final RoleDTO roleDTO, final Role role) {
        role.setRole(roleDTO.getRole());
        return role;
    }

}
