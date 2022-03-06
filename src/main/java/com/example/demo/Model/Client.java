package com.example.demo.Model;

import javax.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int clientID;
    @Column
    private String clientName;
    @Column
    private String clientLastName;

    public Client(String clientName, String clientLastName) {
        this.clientName = clientName;
        this.clientLastName = clientLastName;
    }

    public Client() {
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public Client(int clientID) {
        this.clientID = clientID;
    }

    public void ProductList(){

    }
}
