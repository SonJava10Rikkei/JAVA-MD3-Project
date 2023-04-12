package rikkei.academy.model.order;

import rikkei.academy.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private int cartId;
    private double total;
    private List<CartDetail> productCart = new ArrayList<>();
    private User userCart;

    public Cart() {
    }

    public Cart(int id, User user) {
        this.cartId = id;
        this.userCart = user;
    }

    public Cart(int cartId, double total, User userCart) {
        this.cartId = cartId;
        this.total = total;
        this.userCart = userCart;
    }


    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<CartDetail> getProductCart() {
        return productCart;
    }

    public void setProductCart(List<CartDetail> productCart) {
        this.productCart = productCart;
    }

    public User getUserCart() {
        return userCart;
    }

    public void setUserCart(User userCart) {
        this.userCart = userCart;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", total=" + total +
                ", productCart=" + productCart +
                ", userCart=" + userCart +
                '}';
    }
}
