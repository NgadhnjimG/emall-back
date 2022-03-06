package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.jni.Local;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name="employee_product_invoice")
public class EmployeeProductInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer joinID;

    public Integer getJoinID() {
        return joinID;
    }

    public void setJoinID(Integer joinID) {
        this.joinID = joinID;
    }

    public EmployeeProductInvoice() { }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="employee")
    private Employee employee;

    @JsonIgnore
    @ManyToOne
    @JoinColumn()
    private Invoice invoice;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public EmployeeProductInvoice(Product product, Employee employee, Invoice invoice) {
//        this.pk=new Pk(employee.getEmpID(), product.getProductCode(), invoice.getInvoiceID());
        this.product = product;
        this.employee = employee;
        this.invoice = invoice;
        this.localDate= LocalDate.now();
        this.localTime=LocalTime.now();
    }

    @Column
    private int ammount;

    @Column(name = "local_date",columnDefinition = "DATE")
    private LocalDate localDate;

    @Column(name = "local_time",columnDefinition = "TIME")
    private LocalTime localTime;

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }


}
