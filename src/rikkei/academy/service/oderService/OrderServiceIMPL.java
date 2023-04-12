package rikkei.academy.service.oderService;

import rikkei.academy.config.Config;
import rikkei.academy.config.PathConfig;
import rikkei.academy.model.order.Order;

import java.util.List;

public class OrderServiceIMPL implements IOrderService {
    List<Order> orderList = new Config<Order>().readFormFile(PathConfig.PATH_ORDER);


    @Override
    public List<Order> findAll() {
        return orderList;
    }

    @Override
    public void save(Order order) {
        if (findById(order.getOrderId()) == null) {
            orderList.add(order);
        } else {
            int index = orderList.indexOf(findById(order.getOrderId()));
            orderList.set(index, order);
        }
        new Config<Order>().writeToFile(PathConfig.PATH_ORDER, orderList);
    }

    @Override
    public Order findById(int id) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderId() == id) {
                return orderList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        orderList.remove(findById(id));
        new Config<Order>().writeToFile(PathConfig.PATH_PRODUCT, orderList);
    }
}
