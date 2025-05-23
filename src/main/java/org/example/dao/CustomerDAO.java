package org.example.dao;

import org.example.model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends BaseDAO<Customer>{
    //Constructor
    public CustomerDAO(Connection connection) {
        super(connection);
    }

    //Methods
    /**
     * Create a customer in database
     * @param customer
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean create(Customer customer) throws SQLException{
        request = "INSERT INTO customer (last_name,first_name,phone) values (?,?,?)";
        preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, customer.getLastName());
        preparedStatement.setString(2, customer.getFirstName());
        preparedStatement.setString(3, customer.getPhone());
        int nbRow = preparedStatement.executeUpdate();
        resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()){
            customer.setIdCustomer(resultSet.getInt(1));
        }
        return nbRow == 1;
    }

    /**
     * Update a customer datas within database
     * @param customer
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean update(Customer customer) throws SQLException{
        request = "UPDATE Customer SET last_name = ?, first_name = ?, phone = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setString(1, customer.getLastName());
        preparedStatement.setString(2, customer.getFirstName());
        preparedStatement.setString(3, customer.getPhone());
        int nbRow = preparedStatement.executeUpdate();
        return nbRow == 1;
    }

    /**
     * Delete a customer from database
     * @param customer
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean delete(Customer customer) throws SQLException{
        request = "UPDATE Customer SET last_name = ?, first_name = ?, phone = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setString(1, customer.getLastName());
        preparedStatement.setString(2, customer.getFirstName());
        preparedStatement.setString(3, customer.getPhone());
        int nbRow = preparedStatement.executeUpdate();
        return nbRow == 1;
    }

    /**
     * Get a customer from database
     * @param id
     * @return Customer
     * @throws SQLException
     */
    @Override
    public Customer get(int id) throws SQLException{
        Customer customer = null;
        request = "SELECT * FROM Customer WHERE id_customer = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            customer = new Customer(
                    resultSet.getInt("idCustomer"),
                    resultSet.getString("last_name"),
                    resultSet.getString("first_name"),
                    resultSet.getString("phone")
            );
        }
        return customer;
    }

    /**
     * Get all customers from database
     * @return List(Customer)
     * @throws SQLException
     */
    @Override
    public List<Customer> getAll() throws SQLException{
        List<Customer> customers = new ArrayList<>();
        request = "SELECT * FROM Customer";
        preparedStatement = connection.prepareStatement(request);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
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
