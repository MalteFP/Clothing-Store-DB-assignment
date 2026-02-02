package org.example.CustomerPages;

import org.example.LoadData;
import org.example.Main;
import org.example.Page;
import org.example.PageLogin;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PageAddNewCustomer extends Page {

    boolean back = false;
    boolean correctFormat = true;

    @Override
    protected void display() throws SQLException {
        //Formatting for split
        System.out.printf("0: Back%n");
        System.out.println("Please enter the relevant customer information in the following format: ");
        System.out.println("FullName, Address, ZipCode, City, Balance");
    }

    @Override
    protected Page nextPage() {
        if (back) {
            return new PageLogin().init(connection);
        } else if (correctFormat) {
            return new PageMainMenuCustomer().init(connection);
        } else {
            return new PageAddNewCustomer().init(connection);
        }
    }

    @Override
    protected void act() throws SQLException {
        //Split for database
        String[] columns = decision().split(", ");
        try {
            Integer.parseInt(columns[2]);
            Integer.parseInt(columns[4]);
        } catch (Exception _) {
            try {
                correctFormat = false;
                if (Integer.parseInt(columns[0]) == 0 && columns.length == 1) {
                    back = true;
                } else {
                    System.out.println("Formatting error");
                }

            } catch (Exception _) {
                System.out.println("Formatting error");
            }
        }
        //Adds customer to database
        if (correctFormat) {
            Statement addCustomer = connection.createStatement();
            addCustomer.executeUpdate("INSERT INTO Customers (FullName, Address, ZipCode, City, Balance) VALUES ('" + columns[0] + "', '" + columns[1] + "', '" + columns[2] + "', '" + columns[3] + "', " + columns[4] + ")");
            Main.customerList = LoadData.loadCustomers();
            Main.currentCustomer = Main.customerList.getLast();
        }
    }

    private String decision() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}