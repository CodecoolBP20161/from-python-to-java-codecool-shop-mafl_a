package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by franka on 2016.11.29..
 */
public class SupplierDaoJDBC implements SupplierDao {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    private static List<Supplier> DATA = new ArrayList<>();


    @Override
    public void add(Supplier supplier) {
        supplier.setId(DATA.size() + 1);
        String query = "INSERT INTO suppliers (id, name, description) " +
                "VALUES ('" + supplier.getId() + "', '" + supplier.getName() + "', '" + supplier.getDescription() + "');";
        executeQuery(query);
    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
