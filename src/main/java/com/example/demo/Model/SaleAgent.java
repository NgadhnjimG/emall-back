package com.example.demo.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="SaleAgent")
@PrimaryKeyJoinColumn(name = "SaleAgentID")
public class SaleAgent extends Employee{


    public SaleAgent() {
    }

    public SaleAgent(String empName, String empLastName, String empContact, String empUserName, String empPassword, int empSalary) {
        super(empName, empLastName, empContact, empUserName, empPassword, empSalary);
    }



    public SaleAgent(int empID, Set<Privileges> privilegesList) {
        super(empID, privilegesList);
    }

    @ManyToOne
    private Manager manager;

}
