package org.example.EmployeePages;

import org.example.LoadData;
import org.example.Main;
import org.example.Page;
import org.example.Utils;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PageRemoveProduct extends Page {
    int products = 0;
    ArrayList productIDs = new ArrayList();

    @Override
    protected void display() throws SQLException {
        System.out.printf("%n0: Back%n");
        for (int i = 0; i < Main.productList.size(); i++) {
            System.out.println(i + 1 + ": " + Main.productList.get(i).type() + ": " + Main.productList.get(i).itemName() + ": " + Main.productList.get(i).amount() + " in stock: " + Main.productList.get(i).price() + ",-");
        }
        System.out.println("Which would you lke to remove?");
    }

    @Override
    protected Page nextPage() {
        return new PageMainMenuEmployee().init(connection);
    }

    @Override
    protected void act() throws SQLException {
        int decision = decision();
        if(decision != 0){
            Statement removeProduct = connection.createStatement();
            removeProduct.executeUpdate("DELETE FROM Products WHERE ID = " + Main.productList.get(decision - 1).ID());

            Statement removeProductFromCart = connection.createStatement();
            removeProductFromCart.executeUpdate("DELETE FROM Carts WHERE ProductID = " + Main.productList.get(decision - 1).ID());

            Main.productList = LoadData.loadProducts();

        }
    }
    private int decision() {
        return Utils.reader(0,Main.productList.size());
    }


}
