package rikkei.academy.view.viewAdmin.productView;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.validate.ValidateInput;
import rikkei.academy.config.customString.CustomStringMenuViewAd;

public class ProductMenu {
    public ProductMenu() {
        while (true) {
            System.out.println(CustomStringMenuViewAd.ProductMenu);
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
            System.out.print("|     ");
            int chooseMenu = ValidateInput.validateInt();
            System.out.println("'-------------------------------------------------------------'");
            switch (chooseMenu) {
                case 1:
                    new ProductManage().showListProduct();
                    break;
                case 2:
                    new ProductManage().formCreateProduct();
                    break;
                case 3:
                    new ProductManage().updateProduct();
                    break;
                case 4:
                    new ProductManage().deleteCategory();
                    break;
//                case 5:
//                    new ProductManage().searchProductByName();
//                    break;
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
        new ProductMenu();
    }
}
