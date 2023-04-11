package rikkei.academy.view.viewAdmin.userManage;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.Config;
import rikkei.academy.config.validate.ValidateInputCustom;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;
import rikkei.academy.model.role.RoleName;
import rikkei.academy.view.viewAdmin.categoryView.CategoryViewManage;
import rikkei.academy.view.viewAdmin.productView.ProductViewManage;
import rikkei.academy.view.viewAdmin.productView.ProductViewMenu;
import rikkei.academy.view.viewAll.ViewHomeAfterCheck;
import rikkei.academy.view.viewAll.viewLoginRegister.FormLoginRegister;
import rikkei.academy.view.viewuser.ViewChangeProFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewUserManage {
    UserController userController = new UserController();
    User currenUser = userController.getCurrenUser();
    List<User> userList = userController.getUserList();

    public ViewUserManage() {
        while (true) {
            System.out.println(
                    "\n                                              .————————————————————————————————————————————————————————.\n" +
                            "                                              ║                " + ColorConfig.BLUE + "MENU QUẢN LÝ NGƯỜI DÙNG" + ColorConfig.RESET + "                 ║\n" +
                            "                                              ║--------------------------------------------------------║\n" +
                            "                                              ║              1. Thay đổi quyền của người dùng          ║\n" +
                            "                                              ║              2. Chặn người dùng                        ║\n" +
                            "                                              ║              3. Xóa người dùng                         ║\n" +
                            "                                              ║              4. Chỉnh sửa hồ sơ của bạn                ║\n" +
                            "                                              ║              5. Đổi mật khẩu của bạn                   ║\n" +
                            "                                              ║              0. Trở về menu                            ║\n" +
                            "                                              '————————————————————————————————————————————————————————'\n");
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
            System.out.print("|     ");
            int chooseMenu = ValidateInputCustom.validateInt();
            System.out.println("'-------------------------------------------------------------'");
            switch (chooseMenu) {
                case 1:
                    formChangeRole();
                    break;
                case 2:
                    formBlockUser();
                    break;
                case 3:
                    formDeleteUser();
                    break;
                case 4:
                    new ViewChangeProFile().menuProFile();
                    break;
                case 5:
                    new ViewChangeProFile().formChangePassword();
                    break;
                case 0:
                    System.out.println("|     Bạn đã quay lại Menu :                                  |");
                    new ViewHomeAfterCheck();
                    break;
                default:
                    System.out.print("" + ColorConfig.RED + "|     Hãy nhập lại lựa chọn Menu của bạn (0-6)!               |" + ColorConfig.RESET + "\n" +
                            "'-------------------------------------------------------------'\n");
            }
        }
    }

    private static void listUserManager(List<User> userList) {
        System.out.println(
                "\n                                            .—————————————————————————————— DANH SÁCH NGƯỜI DÙNG ———————————————————————————————.\n" +
                        "                                            ║        |                          |                       |                       ║\n" +
                        "                                            ║   ID   |      TÊN NGƯỜI DÙNG      |    QUYỀN NGƯỜI DÙNG   |      TRẠNG THÁI       ║\n" +
                        "                                            ║        |                          |                       | (true = chặn # false) ║\n" +
                        "                                            ║-----------------------------------------------------------------------------------║");
        userList.forEach(user -> {
            System.out.printf("                                            ║   %-2d   |      %-15s     |       %-10s      |       %-10s      ║\n",
                    user.getId(),
                    user.getUsername(),
                    user.getListRole(),
                    user.isStatus());
        });
        System.out.println("                                            '———————————————————————————————————————————————————————————————————————————————————'\n");
    }

    private void formChangeRole() {
        List<User> userList = new ArrayList<>(this.userList);
        User current = null;
        for (User user : userList) {
            if (user.getUsername().equals(currenUser.getUsername())) {
                current = user;
            }
        }
        userList.remove(current);
        listUserManager(userList);
        System.out.println(".--------------" + ColorConfig.BLUE + " 1. Thay đổi quyền người dùng " + ColorConfig.RESET + "-----------------.");
        System.out.print(
                "|     Nhập ID của người dùng bạn muốn thay đổi quyền:         |\n" +
                        "|     ");
        int id = ValidateInputCustom.validateInt();
        User userToChangeRole = userController.findById(id);
        if (userToChangeRole == null) {
            System.out.print(
                    "|     " + ColorConfig.RED + "ID không có trong danh sách người dùng!" + ColorConfig.RESET + "                 |\n" +
                            "|     " + ColorConfig.RED + "Xin vui lòng nhập lại:" + ColorConfig.RESET + "                                  |\n" +
                            "|     ");
            formChangeRole();
        }
        if (String.valueOf(userToChangeRole.getListRole()).equals("ADMIN")) {
            System.out.println("|     " + ColorConfig.RED + "Không được phép thay đổi quyền của người là admin!" + ColorConfig.RESET + "      |");
            formChangeRole();
        } else {
            String role;
            while (true) {
                System.out.print(
                        "|     Nhập quyền của người dùng (pm/user) :                   |\n" +
                                "|     ");
                role = Config.InputConfig.getString();
                switch (role) {
                    case "pm":
                    case "user":
                        break;
                    default:
                        System.out.println("|     Vui lòng nhập lại 1 trong 2 quyền (pm hoặc user) :      |");
                        continue;
                }
                break;
            }
            Set<String> strRoles = new HashSet<>();
            strRoles.add(role);
            userController.setRole(id, strRoles);
            System.out.println("|     " + ColorConfig.GREEN + "Danh sách sau khi đổi quyền :" + ColorConfig.RESET + "                           |");
            listUserManager(userList);

        }
        System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
        System.out.println("|     Nhập phím bất kỳ để tiếp tục đổi quyền người dùng khác, |");
        System.out.println("|     hoặc nhập 'M' để quay lại Menu:                         |");
        System.out.print("|     ");
        String backMenu = Config.scanner().nextLine();
        System.out.println("'-------------------------------------------------------------'\n");
        if (backMenu.equalsIgnoreCase("m")) {
            new ViewUserManage();
        } else {
            formChangeRole();
        }
    }

    private void formBlockUser() {
        RoleName maxRole = currenUser.getListRole();
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
        listUserManager(userList);

        System.out.println(".---------------------" + ColorConfig.BLUE + " 2. Chặn người dùng " + ColorConfig.RESET + "----------------------.");
        System.out.print(
                "|     Nhập id của người dùng để thay đổi trạng thái             |\n" +
                        "|     chặn hoặc bỏ chặn (true => false và ngược lại):           |\n" +
                        "|     ");
        int id = ValidateInputCustom.validateInt();
        if (!userList.contains(userController.findById(id))) {
            System.out.println("|     " + ColorConfig.RED + "ID không có trong danh sách!" + ColorConfig.RESET + "                              |\n");

            return;
        }
        User userToChangeRole = userController.findById(id);
        if (String.valueOf(userToChangeRole.getListRole()).equals("ADMIN")) {
            System.out.println("|     " + ColorConfig.RED + "Không được phép chặn người dùng là admin!" + ColorConfig.RESET + "                 |");
            formBlockUser();
        } else {
            userController.changeStatus(id);
            System.out.println("|     " + ColorConfig.GREEN + "Danh sách sau khi chặn :" + ColorConfig.RESET + "                                |");
            listUserManager(userList);
        }
        System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
        System.out.println("|     Nhập phím bất kỳ để tiếp tục chặn người dùng khác,      |");
        System.out.println("|     hoặc nhập 'M' để quay lại Menu:                         |");
        System.out.print("|     ");
        String backMenu = Config.scanner().nextLine();
        System.out.println("'-------------------------------------------------------------'\n");
        if (backMenu.equalsIgnoreCase("m")) {
            new ViewUserManage();
        } else {
            formBlockUser();
        }
    }

    private void formDeleteUser() {
        RoleName maxRole = currenUser.getListRole();
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
        listUserManager(userList);
        System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
        System.out.print(
                "|     Nhập ID của người dùng để xóa                            |\n" +
                        "|     ");
        int id = ValidateInputCustom.validateInt();
        if (!userList.contains(userController.findById(id))) {
            System.out.println("|     " + ColorConfig.RED + "ID không có trong danh sách!" + ColorConfig.RESET + "                              |\n");
            return;
        }
        System.out.println("|" + ColorConfig.YELLOW + "  Bạn có chắc chắn muốn xóa người dùng này không?           " + ColorConfig.RESET + " |");
        System.out.println("|     Nhập Y để xóa hoặc N để quay lại? (y/n)                 |");
        while (true) {
            System.out.print("|     ");
            String deleteOption = Config.scanner().nextLine();
            if (deleteOption.equalsIgnoreCase("y")) {
                userController.deleteUserById(id);
                User remove = null;
                for (User user : userList) {
                    if (user.getId() == id) {
                        remove = user;
                    }
                }
                userList.remove(remove);
                System.out.println("|     " + ColorConfig.GREEN + "Đã xóa người dùng!!!" + ColorConfig.RESET + "                                    |");
                break;
            } else if (deleteOption.equalsIgnoreCase("n")) {
                System.out.println("|     " + ColorConfig.GREEN + "Người dùng chưa được xóa" + ColorConfig.RESET + "                                |");
                break;
            } else {
                System.out.println("|     " + ColorConfig.YELLOW + "Vui lòng nhập Y hoặc N:" + ColorConfig.RESET + "                                 |");
            }
        }

        listUserManager(userList);
        System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
        System.out.println("|     Nhập phím bất kỳ để tiếp tục xóa người dùng khác,       |");
        System.out.println("|     hoặc nhập 'M' để quay lại Menu:                         |");
        System.out.print("|     ");
        String backMenu = Config.scanner().nextLine();
        System.out.println("'-------------------------------------------------------------'\n");
        if (backMenu.equalsIgnoreCase("m")) {
            new ViewUserManage();
        } else {
            formDeleteUser();
        }

    }

    public static void main(String[] args) {
        new ViewUserManage();
    }

}
