package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    private static ProductDaoJDBC instance = null;

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    public static ProductDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        String query = "INSERT INTO products (name, description, default_price, currency, product_category, supplier)"
                + "VALUES ('" + product.getName() + "', '" + product.getDescription() + "', '"
                + product.getDefaultPrice() + "', '" + product.getDefaultCurrency().getCurrencyCode() +"', '"
                + product.getProductCategory().getId() + "', '"
                + product.getSupplier().getId() + "');";
        System.out.println(query);
        executeQuery(query);
    }

    void setId(Product product) {
        String query = "SELECT * FROM products WHERE name ='" + product.getName() + "';";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet.next()) {
                product.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM products WHERE id ='" + id + "';";
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
//            Currency currency = Currency.getInstance();

            if (resultSet.next()){
                ProductCategoryDao productData = ProductCategoryDaoJDBC.getInstance();
                ProductCategory category = productData.find(resultSet.getInt("product_category"));

                SupplierDao supplierData = SupplierDaoJDBC.getInstance();
                Supplier supplier = supplierData.find(resultSet.getInt("supplier"));

                return new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        category,
                        supplier);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM products WHERE id = '" + id +"';";
        executeQuery(query);
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products;";
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
//                Currency currency = Currency.getInstance(resultSet.getString("currency"));

                ProductCategoryDao productData = ProductCategoryDaoJDBC.getInstance();
                ProductCategory category = productData.find(resultSet.getInt("product_category"));

                SupplierDao supplierData = SupplierDaoJDBC.getInstance();
                Supplier supplier = supplierData.find(resultSet.getInt("supplier"));

                products.add(new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        category,
                        supplier));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE supplier='" + supplier.getId() + "';";
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
//                Currency currency = Currency.getInstance(resultSet.getString("currency"));

                ProductCategoryDao productData = ProductCategoryDaoJDBC.getInstance();
                ProductCategory category = productData.find(resultSet.getInt("product_category"));

                products.add(new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        category,
                        supplier));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<Product> getBy(ProductCategory productCategory){
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE product_category='" + productCategory.getId() + "';";
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
//                Currency currency = Currency.getInstance(resultSet.getString("currency"));

                SupplierDao supplierData = SupplierDaoJDBC.getInstance();
                Supplier supplier = supplierData.find(resultSet.getInt("supplier"));

                products.add(new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        productCategory,
                        supplier));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }return null;
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
