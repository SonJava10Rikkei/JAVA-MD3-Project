package rikkei.academy.model.productModel;

import java.io.Serializable;

public class Product implements Serializable {
    private int productId;
    private String productName;
    private String productBrand;
    private double productPrice;
    private String descriptions;

    public Product() {
    }

    public Product(int productId, String productName, String productBrand, double productPrice, String descriptions) {
        this.productId = productId;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productPrice = productPrice;
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
                ", descriptions='" + descriptions + '\'' +
                '}';
    }

}
