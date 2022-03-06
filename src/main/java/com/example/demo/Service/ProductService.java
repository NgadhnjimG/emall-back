package com.example.demo.Service;

import com.example.demo.Model.Product;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    private ProductRepository repo;

    public ProductService(ProductRepository repo){
        this.repo=repo;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productsList=this.repo.findAll();
        return productsList;
    }

    @Override
    public Product getProductById(int id) {
        return repo.findByProductCode(id);
    }
}
