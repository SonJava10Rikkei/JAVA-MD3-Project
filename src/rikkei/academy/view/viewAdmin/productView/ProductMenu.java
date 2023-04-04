package rikkei.academy.view.viewAdmin.productView;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.validate.ValidateInput;
import rikkei.academy.view.menuView.CustomStringMenuViewAd;

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
                    new ProductViewCRUD().showListProduct();
                    break;
                case 2:
                    new ProductViewCRUD().formCreateProduct();
                    break;
                case 3:
                    new ProductViewCRUD().updateProduct();
                    break;
                case 4:
                    new ProductViewCRUD().deleteCategory();
                    break;
                case 5:
                    new ProductViewCRUD().searchProductByName();
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
        new ProductMenu();
    }
}
