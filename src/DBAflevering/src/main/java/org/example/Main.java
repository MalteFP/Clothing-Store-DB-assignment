package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static Customer currentCustomer = null;
    public static ArrayList<Customer> customerList = null;
    public static ArrayList<Product> productList = null;
    public static ArrayList<Store> storeList = null;

    public static void main(String[] args) throws SQLException {
        DBConnect db = new DBConnect();
        Connection connection = db.getLocalConnection();


        new LoadData().init(connection);
        productList = LoadData.loadProducts();
        customerList = LoadData.loadCustomers();
        storeList = LoadData.loadStores();



        Page page = new PageLogin();
        page.init(connection);

        while (page != null) {
            page = page.run();
        }


        System.out.println("Bye!");
        db.closeLocalConnection();


    }
}