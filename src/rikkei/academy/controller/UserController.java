package rikkei.academy.controller;

import rikkei.academy.config.Config;
import rikkei.academy.config.PathConfig;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessenger;
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

    IRoleService roleService = new RoleServiceIMPL();
    IUserService userService = new UserServiceIMPL();

    public List<User> getUserList() {
        return userService.findAll();
    }

    public ResponseMessenger register(SignUpDTO sinUpDTO) {
        if (userService.existedByUserName(sinUpDTO.getUsername())) {
            return new ResponseMessenger("Username_Existed!!!");
        }
        if (userService.existedByMail(sinUpDTO.getEmail())){
            return new ResponseMessenger("Email_Existed!!!");
        }
        Set<String> strRoles = sinUpDTO.getRoles();
        Set<Role> roles = new HashSet<>();
        for (String role: strRoles) {
            switch (role){
                case "pm":
                    roles.add(roleService.findByRoleName(RoleName.PM));
                    break;
                case "admin":
                    roles.add(roleService.findByRoleName(RoleName.ADMIN));
                    break;
                case "user":
                    roles.add(roleService.findByRoleName(RoleName.USER));
                    break;
                default:
                    return new ResponseMessenger("Invalid role");

            }
        }
        User user = new User(
                sinUpDTO.getId(),
                sinUpDTO.getName(),
                sinUpDTO.getUsername(),
                sinUpDTO.getEmail(),
                sinUpDTO.getPassword(),
                roles
        );
        userService.save(user);
        getUserList();
        return new ResponseMessenger("Create success");

    }
    public ResponseMessenger login(SignUpDTO sinUpDTO){
        if (userService.checkLogin(sinUpDTO.getUsername(),sinUpDTO.getPassword())){
            User user = userService.findByUserName(sinUpDTO.getUsername());

            List<User> userLogin = new ArrayList<>();
            userLogin.add(user);
            new Config<User>().writeToFile(PathConfig.PATH_USER_PRINCIPAL,userLogin);
            return new ResponseMessenger("Login-Success");
        }else {
            return new ResponseMessenger("Login-Failed!!!");
        }
    }
    public User grtCurrenUser(){
        return userService.getCurrenUser();
    }

    public void logOut(){
        new Config<User>().writeToFile(PathConfig.PATH_USER_PRINCIPAL,null);
    }
    public void changeStatus(int id){
        userService.changeStatus(id);
    }
    public User findById(int id){
        return userService.findById(id);
    }
    public List<User> findByRoleName(RoleName... roleNames){
        return userService.findByRole(roleNames);
    }
    public boolean existByEmail(String email){
        return userService.existedByMail(email);
    }
    public void deleteUserById(int id){
        userService.deleteById(id);
    }
    public void setRole(int id, Set<String> strRoles){
        Set<Role> roles = new HashSet<>();
        for (String role: strRoles) {
            switch (role){
                case "admin":
                    roles.add(roleService.findByRoleName(RoleName.ADMIN));
                    break;
                case "pm":
                    roles.add(roleService.findByRoleName(RoleName.PM));
                    break;
                case "user":
                    roles.add(roleService.findByRoleName(RoleName.USER));
                    break;
            }
        }
        userService.changeRole(id,roles);
    }

    public void upDateProFile(User user){
        userService.changeProFile(user);
    }

}
