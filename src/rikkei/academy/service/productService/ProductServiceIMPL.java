package rikkei.academy.service.productService;

import rikkei.academy.config.Config;
import rikkei.academy.config.PathConfig;
import rikkei.academy.model.productModel.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    @Override
    public void updateProduct(Product product) {
        Product product1 = findById(product.getProductId());
        product1.setProductName(product.getProductName());
        product1.setCategories(product.getCategories());
        new Config<Product>().writeToFile(PathConfig.PATH_PRODUCT, listProducts);
    }




    @Override
    public List<Product> searchProductByName(String name) {
        List<Product> searchWithName = new ArrayList<>();
        for (int i = 0; i < listProducts.size(); i++) {
            if (listProducts.get(i).getProductName().toLowerCase().contains(name.toLowerCase())) {
                searchWithName.add(listProducts.get(i));
            }
        }
        return searchWithName;
    }

//    @Override
//    public List<Product> findAllByPriceAsc() {
//        List<Product> sortPrice = new ArrayList<>();
//        for (int i = 0; i < listProducts.size(); i++) {
//            sortPrice.add(listProducts.get(i));
//        }
//        Collections.sort(sortPrice);
//        List<Product> findAllByPriceAsc = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            findAllByPriceAsc.add(sortPrice.get(i));
//        }
//
//        return findAllByPriceAsc;
//    }

    @Override
    public List<Product> findAllByPriceAsc() {
        List<Product> sortedProducts = new ArrayList<>(listProducts);
        sortedProducts.sort(Comparator.comparing(Product::getProductPrice));
        return sortedProducts;
    }

    @Override
    public List<Product> findAllByPriceDesc() {
        List<Product> sortedProducts = new ArrayList<>(listProducts);
        sortedProducts.sort(Comparator.comparing(Product::getProductPrice).reversed());
        return sortedProducts;
    }

}
