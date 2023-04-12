package rikkei.academy.view.viewuser.orderUser;

import rikkei.academy.config.Config;
import rikkei.academy.config.customString.CustomString;
import rikkei.academy.controller.CartController;
import rikkei.academy.controller.OrderController;
import rikkei.academy.controller.ProductController;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;
import rikkei.academy.model.order.Cart;
import rikkei.academy.model.order.CartDetail;
import rikkei.academy.model.order.Order;
import rikkei.academy.model.productModel.Category;
import rikkei.academy.model.productModel.Product;
import rikkei.academy.view.viewAdmin.productView.ProductViewMenu;
import rikkei.academy.view.viewAll.HomePageMenu;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CartViewManage {
//    OrderController orderController = new OrderController();
    //    List<Order> listOrder = orderController.getListOrder();
    ProductController productController = new ProductController();
    List<Product> listProduct = productController.getListProduct();

//    UserController userController = new UserController();
    CartController cartController = new CartController();
    List<Cart> cartList = cartController.getListCart();
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeVN);

    public void showCart(User loginUser) {

        if (cartList.size() == 0) {
            System.out.println(" Khong co nguoi dung nao! ");
        } else {
            System.out.println("List of Orders:");
            System.out.println("------------------------------");
            for (Cart cart : cartList) {
                if (cart.getUserCart().getId() == loginUser.getId()) {
                    if (cart.getProductCart().size() == 0) {
                        System.out.println("GIo hang trong!");
                    } else {
                        System.out.println("List of Orders:");
                        System.out.println("------------------------------");
                        System.out.println("Cart ID: " + cart.getCartId());
                        System.out.println("User ID: " + cart.getUserCart().getId());
                        for (CartDetail cartDetail : cart.getProductCart()) {
                            System.out.println("detail ID" + cartDetail.getCartDetailId());
                            System.out.println("Ten sp" + cartDetail.getProduct().getProductName());
                            System.out.println("s luong" + cartDetail.getQuantity());
                        }
                        System.out.println("Total Amount: " + numberFormat.format(cart.getTotal()));
                        System.out.println("------------------------------");
                    }
                }
            }
        }

    }

    public void addToCartById(User loginUser) {
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

        Scanner scanner = new Scanner(System.in);
        int productId, quantity;
        Product selectedProduct = null;
        while (true) {
            System.out.print("Enter product ID: ");
            productId = scanner.nextInt();
            System.out.print("Enter quantity: ");
            quantity = scanner.nextInt();
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
                System.out.println("Khong tim thay sp theo Id nay");
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
        Product remainProduct = null;
        for (Product product : listProduct) {
            if (product.getProductId() == productId) {
                int currentQuantyti = product.getQuantity();
                int newQuantyti = currentQuantyti - quantity;
                if (newQuantyti >= 0) {
                    product.setQuantity(newQuantyti);
                    remainProduct = product;
                    break;
                } else {
                    System.out.println("Khong con du hang vui nhap laij so luong!");
                    System.out.println("|     Nhập phím bất kỳ để tiếp tục mua sản phẩm khac          |");
                    System.out.println("|     hoặc nhập 'M' để quay lại Menu:                         |");
                    System.out.print("|     ");
                    String backMenu = Config.scanner().nextLine();
                    System.out.println("'-------------------------------------------------------------'\n");
                    if (backMenu.equalsIgnoreCase("m")) {
                        new CartViewMenu();
                    } else {
                        addToCartById(loginUser);
                    }
                }
            }
        }
        if (existedCart) {
            CartDetail updateCartDetail = productCart.get(index);
            int newQuantity = updateCartDetail.getQuantity() + quantity;
            updateCartDetail.setQuantity(newQuantity);
            System.out.println("Cap nhat so luong sp thanh cong!");
        } else {
            int cartDetailId = 0;
            if (productCart.size() == 0) {
                cartDetailId = 1;
            } else {
                cartDetailId = productCart.get(productCart.size() - 1).getCartDetailId() + 1;
            }
            CartDetail newCartDetail = new CartDetail(cartDetailId, selectedProduct, quantity);
            productCart.add(newCartDetail);
            System.out.println("Them san phan moi thanh cong");
        }

        double total = currentCart.getTotal() + selectedProduct.getProductPrice() * quantity;
        currentCart.setTotal(total);
        cartController.createCart(currentCart);
        productController.updateProduct(remainProduct);
        System.out.println("|     Nhập phím bất kỳ để tiếp tục mua sản phẩm khac          |");
        System.out.println("|     hoặc nhập 'M' để quay lại Menu:                         |");
        System.out.print("|     ");
        String backMenu = Config.scanner().nextLine();
        System.out.println("'-------------------------------------------------------------'\n");
        if (backMenu.equalsIgnoreCase("m")) {
            new CartViewMenu();
        } else {
            addToCartById(loginUser);
        }

    }
}
