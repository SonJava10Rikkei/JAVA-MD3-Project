package rikkei.academy.controller;

import rikkei.academy.model.productModel.Product;
import rikkei.academy.service.product.IProductService;
import rikkei.academy.service.product.ProductServiceIMPL;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    IProductService productService = new ProductServiceIMPL();

    public List<Product> getListProduct() {
        return productService.getAll();
    }

    public void createProduct(Product product) {
        productService.save(product);
    }

    public Product detailProduct(int id) {
        return productService.findById(id);
    }

    public void updateProduct(Product product) {
        productService.save(product);
    }

    public void deleteProduct(int id) {
        productService.deleteById(id);
    }

    public List<Product> searchProductByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : productService.getAll()) {
            if (product.getNameProduct().equalsIgnoreCase(name)) {
                result.add(product);
            }
        }
        return result;
    }
}
