package com.example.demo.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyID;
    @Column
    private String companyName;
    @Column
    private String companyAddress;
    @Column
    private String companyContact;
    @Column
    private String companyEmail;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Stock",
            joinColumns = @JoinColumn(name = "companyID"),
            inverseJoinColumns = @JoinColumn(name = "productID"))
    private Set<Product> products = new HashSet<Product>(0);
//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Employee> employees=new HashSet<Employee>(0);

    public Company(String companyName, String companyAddress, String companyContact, String companyEmail) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyContact = companyContact;
        this.companyEmail = companyEmail;
    }

    public Company(int companyID) {
        this.companyID = companyID;
    }

    public Company(int companyID, Set<Product> productList) {
        this.companyID = companyID;
        this.products = productList;
    }

    public Company() {
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return companyID == company.companyID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyID);
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addOneProduct(Product prod) {
        this.products.add(prod);
    }

    public void addOneProductMultipleTimes(Product product, int times) {
        do {
            this.products.add(product);
            times--;
        } while (times > 0);
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Set<Employee> employees;


    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee){
        this.employees.add(employee);
    }

}
