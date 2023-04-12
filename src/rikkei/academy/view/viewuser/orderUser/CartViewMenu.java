package rikkei.academy.view.viewuser.orderUser;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.validate.ValidateInputCustom;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;
import rikkei.academy.view.viewAll.ViewHomeAfterCheck;

public class CartViewMenu {
    UserController userController = new UserController();

    User loginUser = userController.getCurrenUser();

    public CartViewMenu() {
        while (true) {
            System.out.println(
                    "\n                                              .————————————————————————————————————————————————————————.\n" +
                            "                                              ║                     " + ColorConfig.BLUE + "MENU GIỎ HÀNG" + ColorConfig.RESET + "                      ║\n" +
                            "                                              ║--------------------------------------------------------║\n" +
                            "                                              ║           1. Xem lại giỏ hàng                          ║\n" +
                            "                                              ║           2. Thêm sản phẩm vào giỏ hàng                ║\n" +
                            "                                              ║           3. Bớt hoặc xóa sản phẩm khỏi giỏ hàng       ║\n" +
                            "                                              ║           4. Xóa tất cả sản phẩm khỏi giỏ hàng         ║\n" +
                            "                                              ║           5. Thanh toán                                ║\n" +
                            "                                              ║           6. Xem lại lịch sử mua hàng                  ║\n" +
                            "                                              ║           0. Trở về Menu                               ║\n" +
                            "                                              '————————————————————————————————————————————————————————'\n");
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
            System.out.print("|     ");
            int chooseMenu = ValidateInputCustom.validateInt();
            System.out.println("'-------------------------------------------------------------'");
            switch (chooseMenu) {
                case 1:
                    new CartViewManage().showCart(loginUser);
                    break;
                case 2:
                    new CartViewManage().addToCartById(loginUser);
                    break;
                case 3:


                    break;
                case 4:


                    break;
                case 5:


                    break;
                case 0:
                    System.out.println("Bạn đã quay về Menu !");
                    new ViewHomeAfterCheck();
                    break;
                default:
                    System.out.print("" + ColorConfig.RED + "|     Hãy nhập lại lựa chọn Menu của bạn (0-5)!               |" + ColorConfig.RESET + "\n" +
                            "'-------------------------------------------------------------'\n");
            }
        }
    }

    public static void main(String[] args) {
        new CartViewMenu();
    }
}
