package rikkei.academy.view.viewAll;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.validate.ValidateInputCustom;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;
import rikkei.academy.model.role.RoleName;
import rikkei.academy.view.viewAdmin.categoryView.CategoryViewManage;
import rikkei.academy.view.viewAdmin.productView.ProductViewManage;
import rikkei.academy.view.viewAll.viewLoginRegister.FormLoginRegister;


import java.util.ArrayList;
import java.util.List;

public class ViewHome {
    UserController userController = new UserController();
    User currenUser = userController.getCurrenUser();
    List<User> userList = userController.getUserList();

    RoleName roleName = new ArrayList<>(currenUser.getRoles()).get(0).getRoleName();

    public ViewHome() {
        switch (roleName) {
            case ADMIN:
                menuAdmin();
                break;
            case PM:
                menuPm();
                break;
            default:
//                USER
                menuUser();
                break;
        }
    }
    public void menuAdmin() {
//        while (true){
//            System.out.println(
//                    "\n                                              .————————————————————————————————————————————————————————.\n" +
//                            "                                              ║                     " + ColorConfig.BLUE + "MENU TRANG CHỦ" + ColorConfig.RESET + "                     ║\n" +
//                            "                                              ║--------------------------------------------------------║\n" +
//                            "                                              ║              1. Đăng ký                                ║\n" +
//                            "                                              ║              2. Đăng nhập                              ║\n" +
//                            "                                              ║              3. Danh mục sản phẩm                      ║\n" +
//                            "                                              ║              4. Xem tất cả sản phẩm                    ║\n" +
//                            "                                              ║              5. Tìm kiếm sản phẩm                      ║\n" +
//                            "                                              ║              6. Sắp xếp sản phẩm theo giá              ║\n" +
//                            "                                              ║              0. Thoát chương trình                     ║\n" +
//                            "                                              '————————————————————————————————————————————————————————'\n");
//            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
//            System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
//            System.out.print("|     ");
//            int chooseMenu = ValidateInputCustom.validateInt();
//            System.out.println("'-------------------------------------------------------------'");
//            switch (chooseMenu) {
//                case 1:
//                    new FormLoginRegister().fromRegister();
//                    break;
//                case 2:
//                    new FormLoginRegister().formLogin();
//                    break;
//                case 3:
//                    new CategoryViewManage().showListCategory();
//                    break;
//                case 4:
//                    new ProductViewManage().showListProduct();
//                    break;
//                case 5:
//
//                    break;
//                case 0:
//                    System.err.println("     Bạn đã thoát chương trình!     ");
//                    System.exit(0);
//                    break;
//                default:
//                    System.out.print("" + ColorConfig.RED + "|     Hãy nhập lại lựa chọn Menu của bạn (0-7)!               |" + ColorConfig.RESET + "\n" +
//                            "'-------------------------------------------------------------'\n");
//            }
//            break;
//        }

        System.out.println("*****************WELCOME*************************");
        System.out.println("Hello: " + roleName + " : " + currenUser.getName());
        System.out.println("1: Log Out");
        System.out.println("2: User manage");
        System.out.println("3: Show list user");
        System.out.println("4: Category manage");
        System.out.println("5: Video manage");
        System.out.println("6: Series Video manage");


        int choice = ValidateInputCustom.validateInt();

        switch (choice) {
            case 1:
                userController.logOut();
//                new ViewMainMenu().menu();
                return;
            case 2:
                formUserManage();
                break;
            case 3:
                formShowListUser();
                break;
            case 4:
//                new ViewCategory().menuCategory();
                break;
            case 5:
//                new ViewVideo().menuVideo();
                break;
            case 6:
//                new ViewSeriesFilm().seriesMenu();
                break;
            default:
                System.out.println("Invalid choice");
        }
        menuAdmin();
    }

    private void menuPm() {
        System.out.println("*****************WELCOME*************************");
        System.out.println("Hello: " + roleName + " : " + currenUser.getName());
        System.out.println("1: Log Out");
        System.out.println("2: User manage");
        System.out.println("3: Show list user");
        System.out.println("4: Category manage");
        System.out.println("5: Video manage");
        System.out.println("6: Series Video manage");


        int choice = ValidateInputCustom.validateInt();
        switch (choice) {
            case 1:
                userController.logOut();
//                new ViewMainMenu().menu();
                return;
            case 2:
                formUserManage();
                break;
            case 3:
                formShowListUser();
                break;
            case 4:
//                new ViewCategory().menuCategory();
                break;
            case 5:
//                new ViewVideo().menuVideo();
                break;
            case 6:
//                new ViewSeriesFilm().seriesMenu();
                break;
            default:
                System.out.println("Invalid choice");
        }
        menuPm();

    }


    public void menuUser() {
        System.out.println("*****************WELCOME*************************");
        System.out.println("Hello: " + currenUser.getName());
        System.out.println("1: Log Out");
        System.out.println("2: Your Profile");
        System.out.println("3: Change password");
        System.out.println("4: Show list Category");
        System.out.println("5: Show list Video");
        System.out.println("6: Show video with category");
        System.out.println("7: Top View Video");
        System.out.println("8: Show phim lẻ");
        System.out.println("9: Show phim bộ");
        System.out.println("10: Search Video by name");
        System.out.println("11: Search Video by category");
        System.out.println("12: Details Video");
        System.out.println("13: Top Video by NSX");
        System.out.println("14: Top Like Video");

        int choice = ValidateInputCustom.validateInt();

        switch (choice) {
            case 1:
                userController.logOut();
//                new ViewMainMenu().menu();
                return;
            case 2:
//                new ViewChangeProFile().menuProFile();
                break;
            case 3:
//                new ViewChangeProFile().formChangePassword();
                break;
            case 4:
//                new ViewCategory().formShowListCategory();
                break;
            case 5:
//                new ViewVideo().formShowListVideo();
                break;
            case 6:
//                new ViewVideo().formVideoWithCategory();
                break;
            case 7:
//                new ViewVideo().formTopViewVideo();
                break;
            case 8:
//                new ViewVideo().formShowFilm();
                break;
            case 9:
//                new ViewVideo().formShowSeriesFilm();
                break;
            case 10:
//                new ViewVideo().formSearchFilmWithName();
                break;
            case 11:
//                new ViewVideo().formVideoWithCategory();
                break;
            case 12:
//                new ViewVideo().formLikeVideo();
                break;
            case 13:
//                new ViewVideo().formVideoByDate();
                break;
            case 14:
//                new ViewVideo().formTopLikeVideo();
                break;
            default:
                System.out.println("Invalid choice");
        }
        menuUser();
    }




    private void formShowListUser() {
        System.out.printf("%-15s%-15s%-15s%-25s%-15s%s%n", "id", "name", "Username", "email", "password", "Role");
        for (User user : userList) {
            System.out.printf("%-15s%-15s%-15s%-25s%-15s%s%n", user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());
        }
    }

    private void formUserManage() {
//       new ViewUserManage().menu();

    }
}

