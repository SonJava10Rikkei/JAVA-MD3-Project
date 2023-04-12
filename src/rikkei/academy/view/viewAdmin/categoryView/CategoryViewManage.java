package rikkei.academy.view.viewAdmin.categoryView;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.Config;
import rikkei.academy.config.customString.CustomString;
import rikkei.academy.config.validate.ValidateInputCustom;
import rikkei.academy.controller.CategoryController;
import rikkei.academy.model.productModel.Category;

import java.util.List;

public class CategoryViewManage {
    CategoryController categoryController = new CategoryController();
    List<Category> listCategory = categoryController.getListCategory();

    public void showListCategory() {
        System.out.println(CustomString.STR_ListCategoryView);
        for (int i = 0; i < listCategory.size(); i++) {
            if (listCategory.size() != 0) {
                System.out.printf("                                                  ║   %-2d   |   %-33s ║\n", listCategory.get(i).getId(), listCategory.get(i).getNameCategory());
            } else {
                System.out.println("|     " + ColorConfig.RED + "Danh mục hiện đang trống hãy thêm danh mục mới!" + ColorConfig.RESET + "         |");
            }
        }
        System.out.print("                                                  '——————————————————————————————————————————————'\n" +
                ".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.\n" +
                "|     Nhập phím bất kỳ để quay lại Menu :                     |\n" +
                "|     ");
        String backMenu = Config.scanner().nextLine();
        System.out.println("'-------------------------------------------------------------'\n");
        if (backMenu.equalsIgnoreCase("m")) {
            new CategoryViewMenu();
        }
    }

    public void formCreateCategory() {

        while (true) {
            int id = 0;
            if (listCategory.size() == 0) {
                id = 1;
            } else {
                id = listCategory.get(listCategory.size() - 1).getId() + 1;
            }
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Nhập loại sản phẩm mới là:                              |");
            System.out.print("|     ");
            inputCheckNull(id);
            System.out.println("|     " + ColorConfig.GREEN + "Đã thêm thành công !!!" + ColorConfig.RESET + "                                  |");
            System.out.println("'-------------------------------------------------------------'\n");
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Nhập phím bất kỳ để tiếp tục thêm danh mục sản phẩm,    |");
            System.out.println("|     hoặc nhập 'M' để quay lại Menu:                         |");
            System.out.print("|     ");
            String backMenu = Config.scanner().nextLine();
            System.out.println("'-------------------------------------------------------------'\n");
            if (backMenu.equalsIgnoreCase("m")) {
                new CategoryViewMenu();
            }
        }
    }

    public void updateCategory() {

        while (true) {
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Nhập ID của danh mục sản phẩm bạn muốn sửa:             |");
            System.out.print("|     ");
            int id = ValidateInputCustom.validateInt();
            for (int i = 0; i < listCategory.size(); i++) {
                if (id == listCategory.get(i).getId()) {
                    System.out.println("|     Danh mục sản phẩm bạn muốn chỉnh sửa là:                |");
                    System.out.println(CustomString.STR_ListCategoryView);
                    System.out.printf("                                                  ║   %-2d   |   %-33s ║\n", listCategory.get(i).getId(), listCategory.get(i).getNameCategory());
                    System.out.println("                                                  '——————————————————————————————————————————————'\n");
                }
            }
            if (categoryController.detailCategory(id) == null) {
                System.err.println("|     ID không có trong danh mục sản phẩm, hãy nhập lại:      |");
                updateCategory();
            } else {
                System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
                System.out.println("|     Nhập tên mới của danh mục sản phẩm:                     |");
                System.out.print("|     ");
                inputCheckNull(id);
                System.out.println("|" + ColorConfig.GREEN + "     Đã sửa thành công !!!                                   " + ColorConfig.RESET + "|");
            }
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Nhập phím bất kỳ để sửa danh mục khác,                  |");
            System.out.println("|     hoặc nhập 'M' để quay lại Menu:                         |");
            System.out.print("|     ");
            String backMenu = Config.scanner().nextLine();
            System.out.println("'-------------------------------------------------------------'\n");
            if (backMenu.equalsIgnoreCase("m")) {
                new CategoryViewMenu();
            }
        }
    }

    private void inputCheckNull(int id) {
        while (true) {
            String name = Config.scanner().nextLine();
            if (ValidateInputCustom.isNullOrWhiteSpace(name)) {
                System.out.println("|     " + ColorConfig.RED + "Chuỗi nhập không được bỏ trống! Hãy nhập lại:" + ColorConfig.RESET + "           |");
                System.out.print("|     ");
            } else {
                System.out.println("|     Chuỗi hợp lệ!                                           |");
                Category newCategory = new Category(id, name);
                categoryController.createCategory(newCategory);
                break;
            }
        }
    }

    public void deleteCategory() {
        while (true) {
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");

            System.out.println("|     Nhập ID của sản phẩm bạn muốn xóa:                      |");
            System.out.print("|     ");
            int targetId = ValidateInputCustom.validateInt();
            if (categoryController.detailCategory(targetId) == null) {
                System.err.println("|     ID không có trong danh sách sản phẩm hãy nhập lại:      |");
            } else {
                for (int i = 0; i < listCategory.size(); i++) {
                    if (targetId == listCategory.get(i).getId()) {
                        System.out.println("|     Danh mục sản phẩm bạn muốn chỉnh sửa là:                |\n");
                        System.out.println(CustomString.STR_ListCategoryView);
                        System.out.printf("                                                  ║   %-2d   |   %-33s ║\n", listCategory.get(i).getId(), listCategory.get(i).getNameCategory());
                        System.out.println("                                                  '——————————————————————————————————————————————'\n");
                    }
                }
                System.out.println("|" + ColorConfig.YELLOW + "  Bạn có chắc chắn muốn xóa sản phẩm này không?             " + ColorConfig.RESET + " |");
                System.out.println("|     Nhập Y để xóa hoặc N để quay lại? (y/n)                 |");
                while (true) {
                    System.out.print("|     ");
                    String deleteOption = Config.scanner().nextLine();
                    if (deleteOption.equalsIgnoreCase("y")) {
                        categoryController.deleteCategory(targetId);
                        System.out.println("|     " + ColorConfig.GREEN + "Đã xóa danh mục!!!" + ColorConfig.RESET + "                                      |");
                        break;
                    } else if (deleteOption.equalsIgnoreCase("n")) {
                        System.out.println("|     " + ColorConfig.GREEN + "Danh mục chưa được xóa" + ColorConfig.RESET + "                                  |");
                        break;
                    } else {
                        System.out.println("|     " + ColorConfig.YELLOW + "Vui lòng nhập Y hoặc N:" + ColorConfig.RESET + "                                 |");
                    }
                }
            }
            System.out.println(".---------------------" + ColorConfig.BLUE + " Tin nhắn của bạn " + ColorConfig.RESET + "----------------------.");
            System.out.println("|     Nhập phím bất kỳ để xóa danh mục khác                   |");
            System.out.println("|     hoặc nhập 'M' để quay lại Menu:                         |");
            System.out.print("|     ");
            String backMenu = Config.scanner().nextLine();
            System.out.println("'-------------------------------------------------------------'\n");
            if (backMenu.equalsIgnoreCase("m")) {
                new CategoryViewMenu();
            }
        }
    }
}
