package rikkei.academy.view.viewAdmin.orderAdmin;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.validate.ValidateInputCustom;
import rikkei.academy.view.viewAll.ViewHomeAfterCheck;

public class OrderViewAdminMenu {
    public OrderViewAdminMenu() {
        while (true) {
            System.out.println(
                    "\n                                              .————————————————————————————————————————————————————————.\n" +
                            "                                              ║                 " + ColorConfig.BLUE + "MENU QUẢN LÝ ĐƠN HÀNG" + ColorConfig.RESET + "                  ║\n" +
                            "                                              ║--------------------------------------------------------║\n" +
                            "                                              ║           1. Xem các đơn hàng                          ║\n" +
                            "                                              ║           2. Thay đổi trạng thái đơn hàng              ║\n" +
                            "                                              ║           0. Trở về Menu                               ║\n" +
                            "                                              '————————————————————————————————————————————————————————'\n");
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
            System.out.print("|     ");
            int chooseMenu = ValidateInputCustom.validateInt();
            System.out.println("'-------------------------------------------------------------'");
            switch (chooseMenu) {
                case 1:


                    break;
                case 2:


                    break;

                case 0:
                    System.out.println("Bạn đã quay về Menu !");
                    new ViewHomeAfterCheck();
                    break;
                default:
                    System.out.print("" + ColorConfig.RED + "|     Hãy nhập lại lựa chọn Menu của bạn (0-2)!               |" + ColorConfig.RESET + "\n" +
                            "'-------------------------------------------------------------'\n");
            }
        }
    }

    public static void main(String[] args) {
        new OrderViewAdminMenu();
    }
}
