package rikkei.academy.view.viewall;

import rikkei.academy.validate.ValidateInput;
import rikkei.academy.config.ColorConfig;
import rikkei.academy.view.viewadmin.ProductView;

public class Navbar {
    public Navbar() {
        while (true) {
            System.out.println("                                              .————————————————————————————————————————————————————————.");
            System.out.println("                                              ║                     MENU SẢN PHẨM                      ║");
            System.out.println("                                              ║--------------------------------------------------------║");
            System.out.println("                                              ║             1. Hiển thị danh sách sản phẩm             ║");
            System.out.println("                                              ║             2. Thêm mới sản phẩm                       ║");
            System.out.println("                                              ║             3. Sửa sản phẩm                            ║");
            System.out.println("                                              ║             4. Xóa sản phẩm                            ║");
            System.out.println("                                              ║             5. Tìm kiếm sản phẩm                       ║");
            System.out.println("                                              ║             0. Thoát chương trình                      ║");
            System.out.println("                                              '————————————————————————————————————————————————————————'\n");

            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Mời bạn lựa chọn Menu :                                 |");
            int chooseMenu = ValidateInput.validateInt();
            System.out.println("'-------------------------------------------------------------'\n");
            switch (chooseMenu) {
                case 1:
                    new ProductView().showListProduct();
                    break;
                case 2:
                    new ProductView().formCreateProduct();
                    break;
                case 3:
                    new ProductView().updateProduct();
                    break;
                case 4:
                    new ProductView().deleteCategory();
                    break;
                case 5:
                    new ProductView().searchProductByName();
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
        new Navbar();
    }
}