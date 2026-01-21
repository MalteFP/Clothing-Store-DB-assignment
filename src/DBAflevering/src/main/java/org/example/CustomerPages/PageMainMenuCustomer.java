package org.example.CustomerPages;

import org.example.*;

import java.sql.*;

public class PageMainMenuCustomer extends Page {
    @Override
    protected void display() throws SQLException {

        System.out.println("Welcome to the TMM, " + Main.currentCustomer.fullName());

        System.out.println("1. Browse products");
        System.out.println("2. View cart");
        System.out.println("3. Profile");
        System.out.println("4. Exit");
    }

    @Override
    protected Page nextPage() {
        switch (decision()) {
            case 1: return new PageBrowseProducts().init(connection);
            case 2: return  new PageViewCart().init(connection);
            case 3: return  new PageProfile().init(connection);
        }
        return null;
    }

    @Override
    protected void act() throws SQLException {

    }


    private int decision() {
        return Utils.reader(1,4);
    }
}
