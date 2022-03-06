package com.example.demo.Helpers;

import com.example.demo.Helpers.Mocks.EmployeeMock;
import com.example.demo.Helpers.Mocks.InvoiceMock;
import com.example.demo.Helpers.Mocks.ProductMock;
import com.example.demo.Model.Employee;
import com.example.demo.Model.Invoice;
import com.example.demo.Model.Product;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class InvoiceResponse implements Serializable {
    private EmployeeMock employee;
    private Set<ProductMock> product;
    private InvoiceMock invoice;


    public InvoiceResponse() {
    }

    public InvoiceResponse(Employee employee, Set<Product> productSet, Invoice invoice) {
        this.employee = new EmployeeMock(employee);

        this.invoice = new InvoiceMock(invoice);
        fillProductSet(productSet);
    }

    private void fillProductSet(Set<Product> productSet) {
        this.product = new HashSet<>();
        Iterator<Product> productList = productSet.iterator();
        ProductMock productMock;
        while (productList.hasNext()) {
            productMock = new ProductMock(productList.next());
            this.putOneProduct(productMock);
        }
    }

    private void putOneProduct(ProductMock productMock) {
        this.product.add(productMock);
    }

    public EmployeeMock getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = new EmployeeMock(employee);
    }

    public Set<ProductMock> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        fillProductSet(product);
    }

    public InvoiceMock getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = new InvoiceMock(invoice);
    }
}
