package com.example.demo.Helpers;

import com.example.demo.Helpers.Mocks.ProductMock;
import com.example.demo.Helpers.Mocks.ProductsMoneyGivenMock;
import com.example.demo.Model.Product;
import com.example.demo.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class ProductHelper {

    @Autowired
    private IProductService repo;


    public HashMap<Product, Integer> getProductList(ProductsMoneyGivenMock productsMoneyGivenMock){
        productsMoneyGivenMock.convertArrayToArrayList();

        HashMap<Product, Integer> list = new HashMap<>();

        Product product;
        for (ProductMock mock: productsMoneyGivenMock.getScannedProduct()) {
            product=mock.getProduct();

            list.put(product,mock.getQuantity());
        }
        return list;
    }

}
