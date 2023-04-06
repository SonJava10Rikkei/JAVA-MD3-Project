package rikkei.academy.service.role;

import rikkei.academy.model.role.Role;
import rikkei.academy.model.role.RoleName;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceIMPL implements IRoleService {
    static List<Role> roleList = new ArrayList<>();

    static {
        roleList.add(new Role(1, RoleName.ADMIN));
        roleList.add(new Role(2, RoleName.PM));
        roleList.add(new Role(3, RoleName.USER));
    }

    @Override
    public List<Role> findAll() {
        return roleList;
    }

    @Override
    public void save(Role role) {
        roleList.add(role);
    }

    @Override
    public Role findByRoleName(RoleName roleName) {
        for (Role role : roleList) {
            return role;
        }
        return null;
    }
}
