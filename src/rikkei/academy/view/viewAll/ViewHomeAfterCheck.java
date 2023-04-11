package rikkei.academy.view.viewAll;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.validate.ValidateInputCustom;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;
import rikkei.academy.model.role.RoleName;
import rikkei.academy.view.viewAdmin.categoryView.CategoryViewManage;
import rikkei.academy.view.viewAdmin.categoryView.CategoryViewMenu;
import rikkei.academy.view.viewAdmin.productView.ProductViewManage;
import rikkei.academy.view.viewAdmin.productView.ProductViewMenu;
import rikkei.academy.view.viewAdmin.userManage.ViewUserManage;
import rikkei.academy.view.viewuser.ViewChangeProFile;

import java.util.ArrayList;
import java.util.List;

public class ViewHomeAfterCheck {
    UserController userController = new UserController();
    User currenUser = userController.getCurrenUser();
    RoleName roleName = new ArrayList<>(currenUser.getRoles()).get(0).getRoleName();
    List<User> userList = userController.getUserList();

    public ViewHomeAfterCheck() {
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
        while (true) {
            System.out.println(
                    "\n                                               Xin chào : " + ColorConfig.PURPLE + "" + roleName + "" + ColorConfig.RESET + " : " + ColorConfig.PURPLE + "" + currenUser.getName() + "" + ColorConfig.RESET +
                            "\n                                              .————————————————————————————————————————————————————————.\n" +
                            "                                              ║                      " + ColorConfig.BLUE + "MENU ADMIN" + ColorConfig.RESET + "                        ║\n" +
                            "                                              ║--------------------------------------------------------║\n" +
                            "                                              ║              1. Đăng xuất                              ║\n" +
                            "                                              ║              2. Menu quản lý danh mục                  ║\n" +
                            "                                              ║              3. Menu quản lý sản phẩm                  ║\n" +
                            "                                              ║              4. Menu quản lý đơn hàng                  ║\n" +
                            "                                              ║              5. Menu quản lý người dùng                ║\n" +
                            "                                              ║              6. Hiển thị danh sách người dùng          ║\n" +
                            "                                              ║              0. Thoát chương trình                     ║\n" +
                            "                                              '————————————————————————————————————————————————————————'\n");
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
            System.out.print("|     ");
            int chooseMenu = ValidateInputCustom.validateInt();
            System.out.println("'-------------------------------------------------------------'");
            switch (chooseMenu) {
                case 1:
                    userController.logOut();
                    new HomePageMenu();
                    break;
                case 2:
                    new CategoryViewMenu();
                    break;
                case 3:
                    new ProductViewMenu();
                    break;
                case 4:
                    break;
                case 5:
                    new ViewUserManage();
                    break;
                case 6:
                    formShowListUser();
                    break;
                case 0:
                    System.err.println("     Bạn đã thoát chương trình!     ");
                    System.exit(0);
                    break;
                default:
                    System.out.print("" + ColorConfig.RED + "|     Hãy nhập lại lựa chọn Menu của bạn (0-6)!               |" + ColorConfig.RESET + "\n" +
                            "'-------------------------------------------------------------'\n");
            }
        }
    }

    private void menuPm() {
        while (true) {
            System.out.println(
                    "\n                                               Xin chào : " + ColorConfig.PURPLE + "" + roleName + "" + ColorConfig.RESET + " : " + ColorConfig.PURPLE + "" + currenUser.getName() + "" + ColorConfig.RESET +
                            "\n                                              .————————————————————————————————————————————————————————.\n" +
                            "                                              ║                       " + ColorConfig.BLUE + "MENU PM" + ColorConfig.RESET + "                          ║\n" +
                            "                                              ║--------------------------------------------------------║\n" +
                            "                                              ║              1. Đăng xuất                              ║\n" +
                            "                                              ║              2. Menu quản lý danh mục                  ║\n" +
                            "                                              ║              3. Menu quản lý sản phẩm                  ║\n" +
                            "                                              ║              4. Menu quản lý đơn hàng                  ║\n" +
                            "                                              ║              5. Menu quản lý người dùng                ║\n" +
                            "                                              ║              0. Thoát chương trình                     ║\n" +
                            "                                              '————————————————————————————————————————————————————————'\n");
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
            System.out.print("|     ");
            int chooseMenu = ValidateInputCustom.validateInt();
            System.out.println("'-------------------------------------------------------------'");
            switch (chooseMenu) {
                case 1:
                    userController.logOut();
                    new HomePageMenu();
                    break;
                case 2:
                    new CategoryViewMenu();
                    break;
                case 3:
                    new ProductViewMenu();
                    break;
                case 4:
                    break;
                case 5:
                    new ViewUserManage();
                    break;
                case 0:
                    System.err.println("     Bạn đã thoát chương trình!     ");
                    System.exit(0);
                    break;
                default:
                    System.out.print("" + ColorConfig.RED + "|     Hãy nhập lại lựa chọn Menu của bạn (0-5)!               |" + ColorConfig.RESET + "\n" +
                            "'-------------------------------------------------------------'\n");
            }
        }

    }


    public void menuUser() {
        while (true) {
            System.out.println(
                    "\n                                               Xin chào : " + ColorConfig.PURPLE + "" + currenUser.getName() + "" + ColorConfig.RESET +
                            "\n                                              .————————————————————————————————————————————————————————.\n" +
                            "                                              ║                       " + ColorConfig.BLUE + "MENU USER" + ColorConfig.RESET + "                        ║\n" +
                            "                                              ║--------------------------------------------------------║\n" +
                            "                                              ║              1. Đăng xuất                              ║\n" +
                            "                                              ║              2. Thông tin của bạn                      ║\n" +
                            "                                              ║              3. Thay đổi mật khẩu                      ║\n" +
                            "                                              ║              4. Hiển thị danh mục sản phẩm             ║\n" +
                            "                                              ║              5. Hiển thị danh sách sản phẩm            ║\n" +
                            "                                              ║              6. Tìm kiếm sản phẩm                      ║\n" +
                            "                                              ║              7. Sắp xếp sản phẩm                       ║\n" +
                            "                                              ║              8. Mua sản phẩm                           ║\n" +
                            "                                              ║              0. Thoát chương trình                     ║\n" +
                            "                                              '————————————————————————————————————————————————————————'\n");
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
            System.out.print("|     ");
            int chooseMenu = ValidateInputCustom.validateInt();
            System.out.println("'-------------------------------------------------------------'");
            switch (chooseMenu) {
                case 1:
                    userController.logOut();
                    new HomePageMenu();
                    break;
                case 2:
                    new ViewChangeProFile().menuProFile();
                    break;
                case 3:
                    new ViewChangeProFile().formChangePassword();
                    break;
                case 4:
                    new CategoryViewManage().showListCategory();
                    break;
                case 5:
                    new ProductViewManage().showListProduct();
                    break;
                case 6:
                    new ProductViewManage().searchProductByName();
                    break;
                case 7:
                    new ProductViewManage().sortProduct();
                    break;
                case 8:
                    new ProductViewManage().sortProduct();
                    break;
                case 0:
                    System.err.println("     Bạn đã thoát chương trình!     ");
                    System.exit(0);
                    break;
                default:
                    System.out.print("" + ColorConfig.RED + "|     Hãy nhập lại lựa chọn Menu của bạn (0-8)!               |" + ColorConfig.RESET + "\n" +
                            "'-------------------------------------------------------------'\n");
            }
        }
    }


    private void formShowListUser() {
        System.out.println(
                "       .—————————————————————————————————————————————————————————— DANH SÁCH NGƯỜI DÙNG ——————————————————————————————————————————————————————————.\n" +
                        "       ║        |                      |                    |                                           |                    |                    ║\n" +
                        "       ║   ID   |        TÊN           |    TÊN TÀI KHOẢN   |                   EMAIL                   |      MẬT KHẨU      |   QUYỀN TÀI KHOẢN  ║\n" +
                        "       ║        |                      |                    |                                           |                    |                    ║\n" +
                        "       ║------------------------------------------------------------------------------------------------------------------------------------------║");
        for (User user : userList) {
            System.out.printf("       ║   %-2d   |    %-15s   |      %-10s    |       %-18s                  |      %-10s    |      %-10s    ║\n",
                    user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), user.getListRole().name());
        }
        System.out.println("       '——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————'\n");
    }

    private void formUserManage() {

    }
}

