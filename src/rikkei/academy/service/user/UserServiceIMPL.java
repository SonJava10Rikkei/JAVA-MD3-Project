package rikkei.academy.service.user;

import rikkei.academy.config.Config;
import rikkei.academy.config.PathConfig;
import rikkei.academy.model.User;
import rikkei.academy.model.role.Role;
import rikkei.academy.model.role.RoleName;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserServiceIMPL implements IUserService {
     List<User> userList = new Config<User>().readFormFile(PathConfig.PATH_USER);

    @Override
    public List<User> findAll() {
        new Config<User>().writeToFile(PathConfig.PATH_USER, userList);
        List<User> list = new ArrayList<>();
        list.add(getCurrenUser());
        new Config<User>().writeToFile(PathConfig.PATH_USER_PRINCIPAL, list);
        return userList;
    }


    @Override
    public void save(User user) {
        userList.add(user);
        new Config<User>().writeToFile(PathConfig.PATH_USER, userList);
    }

    @Override
    public User findById(int id) {
        return userList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public void deleteById(int id) {
        userList.remove(findById(id));
        new Config<User>().writeToFile(PathConfig.PATH_USER, userList);

    }

    @Override
    public boolean existedByUserName(String username) {
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existedByMail(String mail) {
        for (int i = 0; i < userList.size(); i++) {
            if (mail.equals(userList.get(i).getEmail())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i).getUsername()) && password.equals(userList.get(i).getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User findByUserName(String username) {
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i).getUsername())) {
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public User getCurrenUser() {
        List<User> userList1 = new Config<User>().readFormFile(PathConfig.PATH_USER_PRINCIPAL);
        if (userList1 != null) {
            if (userList1.size() != 0) {
                User user = new Config<User>().readFormFile(PathConfig.PATH_USER_PRINCIPAL).get(0);
                return user;
            }
        }
        return null;
    }

    @Override
    public void changeRole(int id, Set<Role> roles) {
        findById(id).setRoles(roles);
        new Config<User>().writeToFile(PathConfig.PATH_USER, userList);
    }

    @Override
    public List<User> findByRole(RoleName... roleNames) {
//        List<User> find = new ArrayList<>();
//        for (User user: userList) {
//            for (RoleName role: roleNames) {
//                if (user.getListRole() == role){
//                    find.add(user);
//                }
//            }
//
//        }
//        return find;
        return null;
    }

    @Override
    public void changeStatus(int id) {
        User user = findById(id);
        user.setStatus(!user.isStatus());
        new Config<User>().writeToFile(PathConfig.PATH_USER, userList);
    }

    @Override
    public void changeProFile(User user) {
        User user1 = findByUserName(user.getUsername());
        user1.setName(user.getName());
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        new Config<User>().writeToFile(PathConfig.PATH_USER_PRINCIPAL, userList);

    }
}
