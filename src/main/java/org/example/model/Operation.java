package org.example.model;

public class Operation{
    //Properties
    private int idOperation;
    private int idAccount;
    private OperationStatus status;
    private double amount;

    //Constructor
    public Operation(int idOperation, int idAccount, OperationStatus status, double amount){
        this.idOperation = idOperation;
        this.idAccount = idAccount;
        this.status = status;
        this.amount = amount;
    }

    //Methods

    public int getIdOperation(){
        return idOperation;
    }

    public void setIdOperation(int idOperation){
        this.idOperation = idOperation;
    }

    public int getIdAccount(){
        return idAccount;
    }

    public void setIdAccount(int idCustomer){
        this.idAccount = idCustomer;
    }

    public OperationStatus getStatus(){
        return status;
    }

    public void setStatus(OperationStatus status){
        this.status = status;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    @Override
    public String toString(){
        return "Operation{" +
                "idOperation=" + idOperation +
                ", idCustomer=" + idAccount +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }
}
