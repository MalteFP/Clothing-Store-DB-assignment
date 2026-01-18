package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PageRemoveCustomer extends Page{
    int users = 0;

    @Override
    protected void display() throws SQLException {
        Statement allFromCustomers = connection.createStatement();
        ResultSet User = allFromCustomers.executeQuery("SELECT * FROM Customers");

        System.out.println("0. Back");
        while (User.next()) {
            users++;
            int ID = User.getInt("ID");
            String Name = User.getString("FullName");
            System.out.println(Name + ": " + ID);
        }

    }

    @Override
    protected Page nextPage() {
        return new PageMainMenuEmployee().init(connection);
    }

    @Override
    protected void act() throws SQLException {
        Statement removeCustomer = connection.createStatement();
        removeCustomer.executeUpdate("DELETE FROM Customers WHERE ID = " + decision());
    }
    private int decision() {
        return Utils.reader(0,users);
    }


}
