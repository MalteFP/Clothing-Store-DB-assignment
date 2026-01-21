package org.example.CustomerPages;

import org.example.*;
import org.example.PageLogin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PageProfile extends Page {
    @Override
    protected void display() throws SQLException {
        System.out.println("Name: " + Main.currentCustomer.fullName());
        System.out.println("Address: " + Main.currentCustomer.address());
        System.out.println("Zip: " + Main.currentCustomer.zipCode());
        System.out.println("City: " + Main.currentCustomer.city());
        System.out.println("Balance: " + Main.currentCustomer.balance());

        System.out.println("What would you like to do?");
        System.out.println("1. Sign out");
        System.out.println("2. Update Balance");
        System.out.println("3. Back to main menu");
    }

    @Override
    protected Page nextPage() {
        switch (decision()) {
            case 1: { Main.currentCustomer = null;
                return new PageLogin().init(connection);
            }
            case 2: return new PageUpdateBalance().init(connection);

            case 3: return new PageMainMenuCustomer().init(connection);
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
