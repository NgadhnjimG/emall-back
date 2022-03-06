package com.example.demo.Helpers;

import com.example.demo.Helpers.Mocks.EmployeeMock;
import com.example.demo.Helpers.Mocks.InvoiceMock;
import com.example.demo.Model.Employee;
import com.example.demo.Model.Invoice;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeInvoiceHelper implements Serializable {

    private EmployeeMock employee;
    private List<InvoiceMock> invoiceList;

    public EmployeeInvoiceHelper(Employee employee, List<Invoice> invoiceList) {
        this.employee = new EmployeeMock(employee);
        fillInvoice(invoiceList);
    }

    public EmployeeInvoiceHelper(EmployeeMock employeeMock,List<InvoiceMock> list){
        this.employee=employeeMock;
        this.invoiceList=list;
    }

    private void fillInvoice(List<Invoice> list) {
        invoiceList=new ArrayList<>();
        Iterator<Invoice> invoiceIterator=list.listIterator();
        InvoiceMock invoiceMock;
        while(invoiceIterator.hasNext()){
            invoiceMock=new InvoiceMock(invoiceIterator.next());
            this.addInvoiceMockToList(invoiceMock);
        }
    }

    private void addInvoiceMockToList(InvoiceMock invoiceMock){
        if(invoiceMock== null) return;
        this.invoiceList.add(invoiceMock);
    }

    public EmployeeInvoiceHelper() {

    }

    public EmployeeMock getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = new EmployeeMock(employee);
    }

    public List<InvoiceMock> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<InvoiceMock> invoiceList) {
        this.invoiceList = invoiceList;
    }
}
