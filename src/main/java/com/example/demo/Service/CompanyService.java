package com.example.demo.Service;

import com.example.demo.Model.Company;
import com.example.demo.Model.Employee;
import com.example.demo.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class CompanyService implements ICompanyService {

    private Company company;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeInterface employeeInterface;

    @Override
    public void addEmployees(List<Employee> employees, int companyId) {
        company = companyRepository.findByCompanyID(companyId);
        Iterator<Employee> employeeIterator = employees.iterator();
        Set<Employee> employeeSet = new HashSet<>();
        Employee employee;
        while (employeeIterator.hasNext()) {
            employee = employeeInterface.getEmployeeById(employeeIterator.next().getEmpID());
            employee.setCompany(company);
            company.addEmployee(employee);
            employeeSet.add(employee);
        }
        employeeInterface.updateEmployees(employeeSet);
        this.companyRepository.save(company);
    }
}
