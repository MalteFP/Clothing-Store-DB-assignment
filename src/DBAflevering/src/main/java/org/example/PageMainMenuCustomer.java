package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PageMainMenuCustomer extends Page {
    @Override
    protected void display() throws SQLException {
        Statement getName = connection.createStatement();
        String name = getName.executeQuery("SELECT FullName FROM Customers WHERE ID = " + Main.currentCustomerID).getString("FullName");
        System.out.println("Welcome to the TMM, " + name);

        System.out.println("1. Browse products");
        System.out.println("2. View cart");
        System.out.println("3. Exit");
    }

    @Override
    protected Page nextPage() {
        switch (decision()) {
            case 1: return new PageBrowseProducts().init(connection);
            //  break;
            //case 2: MainMenuActions.viewCart();
        }
        return null;
    }

    @Override
    protected void act() throws SQLException {

    }


    private int decision() {
        return Utils.reader(1,3);
    }
}
