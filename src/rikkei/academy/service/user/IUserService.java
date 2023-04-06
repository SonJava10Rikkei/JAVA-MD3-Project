package rikkei.academy.service.user;

import rikkei.academy.model.User;
import rikkei.academy.model.role.Role;
import rikkei.academy.model.role.RoleName;
import rikkei.academy.service.IGenericService;

import java.util.List;
import java.util.Set;

public interface IUserService extends IGenericService<User> {
    boolean existedByUserName(String username);

    boolean existedByMail(String mail);

    boolean checkLogin(String username, String password);

    User findByUserName(String username);

    User getCurrenUser();

    void changeRole(int id, Set<Role> roles);

    List<User> findByRole(RoleName... roleNames);

    void changeStatus(int id);

    void changeProFile(User user);
}
