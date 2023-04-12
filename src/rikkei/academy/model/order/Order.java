package rikkei.academy.model.order;

import rikkei.academy.model.User;
import rikkei.academy.model.productModel.Product;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private int orderId;
    private Date orderDate;
    private double total;
    private List<Product> productOrder;
    private User userOrder;
    private String status = "pending";

    public Order() {
    }

    public Order(int orderId, Date orderDate, double total, List<Product> productOrder, User userOrder) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.total = total;
        this.productOrder = productOrder;
        this.userOrder = userOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order(int orderId, int productId, int quantity) {
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Product> getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(List<Product> productOrder) {
        this.productOrder = productOrder;
    }

    public User getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(User userOrder) {
        this.userOrder = userOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", total=" + total +
                ", products=" + productOrder +
                ", user=" + userOrder +
                ", status='" + status + '\'' +
                '}';
    }
}

