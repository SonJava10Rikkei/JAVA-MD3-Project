package rikkei.academy.view.viewAll;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.validate.ValidateInputCustom;
import rikkei.academy.view.viewAdmin.categoryView.CategoryViewManage;
import rikkei.academy.view.viewAdmin.productView.ProductViewManage;
import rikkei.academy.view.viewuser.FormLoginRegister;
import rikkei.academy.view.viewuser.orderUser.CartViewMenu;

public class HomePageMenu {
    private boolean isLoggedIn = false;

    public HomePageMenu() {
        while (true) {
            System.out.println(
                    "\n                                              .————————————————————————————————————————————————————————.\n" +
                            "                                              ║                     " + ColorConfig.BLUE + "MENU TRANG CHỦ" + ColorConfig.RESET + "                     ║\n" +
                            "                                              ║--------------------------------------------------------║\n" +
                            "                                              ║              1. Xem tất cả sản phẩm                    ║\n" +
                            "                                              ║              2. Xem Danh mục sản phẩm                  ║\n" +
                            "                                              ║              3. Tìm kiếm sản phẩm                      ║\n" +
                            "                                              ║              4. Sắp xếp sản phẩm theo giá              ║\n" +
                            "                                              ║              5. Mua sản phẩm                           ║\n" +
                            "                                              ║              6. Đăng nhập                              ║\n" +
                            "                                              ║              7. Đăng ký                                ║\n" +
                            "                                              ║              0. Thoát chương trình                     ║\n" +
                            "                                              '————————————————————————————————————————————————————————'\n");
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
            System.out.print("|     ");
            int chooseMenu = ValidateInputCustom.validateInt();
            System.out.println("'-------------------------------------------------------------'");
            switch (chooseMenu) {
                case 1:
                    new ProductViewManage().showListProduct();
                    break;
                case 2:
                    new CategoryViewManage().showListCategory();
                    break;
                case 3:
                    new ProductViewManage().searchProductByName();
                    break;
                case 4:
                    new ProductViewManage().sortProduct();
                    break;
                case 5:
                    if (isLoggedIn) {
                        new CartViewMenu();
                    } else {
                        System.out.println("    " + ColorConfig.RED + " Bạn cần đăng nhập trước để có thể mua sản phẩm !" + ColorConfig.RESET + "");
                    }
                    break;
                case 6:
                    new FormLoginRegister().formLogin();
                    isLoggedIn = true;
                    break;
                case 7:
                    new FormLoginRegister().fromRegister();
                    break;
                case 0:
                    System.err.println("     Bạn đã thoát chương trình!     ");
                    System.exit(0);
                    break;
                default:
                    System.out.print("" + ColorConfig.RED + "|     Hãy nhập lại lựa chọn Menu của bạn (0-7)!               |" + ColorConfig.RESET + "\n" +
                            "'-------------------------------------------------------------'\n");
            }
        }
    }

    public static void main(String[] args) {
        new HomePageMenu();
    }

}
