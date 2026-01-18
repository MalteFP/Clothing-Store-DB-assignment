package org.example;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.sql.*;

public class PageMainMenuCustomer extends Page {
    @Override
    protected void display() throws SQLException {
        PreparedStatement getName = connection.prepareStatement("SELECT FullName FROM Customers WHERE ID = ?");
        getName.setInt(1, Main.currentCustomerID);
        ResultSet rs = getName.executeQuery();

        String name = "";

        if (rs.next()) {
            name = rs.getString("FullName");
        }

        System.out.println("Welcome to the TMM, " + name);

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
