package rikkei.academy.service.productService;

import rikkei.academy.config.Config;
import rikkei.academy.config.PathConfig;
import rikkei.academy.model.productModel.Product;

import java.util.List;

public class ProductServiceIMPL implements IProductService {
    List<Product> listProducts = new Config<Product>().readFormFile(PathConfig.PATH_PRODUCT);

    @Override
    public List<Product> findAll() {
        return listProducts;
    }

    @Override
    public void save(Product product) {
        if (findById(product.getProductId()) == null) {
            listProducts.add(product);
        } else {
            int index = listProducts.indexOf(findById(product.getProductId()));
            listProducts.set(index, product);
        }
        new Config<Product>().writeToFile(PathConfig.PATH_PRODUCT, listProducts);
    }

    @Override
    public Product findById(int id) {
        for (int i = 0; i < listProducts.size(); i++) {
            if (listProducts.get(i).getProductId() == id) {
                return listProducts.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        listProducts.remove(findById(id));
        new Config<Product>().writeToFile(PathConfig.PATH_PRODUCT, listProducts);
    }
}
