package rikkei.academy.view.viewAdmin.categoryView;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.validate.ValidateInputCustom;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;
import rikkei.academy.model.role.RoleName;
import rikkei.academy.view.viewAll.ViewHomeAfterCheck;

import java.util.ArrayList;

public class CategoryViewMenu {
    public CategoryViewMenu() {
        while (true) {
            System.out.println(
                    "\n                                              .————————————————————————————————————————————————————————.\n" +
                            "                                              ║                " + ColorConfig.BLUE + "MENU DANH MỤC SẢN PHẨM" + ColorConfig.RESET + "                  ║\n" +
                            "                                              ║--------------------------------------------------------║\n" +
                            "                                              ║        1. Hiển thị danh sách danh mục sản phẩm         ║\n" +
                            "                                              ║        2. Thêm mới danh mục sản phẩm                   ║\n" +
                            "                                              ║        3. Sửa danh mục sản phẩm                        ║\n" +
                            "                                              ║        4. Xóa danh mục sản phẩm                        ║\n" +
                            "                                              ║        0. Quay lại Menu                                ║\n" +
                            "                                              '————————————————————————————————————————————————————————'\n");
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
            System.out.print("|     ");
            int chooseMenu = ValidateInputCustom.validateInt();
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
                    System.out.println("|     Bạn đã quay lại Menu chính                              |");
                    System.out.println("'-------------------------------------------------------------'");
                    new ViewHomeAfterCheck();
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
