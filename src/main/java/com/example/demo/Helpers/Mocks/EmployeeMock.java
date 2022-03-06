package com.example.demo.Helpers.Mocks;

import com.example.demo.Model.Employee;

import javax.persistence.Column;
import java.io.Serializable;

public class EmployeeMock implements Serializable {
    private int empID;
    private String empName;
    private String empLastName;
    private String empContact;
    private String empUserName;
    private String empPassword;
    private int empSalary;

    public EmployeeMock(int empID, String empName, String empLastName, String empContact, String empUserName, String empPassword, int empSalary) {
        this.empID = empID;
        this.empName = empName;
        this.empLastName = empLastName;
        this.empContact = empContact;
        this.empUserName = empUserName;
        this.empPassword = empPassword;
        this.empSalary = empSalary;
    }

    public EmployeeMock() {
    }

    public EmployeeMock(Employee emp){
        this.empContact=emp.getEmpContact();
        this.empID=emp.getEmpID();
        this.empLastName=emp.getEmpLastName();
        this.empName=emp.getEmpName();
        this.empContact=emp.getEmpContact();
        this.empSalary=emp.getEmpSalary();
        this.empUserName=emp.getEmpUserName();
    }

    public int getEmpID() {
        return empID;
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
    public boolean equals(Object obj) {
        if(obj==null) return false;
        return ((EmployeeMock)obj).getEmpID()==this.getEmpID();
    }
}
