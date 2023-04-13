package rikkei.academy.controller;

import rikkei.academy.config.Config;
import rikkei.academy.config.PathConfig;
import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessage;
import rikkei.academy.model.User;
import rikkei.academy.model.order.Cart;
import rikkei.academy.model.role.Role;
import rikkei.academy.model.role.RoleName;
import rikkei.academy.service.oderService.CartServiceIMPL;
import rikkei.academy.service.oderService.ICart;
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
    private ICart cartService = new CartServiceIMPL();

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
        Set<Role> roleSet = new HashSet<>();
        Set<String> strRole = signUpDTO.getRoles();
        strRole.forEach(role -> {
            switch (role) {
                case "admin":
                    roleSet.add(roleService.findByRoleName(RoleName.ADMIN));
                    break;
                case "pm":
                    roleSet.add(roleService.findByRoleName(RoleName.PM));
                default:
                    roleSet.add(roleService.findByRoleName(RoleName.USER));
            }
        });
        User user = new User(
                signUpDTO.getId(),
                signUpDTO.getName(),
                signUpDTO.getUsername(),
                signUpDTO.getEmail(),
                signUpDTO.getPassword(),
                roleSet
        );
        Cart cart = new Cart(signUpDTO.getId(), user);
        userService.save(user);
        cartService.save(cart);
        // Hiển thị danh sách user
//        System.out.println(getUserList());
        return new ResponseMessage("Create success");

    }

    public ResponseMessage login(SignInDTO signInDTO) {
        if (userService.checkLogin(signInDTO.getUsername(), signInDTO.getPassword())) {
            User user = userService.findByUserName(signInDTO.getUsername());

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
        Set<Role> roleSet = new HashSet<>();
        for (String role : strRoles) {
            switch (role) {
                case "admin":
                    roleSet.add(roleService.findByRoleName(RoleName.ADMIN));
                    break;
                case "pm":
                    roleSet.add(roleService.findByRoleName(RoleName.PM));
                    break;
                case "user":
                    roleSet.add(roleService.findByRoleName(RoleName.USER));
                    break;

            }
        }
        userService.changeRole(id, roleSet);
    }

    public void upDateProFile(User user) {
        userService.changeProFile(user);
    }

}
