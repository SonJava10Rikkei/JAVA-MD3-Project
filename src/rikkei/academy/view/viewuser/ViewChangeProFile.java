package rikkei.academy.view.viewuser;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.Config;
import rikkei.academy.config.validate.ValidateInputCustom;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;
import rikkei.academy.view.viewAdmin.userManage.ViewUserManage;
import rikkei.academy.view.viewAll.ViewHomeAfterCheck;

import java.util.List;
import java.util.regex.Pattern;


public class ViewChangeProFile {
    IUserService userService = new UserServiceIMPL();
    UserController userController = new UserController();
    List<User> userList = userController.getUserList();
    User currentUser = userController.getCurrenUser();

    public void menuProFile() {
        System.out.println(
                "\n                                               Xin chào : " + ColorConfig.PURPLE + "" + currentUser.getName() + "" + ColorConfig.RESET + " Email: " + ColorConfig.PURPLE + "" + currentUser.getEmail() + "" + ColorConfig.RESET + " \n" +
                        "                                              .————————————————————————————————————————————————————————.\n" +
                        "                                              ║              " + ColorConfig.BLUE + "MENU CHỈNH SỬA HỒ SƠ CỦA BẠN" + ColorConfig.RESET + "              ║\n" +
                        "                                              ║--------------------------------------------------------║\n" +
                        "                                              ║              1. Thay đổi tên của bạn                   ║\n" +
                        "                                              ║              2. Thay đổi email của bạn                 ║\n" +
                        "                                              ║              0. Trở về menu                            ║\n" +
                        "                                              '————————————————————————————————————————————————————————'\n");
        System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
        System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
        System.out.print("|     ");
        int choice = ValidateInputCustom.validateInt();
        System.out.println("'-------------------------------------------------------------'\n");
        switch (choice) {
            case 1:
                this.formChangeName();
                break;
            case 2:
                this.formChangeEmail();
                break;
            case 0:
                System.out.println("|     Bạn đã quay lại Menu :                                  |");
                new ViewHomeAfterCheck();
                break;
            default:
                System.out.print("" + ColorConfig.RED + "|     Hãy nhập lại lựa chọn Menu của bạn (0-2)!               |" + ColorConfig.RESET + "\n" +
                        "'-------------------------------------------------------------'\n");
                menuProFile();
        }
    }

    private void formChangeName() {
        User user = userController.getCurrenUser();
        System.out.println(".-----------------" + ColorConfig.BLUE + " 1. Thay đổi tên của bạn " + ColorConfig.RESET + "------------------.");
        String name;
        System.out.print(
                "|     Nhập tên mới của bạn :                                  |\n" +
                        "|     ");
        while (true) {
            name = ValidateInputCustom.getString();
            if (ValidateInputCustom.validateName(name))
                break;
            else
                System.out.print(
                        "|     " + ColorConfig.RED + "Tên nhập từ 2 đến tối đa 40 ký tự !" + ColorConfig.RESET + "                     |\n" +
                                "|     " + ColorConfig.RED + "Xin vui lòng nhập lại:" + ColorConfig.RESET + "                                  |\n" +
                                "|     ");
        }
        user.setName(name);
        userController.upDateProFile(user);

        System.out.println("|     " + ColorConfig.GREEN + "Thay đổi tên thành công !" + ColorConfig.RESET + "                               |");
        System.out.println("'-------------------------------------------------------------'\n");
        userController.getUserList();
        currentUser = userController.getCurrenUser();
        menuProFile();
    }

    private void formChangeEmail() {
        User user = userController.getCurrenUser();
        System.out.println(".-----------------" + ColorConfig.BLUE + " 2. Thay đổi enail của bạn " + ColorConfig.RESET + "----------------.");
        String email;
        while (true) {
            email = ValidateInputCustom.getString();
            if (ValidateInputCustom.validateEmail(email))
                break;
            else
                System.out.print(
                        "|     " + ColorConfig.RED + "Email chưa đúng định dang ! " + ColorConfig.RESET + "                            |\n" +
                                "|     " + ColorConfig.RED + "(vidu: danhson@gmail.com) Hãy nhập lại:" + ColorConfig.RESET + "                 |\n" +
                                "|     ");
        }
        if ((!email.equals(user.getEmail())) && userController.existByEmail(email)) {
            System.out.println("Email_Existed!!!");
        } else {
            user.setEmail(email);
            userController.upDateProFile(user);
            System.out.println("Change Email Success!!!!");
        }
        userController.getUserList();
        currentUser = userController.getCurrenUser();
        menuProFile();

    }

    public void formChangePassword() {
        User user = userController.getCurrenUser();
        System.out.println(".-----------------" + ColorConfig.BLUE + " Thay đổi mật khẩu của bạn " + ColorConfig.RESET + "------------------.");
        String password = ValidateInputCustom.getString();
        if (password.equals(user.getPassword())) ;
        String newPassword;
        while (true) {
            newPassword = ValidateInputCustom.getString();
            if (!newPassword.matches(ValidateInputCustom.validatePassword))
                System.out.print(
                        "|     " + ColorConfig.RED + "Mật khẩu từ 6 đến 10 ký tự: Gồm 1 ký tự viết hoa " + ColorConfig.RESET + "       |\n" +
                                "|     " + ColorConfig.RED + "và các ký tự thường, " + ColorConfig.RESET + "                                   |\n" +
                                "|     " + ColorConfig.RED + "Có kí tự số, 1 trong các ký tự đặc biệt sau @$!%*?& " + ColorConfig.RESET + "    |\n" +
                                "|     " + ColorConfig.RED + "(vidu: Son$93) xin vui lòng nhập lại:" + ColorConfig.RESET + "                   |\n" +
                                "|     ");
            else
                break;
        }
        user.setPassword(newPassword);
        userController.upDateProFile(user);
        System.out.println("Change password Success!!!!!");
        userController.getUserList();
        currentUser = userController.getCurrenUser();
    }

    private void backMenu() {
        new ViewHomeAfterCheck();
    }


}
