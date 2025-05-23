package org.example.dao;

import org.example.model.BankAccount;
import org.example.model.Customer;
import org.example.model.Operation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDAO extends BaseDAO<BankAccount> {
    //Properties
    CustomerDAO customerDAO = new CustomerDAO(connection);

    //Constructor
    public BankAccountDAO(Connection connection) {
        super(connection);
    }

    //Methods
    /**
     * Creates a new BankAccount in the database.
     *
     * @param bankAccount the BankAccount object to be created
     * @return true if the creation was successful, false otherwise
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean create(BankAccount bankAccount) throws SQLException {
        request = "INSERT INTO bankaccount (id_account, id_customer) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, bankAccount.getIdAccount());
        preparedStatement.setInt(2, bankAccount.getCustomer().getIdCustomer());
        int nbRow = preparedStatement.executeUpdate();
        resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            bankAccount.setIdAccount(resultSet.getInt(1));
        }
        return nbRow == 1;
    }

    /**
     * Updates an existing BankAccount in the database.
     *
     * @param bankAccount the BankAccount object containing updated information
     * @return true if the update was successful, false otherwise
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean update(BankAccount bankAccount) throws SQLException {
        request = "UPDATE bankaccount SET id_customer = ? WHERE id_account = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setInt(1, bankAccount.getCustomer().getIdCustomer());
        preparedStatement.setInt(2, bankAccount.getIdAccount());
        int nbRow = preparedStatement.executeUpdate();
        return nbRow == 1;
    }

    /**
     * Deletes a BankAccount from the database.
     *
     * @param bankAccount the BankAccount object to delete
     * @return true if the deletion was successful, false otherwise
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean delete(BankAccount bankAccount) throws SQLException {
        request = "DELETE FROM bankaccount WHERE id_customer = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setInt(1, bankAccount.getIdAccount());
        int nbRow = preparedStatement.executeUpdate();
        return nbRow == 1;
    }

    /**
     * Retrieves a BankAccount from the database by its ID.
     *
     * @param id the unique identifier of the BankAccount
     * @return the BankAccount object if found, or null if not found
     * @throws SQLException if a database access error occurs
     */
    @Override
    public BankAccount get(int id) throws SQLException {
        BankAccount bankAccount = null;
        request = "SELECT * FROM bankaccount WHERE id_account = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            bankAccount = new BankAccount(
                    resultSet.getInt("id_account"),
                    customerDAO.get(resultSet.getInt("id_customer")),
                    // TODO: get operations of the customer
                    resultSet.getDouble("amount")
            );
        }
        return bankAccount;
    }

    /**
     * Retrieves all BankAccount entities from the database.
     *
     * @return a List of all BankAccount objects
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<BankAccount> getAll() throws SQLException {
        List<BankAccount> bankAccounts = new ArrayList<>();
        request = "SELECT * FROM bankaccount";
        preparedStatement = connection.prepareStatement(request);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            BankAccount bankAccount = new BankAccount(
                    resultSet.getInt("id_account"),
                    customerDAO.get(resultSet.getInt("id_customer")),
                    // TODO: get operations of the customer
                    resultSet.getDouble("amount")
            );
            bankAccounts.add(bankAccount);
        }
        return bankAccounts;
    }
}
