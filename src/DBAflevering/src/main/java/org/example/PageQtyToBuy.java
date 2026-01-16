package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PageQtyToBuy extends Page {

    private int productToBuy;
    private int quantityOnStock;

    public PageQtyToBuy(int productToBuy) {
        this.productToBuy = productToBuy;
    }

    @Override
    protected void display() throws SQLException {

        Statement getStock = connection.createStatement();
        ResultSet stock = getStock.executeQuery("SELECT Amount FROM Products WHERE ID =" + productToBuy);
        quantityOnStock = stock.getInt("Amount");

        System.out.println("How many would you like?");

    }

    @Override
    protected Page nextPage() {
        return new PageMainMenuCustomer().init(connection);
    }

    @Override
    protected void act() throws SQLException {
        int quantityToAdd = decision();

        Statement AddToCart = connection.createStatement();
        AddToCart.executeUpdate("INSERT INTO Carts (CustomerID, ProductID, Amount) VALUES (" + Main.currentCustomerID + ", " + productToBuy + ", " + quantityToAdd + ")") ;

        //Remove product from stock
        Statement RemoveFromStock  = connection.createStatement();
        RemoveFromStock.executeUpdate("UPDATE Products SET Amount = Amount + " + quantityToAdd + " WHERE ID =" + productToBuy);

        System.out.println("Product has been added from your cart");

    }


    private int decision() {
        return Utils.reader(1,quantityOnStock);
    }
}
