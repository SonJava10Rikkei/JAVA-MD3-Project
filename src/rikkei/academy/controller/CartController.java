package rikkei.academy.controller;

import rikkei.academy.model.order.Cart;
import rikkei.academy.model.order.Order;
import rikkei.academy.service.oderService.CartServiceIMPL;
import rikkei.academy.service.oderService.ICart;

import java.util.List;

public class CartController {
    CartServiceIMPL cartServiceIMPL = new CartServiceIMPL();
    public List<Cart> getListCart() {
        return cartServiceIMPL.findAll();
    }
    public void createCart(Cart cart) {
        cartServiceIMPL.save(cart);
    }

    public void deleteCart(int id) {
        cartServiceIMPL.deleteById(id);
    }

    public Cart detailOrder(int id) {
        return cartServiceIMPL.findById(id);
    }
}
