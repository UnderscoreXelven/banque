package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    //Properties
    private int idAccount;
    private Customer customer;
    private List<Operation> operations = new ArrayList<>();
    private double totalAmount;

    //Constructor
    public BankAccount(int idAccount, Customer customer, List<Operation> operations, double totalAmount) {
        this.idAccount = idAccount;
        this.customer = customer;
        this.operations = operations;
        this.totalAmount = totalAmount;
    }

    //Methods
    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "idAccount=" + idAccount +
                ", customer=" + customer +
                ", operations=" + operations +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
