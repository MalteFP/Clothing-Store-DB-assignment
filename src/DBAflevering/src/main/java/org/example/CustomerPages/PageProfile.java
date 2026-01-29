package org.example.CustomerPages;

import org.example.*;
import org.example.PageLogin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PageProfile extends Page {
    @Override
    protected void display() throws SQLException {
        System.out.printf("%nName: " + Main.currentCustomer.fullName() + "%n");
        System.out.println("Address: " + Main.currentCustomer.address());
        System.out.println("Zip: " + Main.currentCustomer.zipCode());
        System.out.println("City: " + Main.currentCustomer.city());
        System.out.println("Balance: " + Main.currentCustomer.balance());

        System.out.printf("%nWhat would you like to do?%n");
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
