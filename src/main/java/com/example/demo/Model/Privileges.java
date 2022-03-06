package com.example.demo.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Privileges {
    private int privilegeID;
    private String privilegeDescription;

    public Privileges(int privilegeID) {
        this.privilegeID = privilegeID;
    }

    public Privileges(String privilegeDescription) {
        this.privilegeDescription = privilegeDescription;
    }

    public Privileges() {

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPrivilegeID() {
        return privilegeID;
    }

    public void setPrivilegeID(int privilegeID) {
        this.privilegeID = privilegeID;
    }

    @Column(nullable = false)
    public String getPrivilegeDescription() {
        return privilegeDescription;
    }

    public void setPrivilegeDescription(String pricilegeDescription) {
        this.privilegeDescription = pricilegeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privileges that = (Privileges) o;
        return Objects.equals(privilegeDescription, that.privilegeDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privilegeDescription);
    }
}
