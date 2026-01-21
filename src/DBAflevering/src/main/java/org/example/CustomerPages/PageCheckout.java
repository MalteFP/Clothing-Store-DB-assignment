package org.example.CustomerPages;

import org.example.Main;
import org.example.Page;
import org.example.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PageCheckout extends Page {
    int total;
    int wantToCheckOut;
    @Override
    protected void display() throws SQLException {
        //Calculate total in cart
        Statement getTotal = connection.createStatement();
        ResultSet prices = getTotal.executeQuery("SELECT Carts.Amount, Products.Price FROM Carts" +
                " INNER JOIN Products ON Carts.ProductID = Products.ID" +
                " WHERE Carts.CustomerID =" + Main.currentCustomer.ID()
        );
        while(prices.next()) {
            total += prices.getInt("Amount") * prices.getInt("Price");
        }

        System.out.println("Do you want to checkout, your total is " + total+",-");
        System.out.println("1. Yes");
        System.out.println("2. No");
    }

    @Override
    protected Page nextPage() {
        return new PageMainMenuCustomer().init(connection);
    }

    @Override
    protected void act() throws SQLException {
        wantToCheckOut = decision();
        switch(wantToCheckOut) {
            case 1: {
                Statement getBalance = connection.createStatement();
                ResultSet balance = getBalance.executeQuery("SELECT Balance FROM Customers WHERE ID =" + Main.currentCustomer.ID());

                if (total <= balance.getInt("Balance")) {
                    //Empty customer cart
                    Statement emptyCart = connection.createStatement();
                    emptyCart.executeUpdate("DELETE FROM Carts WHERE CustomerID =" + Main.currentCustomer.ID());

                    //Take Money
                    Statement takeMoney = connection.createStatement();
                    takeMoney.executeUpdate("UPDATE Customers SET Balance = Balance - " + total + " WHERE ID =" + Main.currentCustomer.ID());

                    Main.currentCustomer.setBalance(Main.currentCustomer.balance() + total);

                } else {
                    System.out.println("You don't have enough balance to checkout");
                }

            }
        }
    }
    private int decision() {
        return Utils.reader(1,2);
    }
}
