package rikkei.academy.view.viewUser;

import rikkei.academy.config.validate.InputConfig;
import rikkei.academy.config.validate.ValidateInput;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;
import rikkei.academy.model.role.RoleName;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewUserManage {
    UserController userController = new UserController();
    User currenUser = userController.grtCurrenUser();
    List<User> userList = userController.getUserList();
    public ViewUserManage() {
        System.out.println("***************USER MANAGE********************");
        System.out.println("1: Change Role");
        System.out.println("2: BlockUser");
        System.out.println("3: DeleteUser");
        System.out.println("4: Edit Profile");
        System.out.println("5: Change password");
        System.out.println("6: back");
        int choice = ValidateInput.validateInt();
        switch (choice) {
            case 1:
                this.formChangeRole();
                break;
            case 2:
                this.formBlockUser();

                break;
            case 3:
                this.formDeleteUser();
                break;
            case 4:
//                new ViewChangeProFile().menuProFile();
                break;
            case 5:
//                new ViewChangeProFile().formChangePassword();
                break;
            case 6:
//                this.backMenu();
                break;
            default:
        }
//        menu();

    }
//    private void backMenu(){
//        new ViewHome();
//    }

    private void formChangeRole() {
        System.out.println("****************CHANGE ROLE************************");
        List<User> userList = new ArrayList<>(this.userList);
        User current = null;
        for (User user : userList) {
            if (user.getUsername().equals(currenUser.getUsername())) {
                current = user;
            }
        }
        userList.remove(current);
        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME", "ROLE");
        userList.forEach(user -> {
            System.out.println(System.out.printf("%-5d%-15s%s%n", user.getId(), user.getUsername(), user.getListRole()));
        });
        System.out.println("Enter user id to edit role");
        int id = ValidateInput.validateInt();
        if (!userList.contains(userController.findById(id))) {
            System.out.println("ID not found");
            return;
        }
        System.out.println("Enter Role");
        String role = InputConfig.getString();
        Set<String> strRoles = new HashSet<>();
        strRoles.add(role);
        userController.setRole(id, strRoles);
        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME", "ROLE");
        userList.forEach(user -> {
            System.out.printf("%-5d%-15s%s%n", user.getId(), user.getUsername(), user.getListRole());
        });
    }

    private void formBlockUser() {
        RoleName maxRole = currenUser.getListRole();
        System.out.println("*****BLOCK USER FORM*****");
        List<User> userList;
        if (maxRole == RoleName.ADMIN) {
            userList = new ArrayList<>(this.userList);
            User current = null;
            for (User user : userList) {
                if (user.getUsername().equals(currenUser.getUsername())) {
                    current = user;
                }
            }
            userList.remove(current);
        } else {
            userList = userController.findByRoleName(RoleName.USER);
        }
        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME", "STATUS");
        userList.forEach(user -> {
            System.out.printf("%-5d%-15s%s%n", user.getId(), user.getUsername(), user.isStatus());
        });

        System.out.println("Enter user id to block");

        int id = ValidateInput.validateInt();

        if (!userList.contains(userController.findById(id))) {
            System.out.println("ID not found");
            return;
        }

        userController.changeStatus(id);

        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME", "STATUS");
        userList.forEach(user -> {
            System.out.printf("%-5d%-15s%s%n", user.getId(), user.getUsername(), user.isStatus());
        });

    }

    private void formDeleteUser() {

        RoleName maxRole = currenUser.getListRole();
        System.out.println("*****DELETE USER FORM*****");
        List<User> userList;
        if (maxRole == RoleName.ADMIN) {
            userList = new ArrayList<>(this.userList);
            User current = null;
            for (User user : userList) {
                if (user.getUsername().equals(currenUser.getUsername())) {
                    current = user;
                }
            }
            userList.remove(current);
        } else {
            userList = userController.findByRoleName(RoleName.USER);
        }
        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME", "STATUS");
        userList.forEach(user -> {
            System.out.printf("%-5d%-15s%s%n", user.getId(), user.getUsername(), user.isStatus());
        });

        System.out.println("Enter user id to delete");

        int id = ValidateInput.validateInt();

        if (!userList.contains(userController.findById(id))) {
            System.out.println("ID not found");
            return;
        }

        userController.deleteUserById(id);
        User remove = null;
        for (User user : userList) {
            if (user.getId() == id) {
                remove = user;
            }
        }
        userList.remove(remove);

        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME", "STATUS");
        userList.forEach(user -> {
            System.out.printf("%-5d%-15s%s%n", user.getId(), user.getUsername(), user.isStatus());
        });

    }

    public static void main(String[] args) {
        new ViewUserManage();
    }

}
