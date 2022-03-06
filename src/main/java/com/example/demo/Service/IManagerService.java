package com.example.demo.Service;

import com.example.demo.Helpers.EmployeeInvoiceHelper;
import com.example.demo.Helpers.Mocks.EmployeeMock;
import com.example.demo.Helpers.Mocks.InvoiceMock;
import com.example.demo.Helpers.Recievers.TimePeriod;
import com.example.demo.Model.Employee;
import com.example.demo.Model.Invoice;

import java.util.Hashtable;
import java.util.List;

public interface IManagerService {
    List<EmployeeInvoiceHelper> getAllInvoicesOfEmployeesForToday();

    List<EmployeeInvoiceHelper> getAllInvoicesOfEmployeesForTimePeriod(TimePeriod timePeriod);

}
