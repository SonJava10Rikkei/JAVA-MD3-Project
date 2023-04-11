package rikkei.academy.view.viewAll;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.validate.ValidateInputCustom;
import rikkei.academy.view.viewAdmin.categoryView.CategoryViewManage;
import rikkei.academy.view.viewAdmin.productView.ProductViewManage;
import rikkei.academy.view.viewAll.viewLoginRegister.FormLoginRegister;

public class HomePageMenu {

    public HomePageMenu() {
        while (true) {
            System.out.println(
                    "\n                                              .————————————————————————————————————————————————————————.\n" +
                            "                                              ║                     " + ColorConfig.BLUE + "MENU TRANG CHỦ" + ColorConfig.RESET + "                     ║\n" +
                            "                                              ║--------------------------------------------------------║\n" +
                            "                                              ║              1. Đăng ký                                ║\n" +
                            "                                              ║              2. Đăng nhập                              ║\n" +
                            "                                              ║              3. Danh mục sản phẩm                      ║\n" +
                            "                                              ║              4. Xem tất cả sản phẩm                    ║\n" +
                            "                                              ║              5. Tìm kiếm sản phẩm                      ║\n" +
                            "                                              ║              6. Sắp xếp sản phẩm theo giá              ║\n" +
                            "                                              ║              0. Thoát chương trình                     ║\n" +
                            "                                              '————————————————————————————————————————————————————————'\n");
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
            System.out.print("|     ");
            int chooseMenu = ValidateInputCustom.validateInt();
            System.out.println("'-------------------------------------------------------------'");
            switch (chooseMenu) {
                case 1:
                    new FormLoginRegister().fromRegister();
                    break;
                case 2:
                    new FormLoginRegister().formLogin();
                    break;
                case 3:
                    new CategoryViewManage().showListCategory();
                    break;
                case 4:
                    new ProductViewManage().showListProduct();
                    break;
                case 5:
                    new ProductViewManage().searchProductByName();
                    break;
                case 6:
                    new ProductViewManage().sortProduct();
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

    public static void main(String[] args) {
        new HomePageMenu();
    }

}
