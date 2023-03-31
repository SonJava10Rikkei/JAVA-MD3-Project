package rikkei.academy.service.product;

import rikkei.academy.config.Config;
import rikkei.academy.model.productModel.Product;

import java.util.List;

public class ProductServiceIMPL implements IProductService {
    List<Product> productList = new Config<Product>().readFormFile(Config.PATH_PRODUCT);

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        if (findById(product.getIdProduct()) == null) {
            productList.add(product);
        } else {
            int index = productList.indexOf(findById(product.getIdProduct()));
            productList.set(index, product);
        }
        new Config<Product>().writeToFile(Config.PATH_PRODUCT, productList);
    }

    @Override
    public Product findById(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getIdProduct() == id) {
                return productList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        productList.remove(findById(id));
        new Config<Product>().writeToFile(Config.PATH_PRODUCT, productList);
    }
}
