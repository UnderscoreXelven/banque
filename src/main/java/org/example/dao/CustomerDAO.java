package org.example.dao;

import org.example.model.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends BaseDAO<Customer> {
    // Constructor
    public CustomerDAO(Connection connection) {
        super(connection);
    }

    // Methods
    /**
     * Create a new customer in the database.
     *
     * @param customer the Customer object to save in the database
     * @return true if the customer was successfully saved, false otherwise
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean create(Customer customer) throws SQLException {
        request = "INSERT INTO customer (last_name, first_name, phone) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, customer.getLastName());
        preparedStatement.setString(2, customer.getFirstName());
        preparedStatement.setString(3, customer.getPhone());
        int nbRow = preparedStatement.executeUpdate();
        resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            customer.setIdCustomer(resultSet.getInt(1));
        }
        return nbRow == 1;
    }

    /**
     * Update an existing customer's data in the database.
     *
     * @param customer the Customer object containing updated information
     * @return true if the customer was successfully updated, false otherwise
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean update(Customer customer) throws SQLException {
        request = "UPDATE customer SET last_name = ?, first_name = ?, phone = ? WHERE id_customer = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setString(1, customer.getLastName());
        preparedStatement.setString(2, customer.getFirstName());
        preparedStatement.setString(3, customer.getPhone());
        preparedStatement.setInt(4, customer.getIdCustomer());
        int nbRow = preparedStatement.executeUpdate();
        return nbRow == 1;
    }

    /**
     * Delete a customer from the database.
     *
     * @param customer the Customer object to delete
     * @return true if the customer was successfully deleted, false otherwise
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean delete(Customer customer) throws SQLException {
        request = "DELETE FROM customer WHERE id_customer = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setInt(1, customer.getIdCustomer());
        int nbRow = preparedStatement.executeUpdate();
        return nbRow == 1;
    }

    /**
     * Retrieve a customer from the database by their ID.
     *
     * @param id the ID of the customer to retrieve
     * @return the Customer object if found, or null if not found
     * @throws SQLException if a database access error occurs
     */
    @Override
    public Customer get(int id) throws SQLException {
        Customer customer = null;
        request = "SELECT * FROM customer WHERE id_customer = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            customer = new Customer(
                    resultSet.getInt("id_customer"),
                    resultSet.getString("last_name"),
                    resultSet.getString("first_name"),
                    resultSet.getString("phone")
            );
        }
        return customer;
    }

    /**
     * Retrieve all customers from the database.
     *
     * @return a List of all Customer objects in the database
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        request = "SELECT * FROM customer";
        preparedStatement = connection.prepareStatement(request);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Customer customer = new Customer(
                    resultSet.getInt("id_customer"),
                    resultSet.getString("last_name"),
                    resultSet.getString("first_name"),
                    resultSet.getString("phone")
            );
            customers.add(customer);
        }
        return customers;
    }
}
