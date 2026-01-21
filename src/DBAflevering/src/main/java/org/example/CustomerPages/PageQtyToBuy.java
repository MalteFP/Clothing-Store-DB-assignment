package org.example.CustomerPages;

import org.example.Main;
import org.example.Page;
import org.example.Utils;

import java.sql.SQLException;
import java.sql.Statement;

public class PageQtyToBuy extends Page {

    private int productToBuy;

    public PageQtyToBuy(int productToBuy) {
        this.productToBuy = productToBuy;
    }

    @Override
    protected void display() throws SQLException {
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
        AddToCart.executeUpdate("INSERT INTO Carts (CustomerID, ProductID, Amount) VALUES (" + Main.currentCustomer.ID() + ", " + Main.productList.get(productToBuy).ID() + ", " + quantityToAdd + ")") ;

        //Remove product from stock
        Statement RemoveFromStock  = connection.createStatement();
        RemoveFromStock.executeUpdate("UPDATE Products SET Amount = Amount - " + quantityToAdd + " WHERE ID =" + Main.productList.get(productToBuy).ID());

        System.out.println("Product has been added from your cart");

    }


    private int decision() {
        return Utils.reader(1,Main.productList.get(productToBuy).amount());
    }
}
