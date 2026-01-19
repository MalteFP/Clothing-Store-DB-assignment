package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PageRemoveProduct extends Page{
    int products = 0;
    ArrayList productIDs = new ArrayList();

    @Override
    protected void display() throws SQLException {
        Statement product = connection.createStatement();
        ResultSet productSet = product.executeQuery("SELECT * FROM Products");


        System.out.println("0. Back");
        while (productSet.next()) {
            products++;
            int ID = productSet.getInt("ID");
            productIDs.add(ID);
            String type = productSet.getString("Type");
            String name = productSet.getString("ItemName");
            int qty = productSet.getInt("Amount");
            int price = productSet.getInt("Price");

            System.out.println(ID + ": " + type + ": " + name + ": " + qty + " in stock: " + price + ",-");
        }
        System.out.println("Which would you lke to remove");
    }

    @Override
    protected Page nextPage() {
        return new PageMainMenuEmployee().init(connection);
    }

    @Override
    protected void act() throws SQLException {
        Statement removeProduct = connection.createStatement();
        removeProduct.executeUpdate("DELETE FROM Products WHERE ID = " + productIDs.get(decision()));
    }
    private int decision() {
        return Utils.reader(0,products - 1);
    }


}
