package rikkei.academy.service.productService;

import rikkei.academy.model.productModel.Product;
import rikkei.academy.service.IGenericService;

import java.util.List;

public interface IProductService extends IGenericService<Product> {
    void updateProduct(Product product);
    List<Product> searchProductByName(String name);

    List<Product> findAllByPriceAsc();

    List<Product> findAllByPriceDesc();

}
