package com.example.demo.Service;

import com.example.demo.Model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product getProductById(int id);
}
