package org.example.CustomerPages;

import org.example.Main;
import org.example.Page;
import org.example.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PageRemoveFromCart extends Page {
    int amountOfItemsInCart = 0;
    ArrayList<Integer> cartIDs = new ArrayList<Integer>();
    @Override
    protected void display() throws SQLException {
        Statement isCartEmpty = connection.createStatement();
        ResultSet customerCartContent =  isCartEmpty.executeQuery("SELECT CustomerID FROM Carts" +
                " WHERE CustomerID = " + Main.currentCustomerID);
        if (!customerCartContent.next()) {
            System.out.println("Your cart is empty");
            return;
        }


        Statement getCart = connection.createStatement();
        ResultSet cart = getCart.executeQuery("SELECT Carts.ID, Carts.Amount, Products.ItemName, Products.Price FROM Carts" +
                " INNER JOIN Products ON Carts.ProductID = Products.ID" +
                " WHERE Carts.CustomerID =" + Main.currentCustomerID
        );

        System.out.println("0. Back");
        while (cart.next()) {
            amountOfItemsInCart++;
            cartIDs.add(cart.getInt("ID"));
            String productName = cart.getString("ItemName");
            int price = cart.getInt("Price");
            int amount = cart.getInt("Amount");

            System.out.println(cartIDs.size() - 1 + ": " + productName + ": " + amount + " Price For Each: " + price + ",-");
        }
        System.out.println("What item would you like to remove?");
    }

    @Override
    protected Page nextPage() {
        return new PageViewCart().init(connection);
    }

    @Override
    protected void act() throws SQLException {
        int itemToCancelID = decision();
        Statement GetInfoOfCancel = connection.createStatement();
        ResultSet itemToCancel = GetInfoOfCancel.executeQuery("SELECT * FROM Carts WHERE ID =" + cartIDs.get(itemToCancelID));

        int ID = itemToCancel.getInt("ID");
        int productID = itemToCancel.getInt("ProductID");
        int amount = itemToCancel.getInt("Amount");


        //Add product to stock
        Statement AddToStock  = connection.createStatement();
        AddToStock.executeUpdate("UPDATE Products SET Amount = Amount + " + amount + " WHERE ID =" + productID);

        //Remove from cart
        Statement RemoveFromCart  = connection.createStatement();
        RemoveFromCart.executeUpdate("DELETE FROM Carts WHERE ID =" + ID);
        System.out.println("Product has been removed from your cart");
    }
    private int decision(){
        return Utils.reader(0,amountOfItemsInCart - 1);
    }

}
