package com.example.demo.Helpers;

import com.example.demo.Helpers.Mocks.EmployeeMock;
import com.example.demo.Helpers.Mocks.InvoiceMock;

import java.util.*;

public class ConvertHashToList {
    public List<EmployeeInvoiceHelper> convertIntoListType(Hashtable<EmployeeMock, List<InvoiceMock>> hashtable){
        Iterator<EmployeeMock> employeeMockList=hashtable.keySet().iterator();
        List<InvoiceMock> invoiceMockList;
        List<EmployeeInvoiceHelper> employeeInvoiceHelperList=new ArrayList<>();
        EmployeeInvoiceHelper employeeInvoiceHelper;
        while(employeeMockList.hasNext()){
            EmployeeMock employeeMock=employeeMockList.next();
            invoiceMockList=hashtable.get(employeeMock);
            employeeInvoiceHelper=new EmployeeInvoiceHelper(employeeMock,invoiceMockList);
            employeeInvoiceHelperList.add(employeeInvoiceHelper);
        }
        return employeeInvoiceHelperList;
    }
}
