package com.example.demo.Model;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;



@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceID;
    @Column(name = "date")
    private LocalDateTime date;
    @Column
    private PaymentType paymentType;
    @Column
    private double cashMoneyGiven;
    @Column
    private double cardMoneyGiven;
    @Column
    private double change;
    @Column
    private double totalPrice;
    @Column()
    private boolean isCancelled=false;
    @Column
    private LocalDateTime cancelledAt;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<EmployeeProductInvoice> employeeProductInvoices;


    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(LocalDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public double getCashMoneyGiven() {
        return cashMoneyGiven;
    }

    public void setCashMoneyGiven(double cashMoneyGiven) {
        this.cashMoneyGiven = cashMoneyGiven;
    }

    public double getCardMoneyGiven() {
        return cardMoneyGiven;
    }

    public void setCardMoneyGiven(double cardMoneyGiven) {
        this.cardMoneyGiven = cardMoneyGiven;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Invoice() {
    }

    public Invoice(int invoiceID, LocalDateTime date, PaymentType paymentType) {
        this.invoiceID = invoiceID;
        this.date = date;
        this.paymentType = paymentType;
    }

    public Invoice(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public PaymentType getPaymentType() {
        return paymentType;
    }


    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }


    public Set<EmployeeProductInvoice> getEmployeeProductInvoices() {
        return employeeProductInvoices;
    }

    public void setEmployeeProductInvoices(Set<EmployeeProductInvoice> employeeProductInvoices) {
        this.employeeProductInvoices = employeeProductInvoices;
    }

    public void addEmployeeProductInvoices(EmployeeProductInvoice employeeProductInvoice) {
        this.employeeProductInvoices.add(employeeProductInvoice);
    }

}
