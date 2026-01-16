package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PageProfile extends Page {
    @Override
    protected void display() throws SQLException {
        Statement getProfile = connection.createStatement();
        ResultSet profile = getProfile.executeQuery("SELECT * FROM Customers");

        System.out.println("Name: " + profile.getString("FullName"));
        System.out.println("Adress: " + profile.getString("Adress"));
        System.out.println("Zip: " + profile.getString("ZipCode"));
        System.out.println("City: " + profile.getString("City"));
        System.out.println("Balance: " + profile.getString("Balance"));

        System.out.println("What would you like to do?");
        System.out.println("1. Sign out");
        System.out.println("2. Update Balance");
        System.out.println("3. Back to main menu");
    }

    @Override
    protected Page nextPage() {
        switch (decision()) {
            case 1: { Main.currentCustomerID = -1;
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
