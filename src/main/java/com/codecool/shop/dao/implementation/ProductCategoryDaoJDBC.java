package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by franka on 2016.11.29..
 */
public class ProductCategoryDaoJDBC implements ProductCategoryDao {
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static List<ProductCategory> DATA = new ArrayList<>();
    private static ProductCategoryDaoJDBC instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoJDBC() {
    }

    public static ProductCategoryDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        category.setId(DATA.size() + 1);
        String query = "INSERT INTO suppliers (id, name, description, department) " +
                "VALUES ('" + category.getId() + "', '" + category.getName() + "', '" +
                category.getDescription() +  "', '" + category.getDepartment() + "');";
        executeQuery(query);
    }

    @Override
    // still needs to be implemented
    public ProductCategory find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    // still needs to be implemented
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    // still needs to be implemented
    public List<ProductCategory> getAll() {
        return DATA;
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
