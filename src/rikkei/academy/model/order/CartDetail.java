package rikkei.academy.model.order;

import rikkei.academy.model.productModel.Product;

import java.io.Serializable;

public class CartDetail implements Serializable {
    private int cartDetailId;
    private Product product;
    private int quantity;

    public CartDetail() {
    }

    public CartDetail(int cartDetailId, Product product, int quantity) {
        this.cartDetailId = cartDetailId;
        this.product = product;
        this.quantity = quantity;
    }

    public int getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(int cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartDetail{" +
                "cartDetailId=" + cartDetailId +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
