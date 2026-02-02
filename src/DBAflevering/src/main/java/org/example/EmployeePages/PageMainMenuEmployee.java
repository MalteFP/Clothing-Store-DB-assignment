package org.example.EmployeePages;

import org.example.*;

import java.sql.SQLException;

public class PageMainMenuEmployee extends Page {
    @Override
    protected void display() throws SQLException {
        //Employee action options
        System.out.println("Welcome to the main menu employee page");
        System.out.println("1: Add new product to shelf");
        System.out.println("2: Remove a product from shelf");
        System.out.println("3: Add to stock of existing item");
        System.out.println("4: Remove a customer");
        System.out.println("5: Log Out");
        System.out.printf("%nWhat would you like to do?%n");
    }

    @Override
    protected Page nextPage() {
        //Choose between employee action options
        switch (decision()) {
            case 1: return new PageAddNewProduct().init(connection);
            case 2: return new PageRemoveProduct().init(connection);
            case 3: return new PageAddStock().init(connection);
            case 4: return new PageRemoveCustomer().init(connection);
            case 5: return new PageLogin().init(connection);
        }
        return null;
    }

    @Override
    protected void act() throws SQLException {

    }

    private int decision() {
        return Utils.reader(1,6);
    }
}
