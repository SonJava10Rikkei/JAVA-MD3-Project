package rikkei.academy.view.product;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.Config;
import rikkei.academy.controller.ProductController;
import rikkei.academy.model.Product;
import rikkei.academy.validate.ValidateInput;
import rikkei.academy.view.Navbar;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class ProductView {

    ProductController productController = new ProductController();
    List<Product> productList = productController.getListProduct();


    public void showListProduct() {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeVN);


        numberFormat.setCurrency(Currency.getInstance(localeVN));
        System.out.println("                      .———————————————————————————————————————————— DANH SÁCH SẢN PHẨM —————————————————————————————————————————————.");
        System.out.println("                      ║        |                      |                    |                  |                                     ║");
        System.out.println("                      ║   MSP  |        TÊN           |     THƯƠNG HIỆU    |       GIÁ        |               MÔ TẢ                 ║");
        System.out.println("                      ║        |                      |                    |                  |                                     ║");
        System.out.println("                      ║-------------------------------------------------------------------------------------------------------------║");
        for (int i = 0; i < productList.size(); i++) {
            String str3 = numberFormat.format(productList.get(i).getPrice());
            System.out.printf("                      ║    %d   |    %-15s   |      %-10s    |    %12s  |   %-33s ║\n", productList.get(i).getIdProduct(), productList.get(i).getNameProduct(), productList.get(i).getBrandProduct(), str3, productList.get(i).getDescriptions());
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
            new Navbar();
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
            System.out.println("");
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Nhập 'M' để quay lại Menu,                           |");
            System.out.println("|     hoặc nhập khác để tiếp tục thêm sản phẩm           |");
            System.out.print("|     ");
            String backMenu = Config.scanner().nextLine();
            System.out.println("'-------------------------------------------------------------'\n");
            System.out.println("");
            if (backMenu.equalsIgnoreCase("m")) {
                new Navbar();
            }
        }
    }

    public void updateProduct() {
        while (true) {
            System.out.println("Nhập ID của sản phẩm bạn muốn sửa: ");
            int id = ValidateInput.validateInt();
            for (int i = 0; i < productList.size(); i++) {
                if (id == productList.get(i).getIdProduct()) {
                    System.out.println("Sản phẩm bạn muốn chỉnh sửa là: " + productList.get(i));
                }
            }
            if (productController.detailProduct(id) == null) {
                System.out.println("ID không hợp lệ!");
            } else {
                System.out.println("Nhập tên mới của sản phẩm: ");
                String name = Config.scanner().nextLine();
                System.out.println("Nhập thương hiệu mới của sản phẩm: ");
                String brand = Config.scanner().nextLine();
                System.out.println("Nhập giá mới của sản phẩm: ");
                double price = ValidateInput.validateDouble();
                System.out.println("Nhập mô tả mới của sản phẩm: ");
                String descriptions = Config.scanner().nextLine();
                Product newProduct = new Product(id, name, brand, price, descriptions);
                productController.createProduct(newProduct);
                System.err.println("Đã sửa thành công !!!");
                System.out.println("Enter để sửa sản phẩm khác hoặc nhập 'MENU' để quay lại Menu ! ");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("menu")) {
                    new Navbar();
                }
            }
        }
    }

    public void deleteCategory() {
        while (true) {
            System.out.println("Nhập Id của sản phẩm bạn muốn xóa");
            int targetId = ValidateInput.validateInt();
            if (productController.detailProduct(targetId) == null) {
                System.err.println("Id khong ton tai trong danh sach!");
            } else {
                productController.deleteProduct(targetId);
                System.out.println("Đã xóa sản phẩm!");
            }
            System.out.println("Enter để xóa sản phẩm khác hoặc nhập 'MENU' để quay lại Menu ! ");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("menu")) {
                new Navbar();
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
        if (backMenu.equalsIgnoreCase("menu")) {
            new Navbar();
        }
    }


}
