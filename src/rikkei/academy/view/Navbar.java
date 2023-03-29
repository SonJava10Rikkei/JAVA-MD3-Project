package rikkei.academy.view;

import rikkei.academy.config.Config;

public class Navbar {
    public Navbar() {
        while (true) {
            System.out.println("*************** CATEGORY MANAGE *************");
            System.out.println("1. Hiển thị danh sách sản phẩm ");
            System.out.println("2. Thêm mới sản phẩm ");
            System.out.println("3. Sửa sản phẩm ");
            System.out.println("4. Xóa sản phẩm ");
            System.out.println("5. Tìm kiếm sản phẩm ");
            System.out.println("0. Thoát chương trình ");
            System.out.println("Mời bạn lựa chọn : ");
            int chooseMenu = Config.scanner().nextInt();
            switch (chooseMenu) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Hãy nhập lại lựa chọn của bạn (0-8)");
            }
        }
    }

    public static void main(String[] args) {
        new Navbar();
    }
}
