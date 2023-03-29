package rikkei.academy.service.product;

import rikkei.academy.config.Config;
import rikkei.academy.model.Product;

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
//            Product product1 = findById(product.getIdProduct());
//            product1.setNameProduct(product.getNameProduct());
//            product1.setBrandProduct(product.getBrandProduct());
//            product1.setPrice(product.getPrice());
//            product1.setDescriptions(product.getDescriptions());
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
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getIdProduct() == id) {
                productList.remove(i);
            }
        }
        new Config<Product>().writeToFile(Config.PATH_PRODUCT, productList);
    }
}
