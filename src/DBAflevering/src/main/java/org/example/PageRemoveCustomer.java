package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PageRemoveCustomer extends Page{
    int users = 0;
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
        return new PageMainMenuEmployee().init(connection);
    }

    @Override
    protected void act() throws SQLException {
        int decision = decision();
        if(decision!=0){
            Statement removeCustomer = connection.createStatement();
            removeCustomer.executeUpdate("DELETE FROM Customers WHERE ID = " + customerIDs.get(decision - 1));
        }
    }
    private int decision() {
        return Utils.reader(0,users);
    }


}
