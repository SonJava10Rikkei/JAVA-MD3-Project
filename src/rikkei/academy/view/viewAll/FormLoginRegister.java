package rikkei.academy.view.viewAll;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessenger;
import rikkei.academy.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FormLoginRegister {
    UserController userController = new UserController();
    List<User> userList = userController.getUserList();

    public void fromRegister() {
        System.out.println("size -->" + userList.size());
        int id = 0;
        if (userList.size() == 0) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }
        System.out.println("id =---" + id);
        System.out.println("Enter the name: ");
        String name = Config.scanner().nextLine();
        System.out.println("Enter the username: ");
        String username = Config.scanner().nextLine();
        System.out.println("Enter the email: ");
        String email = Config.scanner().nextLine();
        System.out.println("Enter the password: ");
        String password = Config.scanner().nextLine();
        System.out.println("Enter the role: ");
        String role = Config.scanner().nextLine();
        Set<String> strRole = new HashSet<>();
        strRole.add(role);
        SignUpDTO sinUpDTO = new SignUpDTO(id, name, username, email, password, strRole);

        while (true) {
            ResponseMessenger check_existed = userController.register(sinUpDTO);

            if (check_existed.getMessenger().equals("Username_Existed")) {
                System.out.println("Username the existed, try again!!!");
                fromRegister();
            } else if (check_existed.getMessenger().equals("Email_Existed!!!")) {
                System.out.println("Email the existed, try again!!!");
                fromRegister();
            } else if (check_existed.getMessenger().equals("Create success")) {
                System.out.println("Xin chào bạn!");
                break;
            }
        }
    }

    public void formLogin() {
        System.out.println("*********************FORM LOGIN*********************");
        System.out.println("Enter your username: ");
        String username = Config.scanner().nextLine();
        System.out.println("Enter your password: ");
        String password = Config.scanner().nextLine();
        while (true){
            ResponseMessenger messenger = userController.login(new SignUpDTO(username, password));
            if (messenger.getMessenger().equals("Login-Failed!!!")) {
                System.out.println("Đăng nhập không thành công! Tên hoặc mật khẩu sai ");
            } else {
                messenger.getMessenger().equals("Login-Success");
                System.out.println("Đăng nhập thành công !");
                new ViewHome();
                break;
            }
        }
    }
    public void showListUser(){
        System.out.println(userController.getUserList());
        System.out.println("Enter back to return Navbar: ");
        String back = Config.scanner().nextLine();
        if(back.equalsIgnoreCase("back")){
            new HomePageMenu();
        }
    }
}

