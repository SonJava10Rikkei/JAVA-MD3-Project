package rikkei.academy.view.viewuser;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.Config;
import rikkei.academy.config.validate.ValidateInputCustom;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessage;
import rikkei.academy.model.User;
import rikkei.academy.model.order.Cart;
import rikkei.academy.view.viewAll.HomePageMenu;
import rikkei.academy.view.viewAll.ViewHomeAfterCheck;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FormLoginRegister {
    UserController userController = new UserController();
    List<User> userList = userController.getUserList();

    public void fromRegister() {
        int id = 0;
        if (userList.size() == 0) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }
        System.out.println(".------------" + ColorConfig.BLUE + " Điền thông tin đăng ký tài khoản " + ColorConfig.RESET + "---------------.");
        System.out.print(
                "|     Nhập tên của bạn :                                      |\n" +
                        "|     ");
        String name;
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
        System.out.print(
                "|     Nhập tên tài khoản đăng ký :                            |\n" +
                        "|     ");
        String username;
        while (true) {
            username = Config.scanner().nextLine();
            if (!username.matches(ValidateInputCustom.validateUserName))
                System.out.print(
                        "|     " + ColorConfig.RED + "Tên tài khoản tối đa 30 ký tự, ký tự phải viết liền nhau" + ColorConfig.RESET + " |\n" +
                                "|     " + ColorConfig.RED + "Xin vui lòng nhập lại:" + ColorConfig.RESET + "                                  |\n" +
                                "|     ");
            else
                break;
        }
        System.out.print(
                "|     Nhập email của bạn :                                    |\n" +
                        "|     ");
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
        System.out.print(
                "|     Nhập mật khẩu của bạn :                                 |\n" +
                        "|     ");
        String password;
        while (true) {
            password = ValidateInputCustom.getString();
            if (!password.matches(ValidateInputCustom.validatePassword))
                System.out.print(
                        "|     " + ColorConfig.RED + "Mật khẩu từ 6 đến 10 ký tự: Gồm 1 ký tự viết hoa " + ColorConfig.RESET + "       |\n" +
                                "|     " + ColorConfig.RED + "và các ký tự thường, " + ColorConfig.RESET + "                                   |\n" +
                                "|     " + ColorConfig.RED + "Có kí tự số, 1 trong các ký tự đặc biệt sau @$!%*?& " + ColorConfig.RESET + "    |\n" +
                                "|     " + ColorConfig.RED + "(vidu: Son$93) xin vui lòng nhập lại:" + ColorConfig.RESET + "                   |\n" +
                                "|     ");
            else
                break;

        }
        System.out.print(
                "|     Nhập quyền tài khoản của bạn (admin, pm, user):         |\n" +
                        "|     ");
        String role = Config.scanner().nextLine();
        Set<String> strRole = new HashSet<>();
        strRole.add(role);
        SignUpDTO sinUpDTO = new SignUpDTO(id, name, username, email, password, strRole);

        while (true) {
            ResponseMessage check_existed = userController.register(sinUpDTO);

            if (check_existed.getMessenger().equals("Username_Existed!!!")) {
                System.out.print(
                        "|     " + ColorConfig.RED + "Nhập tên tài khoản bị trùng, vùi lòng nhập lại:" + ColorConfig.RESET + "         |\n" +
                                "|     ");
                username = Config.scanner().nextLine();
                sinUpDTO.setUsername(username);
            } else if (check_existed.getMessenger().equals("Email_Existed!!!")) {
                System.out.print(
                        "|     " + ColorConfig.RED + "Nhập tên email bị trùng, vùi lòng nhập lại:" + ColorConfig.RESET + "             |\n" +
                                "|     ");
                email = Config.scanner().nextLine();
                sinUpDTO = new SignUpDTO(id, name, username, email, password, strRole);
            } else if (check_existed.getMessenger().equals("Create success")) {
                System.out.println("|     " + ColorConfig.GREEN + "Đã đăng ký thành công, xin mời đăng nhập!" + ColorConfig.RESET + "               |");
                System.out.println("'-------------------------------------------------------------'");
                break;
            }
        }
    }

    public void formLogin() {
        System.out.println(".----------------" + ColorConfig.BLUE + " Điền thông tin đăng nhập " + ColorConfig.RESET + "-------------------.");
        System.out.print(
                "|     Nhập tên tài khoản đăng nhập:                           |\n" +
                        "|     ");
        String username = Config.scanner().nextLine();
        System.out.print(
                "|     Nhập mật khẩu:                                          |\n" +
                        "|     ");
        String password = Config.scanner().nextLine();
        while (true) {
            ResponseMessage messenger = userController.login(new SignUpDTO(username, password));
            if (messenger.getMessenger().equals("Login-Failed!!!")) {
                System.out.println("| " + ColorConfig.RED + "Đăng nhập không thành công! Sai tên tài khoản hoặc mật khẩu" + ColorConfig.RESET + " |");

                System.out.println("|     Nhập phím bất kỳ để nhập lại thông tin,                 |");
                System.out.println("|     hoặc nhập 'm' để quay lại " + ColorConfig.BLUE + "Menu Trang chủ" + ColorConfig.RESET + ":               |");
                System.out.print("|     ");
                String backMenu = Config.scanner().nextLine();
                System.out.println("'-------------------------------------------------------------'");
                if (backMenu.equalsIgnoreCase("m")) {
                    new HomePageMenu();
                }
                formLogin();
            } else {
                messenger.getMessenger().equals("Login-Success");
                System.out.println("|     " + ColorConfig.GREEN + "Đã đăng nhập thành công !" + ColorConfig.RESET + "                               |");
                System.out.println("'-------------------------------------------------------------'");
                new ViewHomeAfterCheck();
                break;
            }
        }
    }

//    public void showListUser() {
//        System.out.println(userController.getUserList());
//        System.out.println("Enter back to return Navbar: ");
//        String back = Config.scanner().nextLine();
//        if (back.equalsIgnoreCase("back")) {
//            new HomePageMenu();
//        }
//    }
}

