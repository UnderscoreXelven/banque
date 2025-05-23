package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {
    //Properties
    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected String request;
    protected ResultSet resultSet;

    //Constructor
    protected BaseDAO(Connection connection){
        this.connection = connection;
    }

    //Methods
    public abstract boolean create(T element) throws SQLException;
    public abstract boolean update(T element) throws SQLException;
    public abstract boolean delete(T element) throws SQLException;
    public abstract T get(int id) throws SQLException;
    public abstract List<T> getAll() throws SQLException;
}
