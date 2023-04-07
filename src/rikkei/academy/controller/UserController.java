package rikkei.academy.controller;

import rikkei.academy.config.Config;
import rikkei.academy.config.PathConfig;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessage;
import rikkei.academy.model.User;
import rikkei.academy.model.role.Role;
import rikkei.academy.model.role.RoleName;
import rikkei.academy.service.role.IRoleService;
import rikkei.academy.service.role.RoleServiceIMPL;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {
    private IUserService userService = new UserServiceIMPL();
    private IRoleService roleService = new RoleServiceIMPL();

    public List<User> getUserList() {
        return userService.findAll();
    }

    public ResponseMessage register(SignUpDTO signUpDTO) {
        if (userService.existedByUserName(signUpDTO.getUsername())) {
            return new ResponseMessage("Username_Existed!!!");
        }
        if (userService.existedByMail(signUpDTO.getEmail())) {
            return new ResponseMessage("Email_Existed!!!");
        }
        Set<String> strRoles = signUpDTO.getRoles();
        Set<Role> roles = new HashSet<>();
        for (String role : strRoles) {
            switch (role) {
                case "pm":
                    roles.add(roleService.findByRoleName(RoleName.PM));
                    break;
                case "admin":
                    roles.add(roleService.findByRoleName(RoleName.ADMIN));
                    break;
                default:
//                    "user":
                    roles.add(roleService.findByRoleName(RoleName.USER));
//                    break;
//                default:
//                    return new ResponseMessage("Invalid role");

            }
        }
        User user = new User(
                signUpDTO.getId(),
                signUpDTO.getName(),
                signUpDTO.getUsername(),
                signUpDTO.getEmail(),
                signUpDTO.getPassword(),
                roles
        );
        userService.save(user);
//        System.out.println(getUserList());
        return new ResponseMessage("Create success");

    }

    public ResponseMessage login(SignUpDTO signUpDTO) {
        if (userService.checkLogin(signUpDTO.getUsername(), signUpDTO.getPassword())) {
            User user = userService.findByUserName(signUpDTO.getUsername());

            List<User> userLogin = new ArrayList<>();
            userLogin.add(user);
            new Config<User>().writeToFile(PathConfig.PATH_USER_PRINCIPAL, userLogin);
            return new ResponseMessage("Login-Success");
        } else {
            return new ResponseMessage("Login-Failed!!!");
        }
    }

    public User getCurrenUser() {
        return userService.getCurrenUser();
    }

    public void logOut() {
        new Config<User>().writeToFile(PathConfig.PATH_USER_PRINCIPAL, null);
    }

    public void changeStatus(int id) {
        userService.changeStatus(id);
    }

    public User findById(int id) {
        return userService.findById(id);
    }

    public List<User> findByRoleName(RoleName... roleNames) {
        return userService.findByRole(roleNames);
    }

    public boolean existByEmail(String email) {
        return userService.existedByMail(email);
    }

    public void deleteUserById(int id) {
        userService.deleteById(id);
    }

    public void setRole(int id, Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();
        for (String role : strRoles) {
            switch (role) {
                case "ADMIN":
                    roles.add(roleService.findByRoleName(RoleName.ADMIN));
                    break;
                case "PM":
                    roles.add(roleService.findByRoleName(RoleName.PM));
                    break;
                default:
//                    "USER":
                    roles.add(roleService.findByRoleName(RoleName.USER));
//                    break;
            }
        }
        userService.changeRole(id, roles);
    }

    public void upDateProFile(User user) {
        userService.changeProFile(user);
    }

}
