package com.example.demo.Service;

import com.example.demo.Model.Employee;

import java.util.Set;

public interface EmployeeInterface {
    Employee getEmployeeById(int id);
    void updateEmployees(Set<Employee> employees);
}
