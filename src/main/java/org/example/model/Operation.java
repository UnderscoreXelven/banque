package org.example.model;

public class Operation{
    //Properties
    private int idOperation;
    private int idCustomer;
    private OperationStatus status;
    private double amount;

    //Constructor
    public Operation(int idOperation, int idCustomer, OperationStatus status, double amount){
        this.idOperation = idOperation;
        this.idCustomer = idCustomer;
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

    public int getIdCustomer(){
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer){
        this.idCustomer = idCustomer;
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
                ", idCustomer=" + idCustomer +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }
}
