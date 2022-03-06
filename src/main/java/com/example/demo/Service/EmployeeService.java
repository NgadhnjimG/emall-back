package com.example.demo.Service;

import com.example.demo.Model.Employee;
import com.example.demo.Model.Invoice;
import com.example.demo.Model.Privileges;
import com.example.demo.Repository.EmployeeProductInvoiceRepository;
import com.example.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService implements IEmployeeService, EmployeeInterface {

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private PrivilegeInterface privilegeInterface;

    @Autowired
    private InvoiceInterface invoiceInterface;

    @Autowired
    private EmployeeProductInvoiceRepository employeeProductInvoiceRepository;



    @Override
    public Employee verifyLogin(String username) {
        Employee emp = repo.findByUsernameAndPassword(username);
        if (emp != null) return emp;
        return null;
    }

    @Override
    public void setPrivileges(List<Privileges> privilegesList, int empID) {
        Employee emp = getEmployeeById(empID);
        Iterator<Privileges> privilegesIterator = privilegesList.iterator();
        Privileges privileges;
        while (privilegesIterator.hasNext()) {
            privileges = privilegesIterator.next();
            privileges = privilegeInterface.getPrivilegeById(privileges.getPrivilegeID());
            emp.addPrivileges(privileges);
        }

        repo.saveAndFlush(emp);
    }

    @Override
    public Employee getEmployeeOfInvoice(Invoice invoice) {

        return invoiceInterface.findEmployeeByInvoice(invoice.getInvoiceID());
    }

    @Override
    public Employee getEmployeeById(int id) {
        Optional<Employee> employee = repo.findById(id);
        if (employee.isEmpty()) return null;
        return employee.get();
    }

    @Override
    public void updateEmployees(Set<Employee> employees) {
        this.repo.saveAll(employees);
    }

//    @Override
//    public Employee getEmployeeOfInvoice(Invoice invoice) {
//
//        Employee employee=employeeProductInvoiceRepository.findByInvoice(invoice);
//        return employee;
//    }

}
