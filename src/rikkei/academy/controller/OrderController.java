package rikkei.academy.controller;

import rikkei.academy.model.User;
import rikkei.academy.model.order.Order;
import rikkei.academy.model.productModel.Category;
import rikkei.academy.model.productModel.Product;
import rikkei.academy.service.oderService.IOrderService;
import rikkei.academy.service.oderService.OrderServiceIMPL;

import java.util.List;

public class OrderController {
    IOrderService orderService = new OrderServiceIMPL();

    public List<Order> getListOrder() {
        return orderService.findAll();
    }
    public void createOrder(Order order) {
        orderService.save(order);
    }

    public void deleteOrder(int id) {
        orderService.deleteById(id);
    }

    public Order detailOrder(int id) {
        return orderService.findById(id);
    }

}
