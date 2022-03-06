package com.example.demo.Service;

import com.example.demo.Model.Employee;
import com.example.demo.Model.Invoice;
import com.example.demo.Model.Privileges;

import java.util.List;

public interface IEmployeeService {

    Employee verifyLogin(String username);

    void setPrivileges(List<Privileges> privileges, int empID);

    Employee getEmployeeOfInvoice(Invoice invoice);

}
