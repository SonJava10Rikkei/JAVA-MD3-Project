package rikkei.academy.model.productModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable, Comparable<Product> {
    static final long serialVersionUID = 1L;
    private int productId;
    private String productName;
    private String productBrand;
    private double productPrice;
    private int quantity;
    private List<Category> categories;
    private String descriptions;
    private boolean status = true;

    public Product() {
    }

    public Product(int productId, String productName, String productBrand, double productPrice, int quantity, List<Category> categories, String descriptions) {
        this.productId = productId;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.categories = categories;
        this.descriptions = descriptions;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public String showListCategory() {
        List<String> categoryNames = new ArrayList<>();
        for (Category category : this.categories) {
            categoryNames.add(category.getNameCategory());
        }
        return String.join(", ", categoryNames);
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", productPrice=" + productPrice +
                ", quantity=" + quantity +
                ", categories=" + categories +
                ", descriptions='" + descriptions + '\'' +
                '}';
    }

    @Override
    public int compareTo(Product o) {
        return 0;
    }
}
