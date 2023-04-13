package rikkei.academy.view.viewuser.orderUser;

import rikkei.academy.config.ColorConfig;
import rikkei.academy.config.Config;
import rikkei.academy.config.customString.CustomString;
import rikkei.academy.config.validate.ValidateInputCustom;
import rikkei.academy.controller.CartController;
import rikkei.academy.controller.ProductController;
import rikkei.academy.model.User;
import rikkei.academy.model.order.Cart;
import rikkei.academy.model.order.CartDetail;
import rikkei.academy.model.productModel.Category;
import rikkei.academy.model.productModel.Product;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CartViewManage {
    ProductController productController = new ProductController();
    List<Product> listProduct = productController.getListProduct();
    CartController cartController = new CartController();
    List<Cart> cartList = cartController.getListCart();
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeVN);

    public void showCart(User loginUser) {

        if (cartList.size() == 0) {
            System.out.println("|     " + ColorConfig.RED + "Không có người dùng nào !" + ColorConfig.RESET + "                               |");
        } else {
            ShowListProductInCart(loginUser);
        }
    }

    public void addProductToCartById(User loginUser) {
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

        Cart currentCart = null;

        for (Cart cart : cartList) {
            if (cart.getUserCart().getId() == loginUser.getId()) {
                currentCart = cart;
                break;
            }
        }
        List<CartDetail> productCart = currentCart.getProductCart();
        int productId, quantity;
        Product selectedProduct = null;
        while (true) {
            System.out.println(".-----------------------" + ColorConfig.BLUE + " Mua sản phẩm " + ColorConfig.RESET + "------------------------.");
            System.out.println("|     Nhập ID của sản phẩm bạn muốn mua:                      |");
            System.out.print("|     ");
            productId = ValidateInputCustom.validateInt();

            System.out.println("|     Nhập số lượng sản phẩm bạn muốn mua:                    |");
            System.out.print("|     ");
            while (true) {
                quantity = ValidateInputCustom.validateInt();
                if (quantity > 0) {
                    break;
                } else {
                    System.out.println("|     " + ColorConfig.RED + "Số lượng sản phẩm bạn muốn mua phải lớn hơn 0:" + ColorConfig.RESET + "          |");
                    System.out.println("|     Vui lòng nhập lại:          |");
                    System.out.print("|     ");
                }
            }
            boolean flag = false;
            for (Product product : listProduct) {
                if (product.getProductId() == productId) {
                    selectedProduct = product;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            } else {
                System.out.println("|     " + ColorConfig.RED + "Không tìm thấy sản phẩm theo ID này !" + ColorConfig.RESET + "                   |");
            }
        }
        boolean existedCart = false;
        int index = -1;
        for (int i = 0; i < productCart.size(); i++) {
            CartDetail cartDetail = productCart.get(i);
            if (selectedProduct.getProductId() == cartDetail.getProduct().getProductId()) {
                existedCart = true;
                index = i;
                break;
            }
        }
        //        Product remainProduct = null;
//        for (Product product : listProduct) {
//            if (product.getProductId() == productId) {
//                int currentQuantyti = product.getQuantity();
//                int newQuantyti = currentQuantyti - quantity;
//                if (newQuantyti >= 0) {
//                    product.setQuantity(newQuantyti);
//                    remainProduct = product;
//                    break;
//                } else {
//                    System.out.println("Khong con du hang vui nhap laij so luong!");
//                    System.out.println("|     Nhập phím bất kỳ để tiếp tục mua sản phẩm khac          |");
//                    System.out.println("|     hoặc nhập 'M' để quay lại Menu:                         |");
//                    System.out.print("|     ");
//                    String backMenu = Config.scanner().nextLine();
//                    System.out.println("'-------------------------------------------------------------'\n");
//                    if (backMenu.equalsIgnoreCase("m")) {
//                        new CartViewMenu();
//                    } else {
//                        addProductToCartById(loginUser);
//                    }
//                }
//            }
//        }
        if (existedCart) {
            CartDetail updateCartDetail = productCart.get(index);
            int newQuantity = updateCartDetail.getQuantity() + quantity;
            for (Product product : listProduct) {
                if (product.getProductId() == productId) {
                    if (newQuantity > product.getQuantity()) {
                        System.out.println(" Số lượng mới đã cộng: " + newQuantity);
                        System.out.println(" trong kho: " + product.getQuantity());
                        System.out.println("" + ColorConfig.RED + "Sản phẩm bạn mua số lượng quá lớn !" + ColorConfig.RESET + " ");
                        System.out.println("" + ColorConfig.RED + "Đã có " + updateCartDetail.getQuantity() + " sản phẩm trong giỏ rồi !! Vui lòng nhập lại:" + ColorConfig.RESET + " ");
                        addProductToCartById(loginUser);
                    }
                }
            }
            updateCartDetail.setQuantity(newQuantity);
            System.out.println("|     " + ColorConfig.GREEN + "Cập nhật số lượng sản phẩm mua thành công !" + ColorConfig.RESET + "             |");
        } else {
            for (Product product : listProduct) {
                if (product.getProductId() == productId) {
                    if (quantity > product.getQuantity()) {
                        System.out.println(" Số lượng mua : " + quantity);
                        System.out.println(" trong kho: " + product.getQuantity());
                        System.out.println("" + ColorConfig.RED + "Sản phẩm bạn mua số lượng quá lớn !" + ColorConfig.RESET + " ");
                        System.out.println("" + ColorConfig.RED + "Hãy nhập từ 1 đến " + product.getQuantity() + " sản phẩm ! Vui lòng nhập lại:" + ColorConfig.RESET + " ");
                        addProductToCartById(loginUser);
                    }
                }
            }
            int cartDetailId = 0;
            if (productCart.size() == 0) {
                cartDetailId = 1;
            } else {
                cartDetailId = productCart.get(productCart.size() - 1).getCartDetailId() + 1;
            }
            CartDetail newCartDetail = new CartDetail(cartDetailId, selectedProduct, quantity);
            productCart.add(newCartDetail);
            System.out.println("|     " + ColorConfig.GREEN + "Thêm sản phẩm mới vào giỏ hàng thành công !" + ColorConfig.RESET + "             |");
        }
        double total = currentCart.getTotal() + selectedProduct.getProductPrice() * quantity;
        currentCart.setTotal(total);
        cartController.createCart(currentCart);
//        productController.updateProduct(remainProduct);
        System.out.println("|     Nhập phím bất kỳ để tiếp tục mua sản phẩm khác          |");
        System.out.println("|     hoặc nhập 'M' để quay lại Menu:                         |");
        System.out.print("|     ");
        String backMenu = Config.scanner().nextLine();
        System.out.println("'-------------------------------------------------------------'\n");
        if (backMenu.equalsIgnoreCase("m")) {
            new CartViewMenu();
        } else {
            addProductToCartById(loginUser);
        }
    }


    public void removeProductfromCartById(User loginUser) {
        ShowListProductInCart(loginUser);
        Cart currentCart = null;
        for (Cart cart : cartList) {
            if (cart.getUserCart().getId() == loginUser.getId()) {
                currentCart = cart;
                break;
            }
        }
        List<CartDetail> productCart = currentCart.getProductCart();
        int productId, quantity;
        Product selectedProduct = null;
        while (true) {
            System.out.println(".-----------------------" + ColorConfig.BLUE + " Bớt sản phẩm " + ColorConfig.RESET + "------------------------.");
            System.out.println("|     Nhập ID của sản phẩm bạn muốn bớt:                      |");
            System.out.print("|     ");
            productId = ValidateInputCustom.validateInt();
            System.out.println("|     Nhập số lượng sản phẩm bạn muốn bớt:                    |");
            System.out.print("|     ");
            while (true) {
                quantity = ValidateInputCustom.validateInt();
                if (quantity > 0) {
                    break;
                }
                System.out.println("|     " + ColorConfig.RED + "Số lượng sản phẩm bạn muốn bớt phải lớn hơn 0:" + ColorConfig.RESET + "          |");
                System.out.println("|     Vui lòng nhập lại:                                      |");
                System.out.print("|     ");

            }
            boolean flag = false;
            for (Product product : listProduct) {
                if (product.getProductId() == productId) {
                    selectedProduct = product;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            } else {
                System.out.println("|     " + ColorConfig.RED + "Không tìm thấy sản phẩm theo ID này !" + ColorConfig.RESET + "                   |");
            }
        }
        boolean existedCart = false;
        int index = -1;
        for (int i = 0; i < productCart.size(); i++) {
            CartDetail cartDetail = productCart.get(i);
            if (selectedProduct.getProductId() == cartDetail.getProduct().getProductId()) {
                existedCart = true;
                index = i;
                break;
            }
        }

        if (existedCart) {
            CartDetail updateCartDetail = productCart.get(index);
            int newQuantity = updateCartDetail.getQuantity() - quantity;
            if (quantity > updateCartDetail.getQuantity()) {
                System.out.println("" + ColorConfig.RED + "Bớt sản phẩm với số lượng quá lớn! Vui lòng nhập lại:" + ColorConfig.RESET + " ");
                removeProductfromCartById(loginUser);
            } else if (quantity == updateCartDetail.getQuantity()) {
                System.out.println("|     " + ColorConfig.RED + "Số lượng sẽ bằng 0 !" + ColorConfig.RESET + "                                    |");
                System.out.println("|     " + ColorConfig.RED + "Bạn có muốn xóa sản phẩm này khỏi giỏ hàng không ?" + ColorConfig.RESET + "      |");
                System.out.println("|     " + ColorConfig.YELLOW + "Mời bạn chọn có hoặc không (y/n) ?" + ColorConfig.RESET + "                   |");
                System.out.print("|     ");
                while (true) {
                    System.out.print("|     ");
                    String deleteOption = Config.scanner().nextLine();
                    if (deleteOption.equalsIgnoreCase("y")) {
                        List<CartDetail> updateDetail = currentCart.getProductCart();
                        for (int i = 0; i < updateDetail.size(); i++) {
                            if (selectedProduct.getProductId() == updateDetail.get(i).getProduct().getProductId()) {
                                updateDetail.remove(i);
                                break;
                            }
                        }
                        currentCart.setProductCart(updateDetail);


                        System.out.println("|     " + ColorConfig.GREEN + "Đã xóa sản phẩm!!!" + ColorConfig.RESET + "                                      |");
                        break;
                    } else if (deleteOption.equalsIgnoreCase("n")) {
                        System.out.println("|     " + ColorConfig.GREEN + "Sản phẩm chưa được xóa" + ColorConfig.RESET + "                                  |");
                        removeProductfromCartById(loginUser);
                    } else {
                        System.out.println("|     " + ColorConfig.YELLOW + "Vui lòng nhập Y hoặc N:" + ColorConfig.RESET + "                                 |");
                    }
                }

            }
            updateCartDetail.setQuantity(newQuantity);
            System.out.println("|     " + ColorConfig.GREEN + "Cập nhật số lượng sản phẩm thành công !" + ColorConfig.RESET + "                 |");

            double total = currentCart.getTotal() - selectedProduct.getProductPrice() * quantity;
            currentCart.setTotal(total);

            cartController.createCart(currentCart);
            System.out.println("|     Nhập phím bất kỳ để tiếp tục xóa sản phẩm khác          |");
            System.out.println("|     hoặc nhập 'M' để quay lại Menu:                         |");
            System.out.print("|     ");
            String backMenu = Config.scanner().nextLine();
            System.out.println("'-------------------------------------------------------------'\n");
            if (backMenu.equalsIgnoreCase("m")) {
                new CartViewMenu();
            } else {
                removeProductfromCartById(loginUser);
            }
        }
    }

    private void ShowListProductInCart(User loginUser) {
        System.out.println(
                "\n                          .————————————————————————————— DANH SÁCH SẢN PHẨM TRONG GIỎ HÀNG ———————————————————————————————.\n" +
                        "                          ║        |          |                      |                    |                  |            ║\n" +
                        "                          ║   STT  |    ID    |        TÊN SP        |     THƯƠNG HIỆU    |     ĐƠN GIÁ      |   SL MUA   ║\n" +
                        "                          ║        |          |                      |                    |                  |            ║\n" +
                        "                          ║-----------------------------------------------------------------------------------------------║");
        for (Cart cart : cartList) {
            if (cart.getUserCart().getId() == loginUser.getId()) {
                if (cart.getProductCart().size() == 0) {
                    System.out.println("                          ║---------------------------- " + ColorConfig.RED + "Giỏ hàng trống không có sản phẩm nào ! " + ColorConfig.RESET + "---------------------------║");

                } else {
                    for (CartDetail cartDetail : cart.getProductCart()) {
                        String formatPrice = numberFormat.format(cartDetail.getProduct().getProductPrice());
                        System.out.printf("                          ║   %-2d   |    %-2d    |    %-15s   |      %-10s    |    %12s  |     %-2d     ║\n",
                                cartDetail.getCartDetailId(),
                                cartDetail.getProduct().getProductId(),
                                cartDetail.getProduct().getProductName(),
                                cartDetail.getProduct().getProductBrand(),
                                formatPrice,
                                cartDetail.getQuantity()
                        );
                    }
                    System.out.println("                          ║-----------------------------------------------------------------------------------------------║");
                    System.out.println("                          ║          " + ColorConfig.GREEN + "" + "Tổng tiền: " + numberFormat.format(cart.getTotal()) + ColorConfig.RESET);
                    System.out.println("                          '———————————————————————————————————————————————————————————————————————————————————————————————'\n");
                }
            }
        }
    }

    public void Payment(User loginUser) {

    }

    public void cccc() {
        //        Product remainProduct = null;
//        for (Product product : listProduct) {
//            if (product.getProductId() == productId) {
//                int currentQuantyti = product.getQuantity();
//                int newQuantyti = currentQuantyti - quantity;
//                if (newQuantyti >= 0) {
//                    product.setQuantity(newQuantyti);
//                    remainProduct = product;
//                    break;
//                } else {
//                    System.out.println("Khong con du hang vui nhap laij so luong!");
//                    System.out.println("|     Nhập phím bất kỳ để tiếp tục mua sản phẩm khac          |");
//                    System.out.println("|     hoặc nhập 'M' để quay lại Menu:                         |");
//                    System.out.print("|     ");
//                    String backMenu = Config.scanner().nextLine();
//                    System.out.println("'-------------------------------------------------------------'\n");
//                    if (backMenu.equalsIgnoreCase("m")) {
//                        new CartViewMenu();
//                    } else {
//                        addProductToCartById(loginUser);
//                    }
//                }
//            }
//        }
    }
}
