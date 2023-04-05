package rikkei.academy.view.viewAdmin.productView;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.Config;
import rikkei.academy.controller.ProductController;
import rikkei.academy.model.productModel.Product;
import rikkei.academy.config.validate.ValidateInput;
import rikkei.academy.config.customString.CustomString;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductManage {

    ProductController productController = new ProductController();
    List<Product> listProduct = productController.getListProduct();
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeVN);

    public void showListProduct() {
        System.out.println(CustomString.ListProductView);
        for (int i = 0; i < listProduct.size(); i++) {
            String formatPrice = numberFormat.format(listProduct.get(i).getProductPrice());
            System.out.printf("                      ║   %-2d   |    %-15s   |      %-10s    |    %12s  |   %-33s ║\n", listProduct.get(i).getProductId(), listProduct.get(i).getProductName(), listProduct.get(i).getProductBrand(), formatPrice, listProduct.get(i).getDescriptions());
        }
        System.out.println("                      '—————————————————————————————————————————————————————————————————————————————————————————————————————————————'\n");
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
            if (listProduct.size() == 0) {
                id = 1;
            } else {
                id = listProduct.get(listProduct.size() - 1).getProductId() + 1;
            }
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Nhập tên của sản phẩm mới là:                           |");
            System.out.print("|     ");
            String name = Config.scanner().nextLine();
            System.out.println("|     Nhập thương hiệu của sản phẩm mới là:                   |");
            System.out.print("|     ");
            String brand = Config.scanner().nextLine();
            System.out.println("|     Nhập giá của sản phẩm mới là:                           |");
            System.out.print("|     ");
            double price = ValidateInput.validateDouble();
            System.out.println("|     Nhập mô tả của sản phẩm mới là:                         |");
            System.out.print("|     ");
            String descriptions = Config.scanner().nextLine();
            Product newProduct = new Product(id, name, brand, price, descriptions);
            productController.createProduct(newProduct);
            System.out.println("|     " + ColorConfig.GREEN + "Đã thêm thành công !!!" + ColorConfig.RESET + "                                  |");
            System.out.println("'-------------------------------------------------------------'\n");
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Nhập phím bất kỳ để tiếp tục thêm sản phẩm,             |");
            System.out.println("|     hoặc nhập 'M' để quay lại Menu:                         |");
            System.out.print("|     ");
            String backMenu = Config.scanner().nextLine();
            System.out.println("'-------------------------------------------------------------'\n");
            if (backMenu.equalsIgnoreCase("m")) {
                new ProductMenu();
            }
        }
    }

    public void updateProduct() {
        while (true) {
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Nhập ID của sản phẩm bạn muốn sửa:                      |");
            System.out.print("|     ");
            int id = ValidateInput.validateInt();
            for (int i = 0; i < listProduct.size(); i++) {
                String formatPrice = numberFormat.format(listProduct.get(i).getProductPrice());
                if (id == listProduct.get(i).getProductId()) {
                    System.out.println("|     Sản phẩm bạn muốn chỉnh sửa là:                         |");
                    System.out.println(CustomString.ListProductView);
                    System.out.printf("                      ║   %-2d   |    %-15s   |      %-10s    |    %12s  |   %-33s ║\n", listProduct.get(i).getProductId(), listProduct.get(i).getProductName(), listProduct.get(i).getProductBrand(), formatPrice, listProduct.get(i).getDescriptions());
                    System.out.println("                      '—————————————————————————————————————————————————————————————————————————————————————————————————————————————'");
                }
            }
            if (productController.detailProduct(id) == null) {
                System.err.println("|     ID không có trong danh sách sản phẩm hãy nhập lại:      |");
                updateProduct();
            } else {
                System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
                System.out.println("|     Nhập tên mới của sản phẩm:                              |");
                System.out.print("|     ");
                String name = Config.scanner().nextLine();
                System.out.println("|     Nhập thương hiệu mới của sản phẩm:                      |");
                System.out.print("|     ");
                String brand = Config.scanner().nextLine();
                System.out.println("|     Nhập giá mới của sản phẩm:                              |");
                System.out.print("|     ");
                double price = ValidateInput.validateDouble();
                System.out.println("|     Nhập mô tả mới của sản phẩm:                            |");
                System.out.print("|     ");
                String descriptions = Config.scanner().nextLine();
                Product newProduct = new Product(id, name, brand, price, descriptions);
                productController.createProduct(newProduct);
                System.out.println("|" + ColorConfig.GREEN + "     Đã sửa thành công !!!                                   " + ColorConfig.RESET + "|");

            }
//            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Nhập phím bất kỳ để sửa sản phẩm khác,                  |");
            System.out.println("|     hoặc nhập 'M' để quay lại Menu:                         |");
            System.out.print("|     ");
            String backMenu = Config.scanner().nextLine();
            System.out.println("'-------------------------------------------------------------'\n");
            if (backMenu.equalsIgnoreCase("m")) {
                new ProductMenu();
            }
        }
    }

    public void deleteCategory() {
        while (true) {
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");

            System.out.println("|     Nhập ID của sản phẩm bạn muốn xóa:                      |");
            System.out.print("|     ");
            int targetId = ValidateInput.validateInt();
            if (productController.detailProduct(targetId) == null) {
                System.err.println("|     ID không có trong danh sách sản phẩm hãy nhập lại:      |");
            } else {
                for (int i = 0; i < listProduct.size(); i++) {
                    String formatPrice = numberFormat.format(listProduct.get(i).getProductPrice());
                    if (targetId == listProduct.get(i).getProductId()) {
                        System.out.println("|     Sản phẩm bạn muốn xóa là:                               |");
                        System.out.println(CustomString.ListProductView);
                        System.out.printf("                      ║   %-2d   |    %-15s   |      %-10s    |    %12s  |   %-33s ║\n", listProduct.get(i).getProductId(), listProduct.get(i).getProductName(), listProduct.get(i).getProductBrand(), formatPrice, listProduct.get(i).getDescriptions());
                        System.out.println("                      '—————————————————————————————————————————————————————————————————————————————————————————————————————————————'\n");
                    }
                }
                System.out.println("|" + ColorConfig.YELLOW + "  Bạn có chắc chắn muốn xóa sản phẩm này không?             " + ColorConfig.RESET + " |");
                System.out.println("|     Nhập Y để xóa hoặc N để quay lại? (y/n)                 |");
                while (true) {
                    System.out.print("|     ");
                    String deleteOption = Config.scanner().nextLine();
                    if (deleteOption.equalsIgnoreCase("y")) {
                        productController.deleteProduct(targetId);
                        System.out.println("|     " + ColorConfig.GREEN + "Đã xóa sản phẩm!!!" + ColorConfig.RESET + "                                      |");
                        break;
                    } else if (deleteOption.equalsIgnoreCase("n")) {
                        System.out.println("|     " + ColorConfig.GREEN + "Sản phẩm chưa được xóa" + ColorConfig.RESET + "                                  |");
                        break;
                    } else {
                        System.out.println("|     " + ColorConfig.YELLOW + "Vui lòng nhập Y hoặc N:" + ColorConfig.RESET + "                                 |");
                    }
                }
            }
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Nhập phím bất kỳ để xóa sản phẩm khác                   |");
            System.out.println("|     hoặc nhập 'M' để quay lại Menu:                         |");
            System.out.print("|     ");
            String backMenu = Config.scanner().nextLine();
            System.out.println("'-------------------------------------------------------------'\n");
            if (backMenu.equalsIgnoreCase("m")) {
                new ProductMenu();
            }
        }
    }

    public void searchProductByName() {
        System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");

        System.out.println("Nhập tên sản phẩm cần tìm kiếm: ");
        System.out.print("|     ");
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
        System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
        System.out.println("Enter để quay lại Menu ! ");
        System.out.print("|     ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("m")) {
            new ProductMenu();
        }
    }
}
