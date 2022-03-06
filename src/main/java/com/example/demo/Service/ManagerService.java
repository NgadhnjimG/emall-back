package com.example.demo.Service;

import com.example.demo.Helpers.ConvertHashToList;
import com.example.demo.Helpers.EmployeeInvoiceHelper;
import com.example.demo.Helpers.Mocks.EmployeeMock;
import com.example.demo.Helpers.Mocks.InvoiceMock;
import com.example.demo.Helpers.Recievers.TimePeriod;
import com.example.demo.Helpers.UserInfoHelper;
import com.example.demo.Model.Employee;
import com.example.demo.Model.EmployeeProductInvoice;
import com.example.demo.Model.Invoice;
import com.example.demo.Repository.EmployeeProductInvoiceRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

@Service
public class ManagerService implements IManagerService{

    @Autowired
    private EmployeeProductInvoiceRepository employeeProductInvoiceRepository;

    @Override
    public List<EmployeeInvoiceHelper> getAllInvoicesOfEmployeesForToday() {
        int id= UserInfoHelper.getCompanyID();
        Hashtable<EmployeeMock,List<InvoiceMock>> hashlist=classifyEmployeesWithInvoices(this.employeeProductInvoiceRepository.getAllByCompanyId(id),null);

        List<EmployeeInvoiceHelper> finalList=new ConvertHashToList().convertIntoListType(hashlist);

        return finalList;
    }

    @Override
    public List<EmployeeInvoiceHelper> getAllInvoicesOfEmployeesForTimePeriod(TimePeriod timePeriod) {
        int id= UserInfoHelper.getCompanyID();
        Hashtable<EmployeeMock,List<InvoiceMock>> hashlist=classifyEmployeesWithInvoices(this.employeeProductInvoiceRepository.getAllByCompanyId(id),timePeriod);

        return null;
    }

    private Hashtable<EmployeeMock,List<InvoiceMock>> classifyEmployeesWithInvoices(List<EmployeeProductInvoice> employeeProductInvoiceList, TimePeriod timePeriod){

        Hashtable<EmployeeMock,List<InvoiceMock>> employeeListHashtable=new Hashtable<>();
        Employee employee;
        Invoice invoice;
        EmployeeMock employeeMock;
        InvoiceMock invoiceMock;
        List<InvoiceMock> invoiceList=new ArrayList<>();
        for (EmployeeProductInvoice EPI:employeeProductInvoiceList) {
            if(EPI.getLocalDate()!= null && EPI.getLocalDate().isBefore(LocalDate.now())) continue;
            if(timePeriod!= null && EPI.getLocalDate()==null && (EPI.getLocalDate().isBefore(timePeriod.getStartingDatePeriod())||EPI.getLocalDate().isAfter(timePeriod.getEndingDatePeriod()))) continue;
            employee=EPI.getEmployee();
            employeeMock=new EmployeeMock(employee);
            invoice=EPI.getInvoice();
            invoiceMock=new InvoiceMock(invoice);
            invoiceList=new ArrayList<>();
            employeeMock=employeeMockIsInList(employeeMock,employeeListHashtable);
            if(employeeMock!=null) {
                invoiceList=employeeListHashtable.get(employeeMock);
                if(invoiceMockIsInList(invoiceMock,invoiceList)) continue;
                invoiceList.add(invoiceMock);
                employeeListHashtable.replace(employeeMock,invoiceList);
                continue;
            }
            employeeMock=new EmployeeMock(employee);
            invoiceList.add(invoiceMock);
            employeeListHashtable.put(employeeMock,invoiceList);
        }
        return employeeListHashtable;
    }

    private EmployeeMock employeeMockIsInList(EmployeeMock employeeMock, Hashtable<EmployeeMock,List<InvoiceMock>> hashtable) {
        Iterator<EmployeeMock> employeeMockIterator=hashtable.keySet().iterator();
        while(employeeMockIterator.hasNext()){
            EmployeeMock employeeMock1=employeeMockIterator.next();
            if(employeeMock1.equals(employeeMock))
                return employeeMock1;
        }
        return null;
    }

    private boolean invoiceMockIsInList(InvoiceMock invoiceMock, List<InvoiceMock> invoiceMocksList){
        Iterator<InvoiceMock> invoiceMockIterator=invoiceMocksList.listIterator();
        while(invoiceMockIterator.hasNext()){
            if(invoiceMockIterator.next().equals(invoiceMock)) return true;
        }
        return false;
    }
}
