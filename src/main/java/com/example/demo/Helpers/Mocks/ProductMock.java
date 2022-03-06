package com.example.demo.Helpers.Mocks;

import com.example.demo.Model.Product;

import java.io.Serializable;

public class ProductMock implements Serializable {

    private Product product;
    private String productDescription;
    private String productName;
    private Double productPrice;
    private int productCode;
    private int quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductMock(int productCode, int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public ProductMock(Product product){
        this.productCode=product.getProductCode();
        this.productDescription=product.getProductDescription();
        this.productName=product.getProductName();
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
