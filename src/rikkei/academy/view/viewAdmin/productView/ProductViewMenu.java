package rikkei.academy.view.viewAdmin.productView;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.validate.ValidateInput;
import rikkei.academy.config.customString.CustomStringMenuViewAd;

public class ProductViewMenu {
    public ProductViewMenu() {
        while (true) {
            System.out.println(CustomStringMenuViewAd.ProductMenu);
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
            System.out.print("|     ");
            int chooseMenu = ValidateInput.validateInt();
            System.out.println("'-------------------------------------------------------------'");
            switch (chooseMenu) {
                case 1:
                    new ProductViewManage().showListProduct();
                    break;
                case 2:
                    new ProductViewManage().formCreateProduct();
                    break;
                case 3:
                    new ProductViewManage().updateProduct();
                    break;
                case 4:
                    new ProductViewManage().deleteCategory();
                    break;
                case 0:
                    System.err.println("     Bạn đã thoát chương trình!     ");
                    System.exit(0);
                    break;
                default:
                    System.out.print("" + ColorConfig.RED + "|     Hãy nhập lại lựa chọn Menu của bạn (0->4)!              |" + ColorConfig.RESET + "\n" +
                            "'-------------------------------------------------------------'\n");
            }
        }
    }

    public static void main(String[] args) {
        new ProductViewMenu();
    }
}