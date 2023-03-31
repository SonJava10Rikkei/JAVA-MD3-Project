package rikkei.academy.model.productModel;

import java.io.Serializable;

public class Product implements Serializable {
    private int idProduct;
    private String nameProduct;
    private String brandProduct;
    private double price;
    private String descriptions;

    public Product() {
    }

    public Product(int idProduct, String nameProduct, String brandProduct, double price, String descriptions) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.brandProduct = brandProduct;
        this.price = price;
        this.descriptions = descriptions;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getBrandProduct() {
        return brandProduct;
    }

    public void setBrandProduct(String brandProduct) {
        this.brandProduct = brandProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return "Product{ " +
                "idProduct = " + idProduct +
                ", nameProduct = '" + nameProduct + '\'' +
                ", brandProduct = '" + brandProduct + '\'' +
                ", price = " + price +
                ", descriptions = '" + descriptions + '\'' +
                '}';
    }
}
