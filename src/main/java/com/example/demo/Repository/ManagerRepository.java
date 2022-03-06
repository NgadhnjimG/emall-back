package com.example.demo.Repository;

import com.example.demo.Model.Employee;

import javax.transaction.Transactional;

@Transactional
public interface ManagerRepository extends EmployeeBaseRepository<Employee> {
}
