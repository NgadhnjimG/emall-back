package com.example.demo.Helpers.Mocks;


import com.example.demo.Model.Invoice;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class InvoiceMock implements Serializable {

    private int invoiceID;
    private LocalDateTime mydate;
    private int paymentType;
    private double totalPrice;

    public InvoiceMock() {
    }

    public InvoiceMock(Invoice inv){
        this.invoiceID= inv.getInvoiceID();
        this.mydate= inv.getDate();
        this.totalPrice= inv.getTotalPrice();
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public LocalDateTime getMydate() {
        return mydate;
    }

    public void setMydate(LocalDateTime mydate) {
        this.mydate = mydate;
    }


    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null) return false;
        return ((InvoiceMock)obj).getInvoiceID()==this.getInvoiceID();
    }
}
