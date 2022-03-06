package com.example.demo.Helpers.Mocks;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class ProductsMoneyGivenMock implements Serializable {
    private List<ProductMock> scannedProduct;
    private ProductMock[] scannedProducts;
    private double cashMoneyGiven;
    private double cardMoneyGiven;
    private String[] paymentTypes;

    public double getCardMoneyGiven() {
        return cardMoneyGiven;
    }

    public void setCardMoneyGiven(double cardMoneyGiven) {
        this.cardMoneyGiven = cardMoneyGiven;
    }

    public double getCashMoneyGiven() {
        return cashMoneyGiven;
    }

    public void setCashMoneyGiven(double cashMoneyGiven) {
        this.cashMoneyGiven = cashMoneyGiven;
    }

    public String[] getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(String[] paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public List<ProductMock> getScannedProduct() {
        return scannedProduct;
    }

    public void setScannedProduct(List<ProductMock> scannedProduct) {
        this.scannedProduct = scannedProduct;
    }

    public ProductMock[] getScannedProducts() {
        return scannedProducts;
    }

    public void setScannedProducts(ProductMock[] scannedProducts) {
        this.scannedProducts = scannedProducts;
    }


    public ProductsMoneyGivenMock() {
    }


    public void addProductMock(ProductMock productMock) {
        this.scannedProduct.add(productMock);
    }


    public void convertArrayToArrayList() {
        this.scannedProduct = Arrays.asList(this.scannedProducts.clone());
    }
}
