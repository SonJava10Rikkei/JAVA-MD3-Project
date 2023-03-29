package rikkei.academy.view.product;

import rikkei.academy.controller.ProductController;
import rikkei.academy.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductView {
    ProductController productController = new ProductController();
    List<Product> productList = productController.getListProduct();

    public void showListProduct() {
        for (Product product : productList) {
            System.out.println(product);
        }

    }
}
