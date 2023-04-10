package rikkei.academy.controller;

import rikkei.academy.model.productModel.Category;
import rikkei.academy.model.productModel.Product;
import rikkei.academy.service.productService.IProductService;
import rikkei.academy.service.productService.ProductServiceIMPL;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    IProductService productService = new ProductServiceIMPL();

    public List<Product> getListProduct() {
        return productService.findAll();
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
    public List<Product> showProductWithCategory(int id) {
        List<Product> productList = new ArrayList<>();
        for (Product product : productService.findAll()) {
            for (Category category : product.getCategories()) {
                if (id == category.getId()) {
                    productList.add(product);
                }
            }
        }
        return productList;
    }

    public List<Product> searchProductByName(String name) {
        return productService.searchProductByName(name);
    }
    public List<Product> findAllByPriceAsc(){
        return productService.findAllByPriceAsc();
    }
    public List<Product> findAllByPriceDesc(){
        return productService.findAllByPriceDesc();
    }
}
