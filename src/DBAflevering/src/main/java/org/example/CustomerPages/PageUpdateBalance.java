package org.example.CustomerPages;

import org.example.Page;
import org.example.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PageUpdateBalance extends Page {

    @Override
    protected void display() throws SQLException {
        Statement getProfile = connection.createStatement();
        ResultSet profile = getProfile.executeQuery("SELECT * FROM Customers");
        System.out.println("Balance: " + profile.getString("Balance"));
        System.out.println("Do you want to add/withdraw from your balance?");

    }

    @Override
    protected Page nextPage() {
        return new PageProfile().init(connection);
    }

    @Override
    protected void act() throws SQLException {
        Statement addToBalance = connection.createStatement();
        addToBalance.executeUpdate("UPDATE Customers SET Balance = Balance + " + decision());
    }

    private int decision() {
        return Utils.reader(-10000, 10000);
    }
}
