package org.example.CustomerPages;

import org.example.Main;
import org.example.Page;
import org.example.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PageUpdateBalance extends Page {

    @Override
    protected void display() throws SQLException {

        System.out.println("Balance: " + Main.currentCustomer.balance());
        System.out.println("Do you want to add/withdraw from your balance?");

    }

    @Override
    protected Page nextPage() {
        return new PageProfile().init(connection);
    }

    @Override
    protected void act() throws SQLException {
        int decision = decision();

        Statement addToBalance = connection.createStatement();
        addToBalance.executeUpdate("UPDATE Customers SET Balance = Balance + " + decision);

        Main.currentCustomer.setBalance(Main.currentCustomer.balance() + decision);
    }

    private int decision() {
        return Utils.reader(-10000, 10000);
    }
}
