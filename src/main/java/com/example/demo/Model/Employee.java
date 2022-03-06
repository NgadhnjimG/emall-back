package com.example.demo.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empID;
    @Column
    private String empName;
    @Column
    private String empLastName;
    @Column
    private String empContact;
    @Column(unique = true)
    private String empUserName;
    @Column
    private String empPassword;
    @Column
    private int empSalary;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<EmployeeProductInvoice> employeeProductInvoices;

    public Set<EmployeeProductInvoice> getEmployeeProductInvoices() {
        return employeeProductInvoices;
    }

    public void setEmployeeProductInvoices(Set<EmployeeProductInvoice> employeeProductInvoices) {
        this.employeeProductInvoices = employeeProductInvoices;
    }

    public void addEmployeeProductInvoices(EmployeeProductInvoice employeeProductInvoice){
        this.employeeProductInvoices.add(employeeProductInvoice);
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Employee_Privileges",
            joinColumns = @JoinColumn(name = "empID"),
            inverseJoinColumns = @JoinColumn(name = "privilegeID"))
    private Set<Privileges> privileges = new HashSet<Privileges>(0);

    public Employee() {

    }

    public Employee(int empID) {
        this.empID = empID;
    }

    public Employee(String empName, String empLastName, String empContact, String empUserName, String empPassword, int empSalary) {
        this.empName = empName;
        this.empLastName = empLastName;
        this.empContact = empContact;
        this.empUserName = empUserName;
        this.empPassword = empPassword;
        this.empSalary = empSalary;
    }


    public int getEmpID() {
        return empID;
    }

    public Employee(int empID, Set<Privileges> privilegesList) {
        this.empID = empID;
        this.privileges = privilegesList;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }


    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public String getEmpContact() {
        return empContact;
    }

    public void setEmpContact(String empContact) {
        this.empContact = empContact;
    }

    public String getEmpUserName() {
        return empUserName;
    }

    public void setEmpUserName(String empUserName) {
        this.empUserName = empUserName;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public int getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(int empSalary) {
        this.empSalary = empSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return empID == employee.empID &&
                Objects.equals(empUserName, employee.empUserName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empID, empUserName);
    }

    public Set<Privileges> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privileges> privileges) {
        this.privileges = privileges;
    }

    public void addPrivileges(Privileges privilege){
        this.privileges.add(privilege);
    }

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
