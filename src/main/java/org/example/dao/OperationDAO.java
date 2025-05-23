package org.example.dao;

import org.example.model.Customer;
import org.example.model.Operation;
import org.example.model.OperationStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO extends BaseDAO<Operation> {
    //Constructor
    public OperationDAO(Connection connection) {
        super(connection);
    }

    //Methods
    /**
     * Creates a new Operation in the database.
     *
     * @param operation the Operation object to be created
     * @return true if the creation was successful, false otherwise
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean create(Operation operation) throws SQLException {
        request = "INSERT INTO operation (amount, status, id_account) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setDouble(1, operation.getAmount());
        preparedStatement.setString(2, operation.getStatus().name());
        preparedStatement.setInt(3, operation.getIdAccount());
        int nbRow = preparedStatement.executeUpdate();
        resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            operation.setIdOperation(resultSet.getInt(1));
        }
        return nbRow == 1;
    }

    /**
     * Updates an existing Operation in the database.
     *
     * @param operation the Operation object containing updated information
     * @return true if the update was successful, false otherwise
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean update(Operation operation) throws SQLException {
        request = "UPDATE operation SET amount = ?, status = ?, id_account = ? WHERE id_operation = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setDouble(1, operation.getAmount());
        preparedStatement.setString(2, operation.getStatus().name());
        preparedStatement.setInt(3, operation.getIdAccount());
        int nbRow = preparedStatement.executeUpdate();
        return nbRow == 1;
    }

    /**
     * Deletes an Operation from the database.
     *
     * @param operation the Operation object to delete
     * @return true if the deletion was successful, false otherwise
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean delete(Operation operation) throws SQLException {
        request = "DELETE FROM operation WHERE id_operation = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setInt(1, operation.getIdOperation());
        int nbRow = preparedStatement.executeUpdate();
        return nbRow == 1;
    }

    /**
     * Retrieves an Operation from the database by its ID.
     *
     * @param id the unique identifier of the Operation
     * @return the Operation object if found, or null if not found
     * @throws SQLException if a database access error occurs
     */
    @Override
    public Operation get(int id) throws SQLException {
        Operation operation = null;
        request = "SELECT * FROM Operation WHERE id_operation = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            operation = new Operation(
                    resultSet.getInt("id_operation"),
                    resultSet.getInt("id_account"),
                    OperationStatus.valueOf(resultSet.getString("status")),  // conversion en enum
                    resultSet.getDouble("amount")
            );
        }
        return operation;
    }

    /**
     * Retrieves all Operation entities from the database.
     *
     * @return a List of all Operation objects
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<Operation> getAll() throws SQLException {
        List<Operation> operations = new ArrayList<>();
        request = "SELECT * FROM operation";
        preparedStatement = connection.prepareStatement(request);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Operation operation = new Operation(
                    resultSet.getInt("id_operation"),
                    resultSet.getInt("id_account"),
                    OperationStatus.valueOf(resultSet.getString("status")),  // conversion en enum
                    resultSet.getDouble("amount")
            );
            operations.add(operation);
        }
        return operations;
    }
}