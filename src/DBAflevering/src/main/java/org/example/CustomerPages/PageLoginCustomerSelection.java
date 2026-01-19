package org.example.CustomerPages;

import org.example.*;
import org.example.PageLogin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PageLoginCustomerSelection extends Page {

    private int decision;
    private int users = 0;
    ArrayList customerIDs = new ArrayList();
    @Override
    protected void display() throws SQLException {
        Statement allFromCustomers = connection.createStatement();
        ResultSet User = allFromCustomers.executeQuery("SELECT * FROM Customers");

        System.out.println("0. Back");
        while (User.next()) {
            users++;
            int ID = User.getInt("ID");
            customerIDs.add(ID);
            String Name = User.getString("FullName");
            System.out.println(Name + ": " + users);
        }

    }

    @Override
    protected Page nextPage() {
        switch (decision)   {
            case 0: return new PageLogin().init(connection);
        }
        return new PageMainMenuCustomer().init(connection);
    }

    @Override
    protected void act() {
        decision = decision();
        Main.currentCustomerID = decision;
    }


    private int decision() {
        return Utils.reader(0,users);
    }
}
