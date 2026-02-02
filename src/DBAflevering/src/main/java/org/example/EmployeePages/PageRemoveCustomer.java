package org.example.EmployeePages;

import org.example.LoadData;
import org.example.Main;
import org.example.Page;
import org.example.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PageRemoveCustomer extends Page {

    @Override
    protected void display() throws SQLException {



        System.out.printf("%n0: Back%n");
        for (int i = 0; i < Main.customerList.size(); i++) {
            System.out.println(i + 1 + ": " + Main.customerList.get(i).fullName());
        }
        System.out.printf("%nWhich customer would you like  to remove?");

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
            removeCustomer.executeUpdate("DELETE FROM Customers WHERE ID = " + Main.customerList.get(decision - 1).ID());

            Main.customerList = LoadData.loadCustomers();

        }
    }
    private int decision() {
        return Utils.reader(0,Main.customerList.size());
    }


}
