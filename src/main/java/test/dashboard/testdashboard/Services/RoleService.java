package test.dashboard.testdashboard.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.dashboard.testdashboard.Entities.Role;
import test.dashboard.testdashboard.Repositories.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(String id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(String id, Role roleDetails) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role != null) {
            role.setName(roleDetails.getName());
            role.setAuthorities(roleDetails.getAuthorities());
            role.setUsers(roleDetails.getUsers());
            return roleRepository.save(role);
        }
        return null;
    }

    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }
}
