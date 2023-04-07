package rikkei.academy.view.viewAdmin.productView;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.Config;
import rikkei.academy.config.validate.InputConfig;
import rikkei.academy.controller.CategoryController;
import rikkei.academy.controller.ProductController;
import rikkei.academy.model.productModel.Category;
import rikkei.academy.model.productModel.Product;
import rikkei.academy.config.validate.ValidateInput;
import rikkei.academy.config.customString.CustomString;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class ProductViewManage {

    ProductController productController = new ProductController();
    List<Product> listProduct = productController.getListProduct();
    CategoryController categoryController = new CategoryController();
    List<Category> listCategory = categoryController.getListCategory();
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeVN);

    public void showListProduct() {
        System.out.println(CustomString.STR_ListProductView);
        for (int i = 0; i < listProduct.size(); i++) {
            Product product = listProduct.get(i);
            List<String> categories = product.getCategories().stream().map(Category::getNameCategory).collect(Collectors.toList());
            String categoriesString = String.join(", ", categories);
            String formatPrice = numberFormat.format(product.getProductPrice());
            System.out.printf("       ║   %-2d   |    %-15s   |      %-10s    |    %12s  |   %-2d   |    %-15s   |   %-33s ║\n",
                    product.getProductId(),
                    product.getProductName(),
                    product.getProductBrand(),
                    formatPrice,
                    product.getQuantity(),
                    categoriesString,
                    product.getDescriptions());
        }
        System.out.println("       '—————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————'\n");
        System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
        System.out.println("|     Nhập phím bất kỳ để quay lại Menu :                     |");
        System.out.print("|     ");
        String backMenu = Config.scanner().nextLine();
        System.out.println("'-------------------------------------------------------------'\n");
        System.out.println("");
        if (backMenu.equalsIgnoreCase("menu")) {
            new ProductViewMenu();
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
            String name = InputConfig.getString();
            System.out.println("|     Nhập thương hiệu của sản phẩm mới :                     |");
            System.out.print("|     ");
            String brand = InputConfig.getString();
            System.out.println("|     Nhập giá của sản phẩm mới :                             |");
            System.out.print("|     ");
            double price = ValidateInput.validateDouble();
            System.out.println("|     Nhập số lượng sản phẩm mới :                            |");
            System.out.print("|     ");
            int quantity = ValidateInput.validateInt();

            System.out.println("|     Nhập danh mục của sản phẩm mới :                        |\n");
            List<Category> listSelectCategory = new ArrayList<>();
            selectCategory(listSelectCategory);

            System.out.println("|     Nhập mô tả của sản phẩm mới :                         |");
            System.out.print("|     ");
            String descriptions = InputConfig.getString();

            Product newProduct = new Product(id, name, brand, price, quantity, listSelectCategory, descriptions);
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
                new ProductViewMenu();
            }
        }
    }

    public void updateProduct() {
        while (true) {
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Nhập ID của sản phẩm bạn muốn sửa:                      |");
            System.out.print("|     ");
            int id = ValidateInput.validateInt();
            System.out.println(CustomString.STR_ListProductView);
            for (int i = 0; i < listProduct.size(); i++) {
                String formatPrice = numberFormat.format(listProduct.get(i).getProductPrice());
                if (id == listProduct.get(i).getProductId()) {
                    System.out.printf("       ║   %-2d   |    %-15s   |      %-10s    |    %12s  |   %-2d   |    %-15s   |   %-33s ║\n",
                            listProduct.get(i).getProductId(),
                            listProduct.get(i).getProductName(),
                            listProduct.get(i).getProductBrand(),
                            formatPrice,
                            listProduct.get(i).getQuantity(),
                            listProduct.get(i).showListCategory(),
                            listProduct.get(i).getDescriptions());
                    System.out.println("       '—————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————'\n");
                }
            }
            if (productController.detailProduct(id) == null) {
                System.err.println("|     ID không có trong danh sách sản phẩm hãy nhập lại:      |");
                updateProduct();
            } else {
                System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
                System.out.println("|     Nhập tên mới của sản phẩm:                              |");
                System.out.print("|     ");
                String name = InputConfig.getString();
                System.out.println("|     Nhập thương hiệu mới của sản phẩm:                      |");
                System.out.print("|     ");
                String brand = InputConfig.getString();
                System.out.println("|     Nhập giá mới của sản phẩm:                              |");
                System.out.print("|     ");
                double price = ValidateInput.validateDouble();
                System.out.println("|     Nhập số lượng sản phẩm mới :                            |");
                System.out.print("|     ");
                int quantity = ValidateInput.validateInt();
                System.out.println("|     Nhập danh mục mới của sản phẩm :                        |\n");
                List<Category> listSelectCategory = new ArrayList<>();
                selectCategory(listSelectCategory);
                System.out.println("|     Nhập mô tả mới của sản phẩm:                            |");
                System.out.print("|     ");
                String descriptions = InputConfig.getString();

                Product newProduct = new Product(id, name, brand, price, quantity, listSelectCategory, descriptions);
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
                new ProductViewMenu();
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
                System.out.println(CustomString.STR_ListProductView);
                for (int i = 0; i < listProduct.size(); i++) {
                    String formatPrice = numberFormat.format(listProduct.get(i).getProductPrice());
                    if (targetId == listProduct.get(i).getProductId()) {
                        System.out.printf("       ║   %-2d   |    %-15s   |      %-10s    |    %12s  |   %-2d   |    %-15s   |   %-33s ║\n",
                                listProduct.get(i).getProductId(),
                                listProduct.get(i).getProductName(),
                                listProduct.get(i).getProductBrand(),
                                formatPrice,
                                listProduct.get(i).getQuantity(),
                                listProduct.get(i).showListCategory(),
                                listProduct.get(i).getDescriptions());
                        System.out.println("       '—————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————'\n");
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
                new ProductViewMenu();
            }


        }
    }

//    public void searchProductByName() {
//        System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
//
//        System.out.println("Nhập tên sản phẩm cần tìm kiếm: ");
//        System.out.print("|     ");
//        String name = Config.scanner().nextLine();
//        List<Product> result = productController.searchProductByName(name);
//        if (result.isEmpty()) {
//            System.out.println("Không tìm thấy sản phẩm nào có tên là '" + name + "'");
//        } else {
//            System.out.println("Kết quả tìm kiếm:");
//            for (Product product : result) {
//                System.out.println(product);
//            }
//        }
//        System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
//        System.out.println("Enter để quay lại Menu ! ");
//        System.out.print("|     ");
//        String backMenu = Config.scanner().nextLine();
//        if (backMenu.equalsIgnoreCase("m")) {
//            new ProductViewMenu();
//        }
//    }

    public void selectCategory(List<Category> listSelectCategory) {
        System.out.println(CustomString.STR_ListCategoryView);
        for (int i = 0; i < listCategory.size(); i++) {
            if (listCategory.size() != 0) {
                System.out.printf("                                                  ║   %-2d   |   %-33s ║\n", listCategory.get(i).getId(), listCategory.get(i).getNameCategory());
            }
        }
        System.out.println("                                                  '——————————————————————————————————————————————'");
        while (true) {
            System.out.println("|     Nhập ID danh mục của sản phẩm mà bạn muốn chọn:         |");
            System.out.print("|     ");
            int idSelect = ValidateInput.validateInt();
            if (categoryController.detailCategory(idSelect) == null) {
                System.out.println("|     " + ColorConfig.RED + "ID bạn chọn không có trong danh mục, hãy nhập lại!" + ColorConfig.RESET + "      |");
            } else {
                Category category = categoryController.detailCategory(idSelect);
                listSelectCategory.add(category);
                break;
            }
        }
    }
}
