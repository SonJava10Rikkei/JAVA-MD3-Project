package rikkei.academy.view.viewAdmin.categoryView;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.customString.CustomStringMenuViewAd;
import rikkei.academy.config.validate.ValidateInput;

public class CategoryViewMenu {
    public CategoryViewMenu() {
        while (true) {
            System.out.println(CustomStringMenuViewAd.CategoryMenu);
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
            System.out.print("|     ");
            int chooseMenu = ValidateInput.validateInt();
            System.out.println("'-------------------------------------------------------------'");
            switch (chooseMenu) {
                case 1:
                    new CategoryViewManage().showListCategory();
                    break;
                case 2:
                    new CategoryViewManage().formCreateCategory();
                    break;
                case 3:
                    new CategoryViewManage().updateCategory();
                    break;
                case 4:
                    new CategoryViewManage().deleteCategory();
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
        new CategoryViewMenu();
    }
}
