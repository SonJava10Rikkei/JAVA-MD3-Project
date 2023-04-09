package rikkei.academy.service.role;

import rikkei.academy.model.role.Role;
import rikkei.academy.model.role.RoleName;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceIMPL implements IRoleService {
    public static List<Role> roleList = new ArrayList<>();

    static {
        roleList.add(new Role(1, RoleName.USER));
        roleList.add(new Role(2, RoleName.PM));
        roleList.add(new Role(3, RoleName.ADMIN));
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
        for (int i = 0; i < roleList.size(); i++) {
            if(roleList.get(i).getRoleName()==roleName){
                return roleList.get(i);
            }
        }
        return null;
    }
}
