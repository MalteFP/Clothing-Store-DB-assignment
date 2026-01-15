package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainMenuActions {
    static void browseProducts() throws SQLException {
        DBConnect db = new DBConnect();
        Connection connection = db.getLocalConnection();

        Statement product = connection.createStatement();
        ResultSet productSet = product.executeQuery("SELECT * FROM Products");
        int amountOfProducts = 0;
        System.out.println("0. Back");
        while (productSet.next()) {
            amountOfProducts++;
            int ID = productSet.getInt("ID");
            String type = productSet.getString("Type");
            String name = productSet.getString("ItemName");
            int amount = productSet.getInt("Amount");
            int price = productSet.getInt("Price");

            System.out.println(ID + ": " + type + ": " + name + ": " + amount + " in stock: " + price + ",-");
        }

        System.out.println("What product would you like to buy? ");
        int ProductToAdd = Utils.reader(0,amountOfProducts);


        if (ProductToAdd == 0) {
           // Actions.mainMenu();
        } else {
            Statement getStock = connection.createStatement();
            ResultSet stock = getStock.executeQuery("SELECT Amount FROM Products WHERE ID =" + ProductToAdd);
            System.out.println("How many would you like?");
            int quantityOnStock = stock.getInt("Amount");
            int amountToAdd = Utils.reader(0, quantityOnStock);

            //Add product to customers cart
            Statement AddToCart = connection.createStatement();
            AddToCart.executeUpdate("INSERT INTO Carts (CustomerID, ProductID, Amount) VALUES (" + Main.currentCustomerID + ", " + ProductToAdd + ", " + amountToAdd + ")") ;

            //Remove product from stock
            Statement RemoveFromStock  = connection.createStatement();
            RemoveFromStock.executeUpdate("UPDATE Products SET Amount = Amount - " + amountToAdd + " WHERE ID =" + ProductToAdd);

            System.out.println("Product has been added from your cart");
            db.closeLocalConnection();
        }



    }
    static void viewCart() throws SQLException {
        DBConnect db = new DBConnect();
        Connection connection = db.getLocalConnection();

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
        System.out.println("Type -1 with you want to go back");
        int itemToCancelID = Utils.reader(-1,amountOfItemsInCart);

        if (itemToCancelID != -1) {
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

        //Calculate total in cart
        Statement getTotal = connection.createStatement();
        ResultSet prices = getCart.executeQuery("SELECT Carts.Amount, Products.Price FROM Carts" +
                                                " INNER JOIN Products ON Carts.ProductID = Products.ID" +
                                                " WHERE Carts.CustomerID =" + Main.currentCustomerID
        );
        int total = 0;
        while(prices.next()) {
            total += prices.getInt("Amount") * prices.getInt("Price");
        }

        System.out.println("Do you want to checkout, your total is " + total+",-");
        System.out.println("0. No");
        System.out.println("1. Yes");

        if(Utils.reader(0,1) == 1) {

            //Empty customer cart
            Statement emptyCart = connection.createStatement();
            emptyCart.executeUpdate("DELETE FROM Carts WHERE CustomerID =" + Main.currentCustomerID);

            //Take Money
            Statement takeMoney = connection.createStatement();
            takeMoney.executeUpdate("UPDATE Customers SET Balance = Balance - " + total + " WHERE ID =" + Main.currentCustomerID);
        }

        db.closeLocalConnection();
    }



    static void Exit() {

        System.exit(0);
    }
}
