package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PageLoginCustomerSelection extends Page {
    
    private int users = 0;

    @Override
    protected void display() throws SQLException {

        Statement allFromCustomers = connection.createStatement();
        ResultSet User = allFromCustomers.executeQuery("SELECT * FROM Customers");

        while (User.next()) {
            users++;
            int ID = User.getInt("ID");
            String Name = User.getString("FullName");
            System.out.println(Name + ": " + ID);
        }

    }

    @Override
    protected Page nextPage() {
        return new PageMainMenuCustomer().init(connection);
    }

    @Override
    protected void act() {
        Main.currentCustomerID = decision();
    }


    private int decision() {
        return Utils.reader(1,users);
    }
}
