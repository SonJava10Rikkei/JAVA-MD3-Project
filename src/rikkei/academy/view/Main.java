package rikkei.academy.view;

import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;
import rikkei.academy.view.viewAll.HomePageMenu;
import rikkei.academy.view.viewAll.ViewHome;

public class Main {
    UserController userController = new UserController();

    public Main(){
        User currenUser = userController.getCurrenUser();
        if (currenUser == null){
            new HomePageMenu();
        }else {
            new ViewHome();
        }

    }

    public static void main(String[] args) {
        new Main();
    }

}
