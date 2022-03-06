package com.example.demo.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "ManagerID")
public class Manager extends Employee {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "SaleAgentID")
    private Set<SaleAgent> employees = new HashSet<>(0);

    public Manager() {
    }

    public Manager(String empName, String empLastName, String empContact, String empUserName, String empPassword, int empSalary) {
        super(empName, empLastName, empContact, empUserName, empPassword, empSalary);
    }

    public Manager(int empID, Set<Privileges> privilegesList) {
        super(empID, privilegesList);
    }


    public Set<SaleAgent> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<SaleAgent> employees) {
        this.employees = employees;
    }

    public void addOneEmployee(SaleAgent emp) {
        if (this.employees.contains(emp)) return;

        this.employees.add(emp);
    }
}
