package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LoadData {

    protected static Connection connection;

    public LoadData init(Connection connection) {
        LoadData.connection = connection;
        return this;
    }


    public static ArrayList<Product> loadProducts() throws SQLException {
        ArrayList<Product> productList = new ArrayList<>();
        Statement getProducts = connection.createStatement();
        ResultSet products = getProducts.executeQuery("SELECT * FROM products");

        while (products.next()) {
            productList.add(new Product(products.getInt("ID"),products.getString("Type"),products.getString("ItemName"), products.getInt("Amount"), products.getInt("Price")));
        }
        return productList;
    }


    public static ArrayList<Customer> loadCustomers() throws SQLException {
        ArrayList<Customer> customerList = new ArrayList<>();
        Statement getCustomers = connection.createStatement();
        ResultSet customers = getCustomers.executeQuery("SELECT * FROM Customers");

        while (customers.next()) {
            customerList.add(new Customer(customers.getInt("ID"),customers.getString("FullName"),customers.getString("Address"), customers.getInt("ZipCode"), customers.getString("City"),  customers.getInt("Balance")));
        }
        return customerList;
    }


    public static ArrayList<Store> loadStores() throws SQLException {
        ArrayList<Store> loadStores = new ArrayList<>();
        Statement getStores = connection.createStatement();
        ResultSet stores = getStores.executeQuery("SELECT * FROM Stores");

        while (stores.next()) {
            loadStores.add(new Store(stores.getInt("ID"),stores.getString("Address"),stores.getInt("ZipCode"), stores.getString("City")));
        }
        return loadStores;
    }
}
