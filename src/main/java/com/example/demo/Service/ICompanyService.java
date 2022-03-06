package com.example.demo.Service;

import com.example.demo.Model.Employee;

import java.util.List;

public interface ICompanyService {
    void addEmployees(List<Employee> employees,int companyId);
}
