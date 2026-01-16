package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PageCheckout extends Page{
    int total;
    int wantToCheckOut;
    @Override
    protected void display() throws SQLException {
        //Calculate total in cart
        Statement getTotal = connection.createStatement();
        ResultSet prices = getTotal.executeQuery("SELECT Carts.Amount, Products.Price FROM Carts" +
                " INNER JOIN Products ON Carts.ProductID = Products.ID" +
                " WHERE Carts.CustomerID =" + Main.currentCustomerID
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
        return null;
    }

    @Override
    protected void act() throws SQLException {
        wantToCheckOut = decision();
        switch(wantToCheckOut) {
            case 1: {
                //Empty customer cart
                Statement emptyCart = connection.createStatement();
                emptyCart.executeUpdate("DELETE FROM Carts WHERE CustomerID =" + Main.currentCustomerID);

                //Take Money
                Statement takeMoney = connection.createStatement();
                takeMoney.executeUpdate("UPDATE Customers SET Balance = Balance - " + total + " WHERE ID =" + Main.currentCustomerID);
            }
        }
    }
    private int decision() {
        return Utils.reader(0,1);
    }
}
