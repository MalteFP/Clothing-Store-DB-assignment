package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PageViewCart extends Page {
    boolean cartIsEmpty;
    @Override
    protected void display() throws SQLException {
        Statement isCartEmpty = connection.createStatement();
        ResultSet customerCartContent =  isCartEmpty.executeQuery("SELECT CustomerID FROM Carts" + " WHERE CustomerID = " + Main.currentCustomerID);
        if (!customerCartContent.next()) {
            System.out.println("Your cart is empty");
            cartIsEmpty = true;
        } else {
            Statement getCart = connection.createStatement();
            ResultSet cart = getCart.executeQuery("SELECT Carts.ID, Carts.Amount, Products.ItemName, Products.Price FROM Carts" +
                    " INNER JOIN Products ON Carts.ProductID = Products.ID" +
                    " WHERE Carts.CustomerID =" + Main.currentCustomerID
            );
            int amountOfItemsInCart = 0;
            ArrayList<Integer> cartIDs = new ArrayList<Integer>();
            while (cart.next()) {
                amountOfItemsInCart++;
                cartIDs.add(cart.getInt("ID"));
                String productName = cart.getString("ItemName");
                int price = cart.getInt("Price");
                int amount = cart.getInt("Amount");

                System.out.println(cartIDs.size() - 1 + ": " + productName + ": " + amount + " Price For Each: " + price + ",-");
            }
            System.out.println("Would you like to remove anything from your cart?");
            System.out.println("1: Yes");
            System.out.println("2: No");
            System.out.println("3: Go to checkout");
        }
    }

    @Override
    protected Page nextPage() {
        if (cartIsEmpty) {
            return new PageMainMenuCustomer().init(connection);
        }
            switch(decision()) {
                case 1: return new PageRemoveFromCart().init(connection);
                case 2: return new PageMainMenuCustomer().init(connection);
                case 3: return new PageCheckout().init(connection);
            }
            return null;
    }

    @Override
    protected void act() throws SQLException {

    }

    private int decision() {
        return Utils.reader(1,3);
    }
}
