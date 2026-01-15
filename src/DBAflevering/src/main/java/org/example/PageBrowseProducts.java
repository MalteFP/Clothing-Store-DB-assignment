package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PageBrowseProducts extends Page {
    int products = 0;
    @Override
    protected void display() throws SQLException {

        Statement product = connection.createStatement();
        ResultSet productSet = product.executeQuery("SELECT * FROM Products");


        System.out.println("0. Back");
        while (productSet.next()) {
            products++;
            int ID = productSet.getInt("ID");
            String type = productSet.getString("Type");
            String name = productSet.getString("ItemName");
            int qty = productSet.getInt("Amount");
            int price = productSet.getInt("Price");

            System.out.println(ID + ": " + type + ": " + name + ": " + qty + " in stock: " + price + ",-");
        }


        System.out.println("What product would you like to buy? ");
    }

    @Override
    protected Page nextPage() {
        int decision = decision();
        if (decision == 0) {
            return new PageMainMenuCustomer().init(connection);
        }
        return new PageQtyToBuy(decision).init(connection);
    }

    @Override
    protected void act() {

    }

    private int decision() {
        return Utils.reader(0,products);
    }
}
