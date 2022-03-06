package com.example.demo.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productCode;
    @Column
    private String productDescription;
    @Column
    private String productName;
    @Column
    private Double productPrice;


    public Product(int productCode, int ammount) {
        this.productCode = productCode;

    }


    public Product(int productCode, String productDescription, String productName, double price) {
        this.productCode = productCode;
        this.productDescription = productDescription;
        this.productName = productName;
        this.productPrice = price;

    }


    public Product() {
    }


    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
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

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
//    private Set<EmployeeProductInvoice> employeeProductInvoices;
//
//    public Set<EmployeeProductInvoice> getEmployeeProductInvoices() {
//        return employeeProductInvoices;
//    }
//
//    public void setEmployeeProductInvoices(Set<EmployeeProductInvoice> employeeProductInvoices) {
//        this.employeeProductInvoices = employeeProductInvoices;
//    }
//
//    public void addEmployeeProductInvoices(EmployeeProductInvoice employeeProductInvoice){
//        this.employeeProductInvoices.add(employeeProductInvoice);
//    }
}
