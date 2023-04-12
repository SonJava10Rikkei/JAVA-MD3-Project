package rikkei.academy.service.oderService;

import rikkei.academy.config.Config;
import rikkei.academy.config.PathConfig;
import rikkei.academy.model.order.Cart;
import rikkei.academy.model.order.Order;

import java.util.List;

public class CartServiceIMPL implements ICart {
    List<Cart> cartList = new Config<Cart>().readFormFile(PathConfig.PATH_CART);

    @Override
    public List<Cart> findAll() {
        return cartList;
    }

    @Override
    public void save(Cart cart) {
        if (findById(cart.getCartId()) == null) {
            cartList.add(cart);
        } else {
            int index = cartList.indexOf(findById(cart.getCartId()));
            cartList.set(index, cart);
        }
        new Config<Cart>().writeToFile(PathConfig.PATH_CART, cartList);
    }

    @Override
    public Cart findById(int id) {
        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).getCartId() == id) {
                return cartList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
