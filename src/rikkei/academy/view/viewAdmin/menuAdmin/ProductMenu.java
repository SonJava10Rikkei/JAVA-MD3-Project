package rikkei.academy.view.viewAdmin.menuAdmin;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.validate.ValidateInput;
import rikkei.academy.view.menuView.CustomStringMenuViewAd;
import rikkei.academy.view.viewAdmin.productView.ProductViewCRUD;

public class ProductMenu {
    public ProductMenu() {


        while (true) {
            System.out.println(CustomStringMenuViewAd.ProductMenu);
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
            int chooseMenu = ValidateInput.validateInt();
            System.out.println("'-------------------------------------------------------------'\n");
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
                    System.err.println("|     Hãy nhập lại lựa chọn của bạn (0-7):                    |");

            }
        }
    }
    public static void main(String[] args) {
        new ProductMenu();
    }
}
