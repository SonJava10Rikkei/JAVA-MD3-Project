package rikkei.academy.view.viewAdmin.productView;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.Config;
import rikkei.academy.controller.ProductController;
import rikkei.academy.model.productModel.Product;
import rikkei.academy.validate.ValidateInput;
import rikkei.academy.view.menuView.CustomString;
import rikkei.academy.view.viewAdmin.menuAdmin.ProductMenu;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductViewCRUD {

    ProductController productController = new ProductController();
    List<Product> productList = productController.getListProduct();
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeVN);


    public void showListProduct() {
        System.out.println(CustomString.ListProductView);
        for (int i = 0; i < productList.size(); i++) {
            String formatPrice = numberFormat.format(productList.get(i).getPrice());
            System.out.printf("                      ║   %-2d   |    %-15s   |      %-10s    |    %12s  |   %-33s ║\n", productList.get(i).getIdProduct(), productList.get(i).getNameProduct(), productList.get(i).getBrandProduct(), formatPrice, productList.get(i).getDescriptions());
        }
        System.out.println("                      '—————————————————————————————————————————————————————————————————————————————————————————————————————————————'");
        System.out.println("");
        System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
        System.out.println("|     Nhập phím bất kỳ để quay lại Menu :                     |");
        System.out.print("|     ");
        String backMenu = Config.scanner().nextLine();
        System.out.println("'-------------------------------------------------------------'\n");
        System.out.println("");
        if (backMenu.equalsIgnoreCase("menu")) {
            new ProductMenu();
        }
    }

    public void formCreateProduct() {
        while (true) {
            int id = 0;
            if (productList.size() == 0) {
                id = 1;
            } else {
                id = productList.get(productList.size() - 1).getIdProduct() + 1;
            }
            System.out.println("");
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Nhập tên của sản phẩm mới là:                           |");
            String name = Config.scanner().nextLine();
            System.out.println("|     Nhập thương hiệu của sản phẩm mới là:                   |");
            String brand = Config.scanner().nextLine();
            System.out.println("|     Nhập giá của sản phẩm mới là:                           |");
            double price = ValidateInput.validateDouble();
            System.out.println("|     Nhập mô tả của sản phẩm mới là:                         |");
            String descriptions = Config.scanner().nextLine();
            Product newProduct = new Product(id, name, brand, price, descriptions);
            productController.createProduct(newProduct);


            System.out.println("|     " + ColorConfig.GREEN + "Đã thêm thành công !!!" + ColorConfig.RESET + "                                  |");
            System.out.println("'-------------------------------------------------------------'\n");
            System.out.println("");
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Nhập 'M' để quay lại Menu,                              |");
            System.out.println("|     hoặc nhập khác để tiếp tục thêm sản phẩm                |");
            String backMenu = Config.scanner().nextLine();
            System.out.println("'-------------------------------------------------------------'\n");
            System.out.println("");
            if (backMenu.equalsIgnoreCase("m")) {
                new ProductMenu();
            }
        }
    }

    public void updateProduct() {
        while (true) {
            System.out.println("|     Nhập ID của sản phẩm bạn muốn sửa:                      |");
            int id = ValidateInput.validateInt();
            for (int i = 0; i < productList.size(); i++) {
                String formatPrice = numberFormat.format(productList.get(i).getPrice());
                if (id == productList.get(i).getIdProduct()) {
                    System.out.println("|     Sản phẩm bạn muốn chỉnh sửa là:                         |");
                    System.out.println(CustomString.ListProductView);
                    System.out.printf("                      ║   %-2d   |    %-15s   |      %-10s    |    %12s  |   %-33s ║\n", productList.get(i).getIdProduct(), productList.get(i).getNameProduct(), productList.get(i).getBrandProduct(), formatPrice, productList.get(i).getDescriptions());
                    System.out.println("                      '—————————————————————————————————————————————————————————————————————————————————————————————————————————————'");
                }
            }
            if (productController.detailProduct(id) == null) {
                System.err.println("|     ID không có trong danh sách sản phẩm hãy nhập lại:      |");
            } else {
                System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
                System.out.println("|     Nhập tên mới của sản phẩm:                              |");
                String name = Config.scanner().nextLine();
                System.out.println("|     Nhập thương hiệu mới của sản phẩm:                      |");
                String brand = Config.scanner().nextLine();
                System.out.println("|     Nhập giá mới của sản phẩm:                              |");
                double price = ValidateInput.validateDouble();
                System.out.println("|     Nhập mô tả mới của sản phẩm:                            |");
                String descriptions = Config.scanner().nextLine();
                Product newProduct = new Product(id, name, brand, price, descriptions);
                productController.createProduct(newProduct);
                System.out.println("|" + ColorConfig.GREEN + "     Đã sửa thành công !!!                                   " + ColorConfig.RESET + "|");
                System.out.println("|" + ColorConfig.YELLOW + "  Enter để sửa sản phẩm khác hoặc nhập 'M' để quay lại Menu!" + ColorConfig.RESET + " |");
                String backMenu = Config.scanner().nextLine();
                System.out.println("'-------------------------------------------------------------'\n");
                if (backMenu.equalsIgnoreCase("m")) {
                    new ProductMenu();
                }
            }
        }
    }

    public void deleteCategory() {
        while (true) {
            System.out.println("|     Nhập ID của sản phẩm bạn muốn xóa                       |");
            int targetId = ValidateInput.validateInt();
            if (productController.detailProduct(targetId) == null) {
                System.err.println("|     ID không có trong danh sách sản phẩm hãy nhập lại:      |");
            } else {
                for (int i = 0; i < productList.size(); i++) {
                    String formatPrice = numberFormat.format(productList.get(i).getPrice());
                    if (targetId == productList.get(i).getIdProduct()) {
                        System.out.println("|     Sản phẩm bạn muốn xóa là:                               |");
                        System.out.println(CustomString.ListProductView);
                        System.out.printf("                      ║   %-2d   |    %-15s   |      %-10s    |    %12s  |   %-33s ║\n", productList.get(i).getIdProduct(), productList.get(i).getNameProduct(), productList.get(i).getBrandProduct(), formatPrice, productList.get(i).getDescriptions());
                        System.out.println("                      '—————————————————————————————————————————————————————————————————————————————————————————————————————————————'");
                    }
                }
                System.out.println("|" + ColorConfig.YELLOW + "  Bạn có chắc chắn muốn xóa sản phẩm này không?             " + ColorConfig.RESET + " |");
                System.out.println("|     Nhập Y để xóa hoặc N để quay lại? (y/n)                 |");
                while (true) {
                    String deleteOption = Config.scanner().nextLine();
                    if (deleteOption.equalsIgnoreCase("y")) {
                        productController.deleteProduct(targetId);
                        System.out.println("|" + ColorConfig.GREEN + "     Đã xóa sản phẩm!!!                                      " + ColorConfig.RESET + "|");
                        break;
                    } else if (deleteOption.equalsIgnoreCase("n")) {
                        System.out.println("|" + ColorConfig.GREEN + "     Sản phẩm chưa được xóa                                  " + ColorConfig.RESET + "|");
                        break;
                    } else {
                        System.out.println("|" + ColorConfig.YELLOW + "     Vui lòng nhập Y hoặc N:                                 " + ColorConfig.RESET + "|");
                    }
                }
            }
            System.out.println("|" + ColorConfig.YELLOW + "  Enter để xóa sản phẩm khác hoặc nhập 'M' để quay lại Menu!" + ColorConfig.RESET + " |");
            String backMenu = Config.scanner().nextLine();
            System.out.println("'-------------------------------------------------------------'\n");
            if (backMenu.equalsIgnoreCase("m")) {
                new ProductMenu();
            }
        }
    }

    public void searchProductByName() {
        System.out.println("Nhập tên sản phẩm cần tìm kiếm: ");
        String name = Config.scanner().nextLine();
        List<Product> result = productController.searchProductByName(name);
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào có tên là '" + name + "'");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Product product : result) {
                System.out.println(product);
            }
        }
        System.out.println("Enter để quay lại Menu ! ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("m")) {
            new ProductMenu();
        }
    }
}
