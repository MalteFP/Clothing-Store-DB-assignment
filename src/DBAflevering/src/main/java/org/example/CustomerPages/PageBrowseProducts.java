package org.example.CustomerPages;

import org.example.Main;
import org.example.Page;
import org.example.Utils;

import java.sql.SQLException;

public class PageBrowseProducts extends Page {
    @Override
    protected void display() throws SQLException {
        System.out.println("0: Back");
        for (int i = 0; i < Main.productList.size(); i++) {
            System.out.println(i + 1 + ": " + Main.productList.get(i).type() + ": " + Main.productList.get(i).itemName() + ": " + Main.productList.get(i).amount() + " in stock: " + Main.productList.get(i).price() + ",-");
        }

        System.out.println("What product would you like to buy? ");
    }

    @Override
    protected Page nextPage() {
        int decision = decision();
        if (decision == 0) {
            return new PageMainMenuCustomer().init(connection);
        }
        return new PageQtyToBuy(decision - 1).init(connection);
    }

    @Override
    protected void act() {

    }

    private int decision() {
        return Utils.reader(0,Main.productList.size());
    }
}
