package org.example.EmployeePages;

import org.example.CustomerPages.PageAddNewCustomer;
import org.example.CustomerPages.PageMainMenuCustomer;
import org.example.LoadData;
import org.example.Main;
import org.example.Page;
import org.example.PageLogin;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;




public class PageAddNewProduct extends Page {

    boolean back = false;
    boolean correctFormat = true;

    @Override
    protected void display() throws SQLException {
        System.out.println("0: Back");
        //Formatting for split
        System.out.println("Please enter the product description in the following format: ");
        System.out.println("Type, Name, Stock, Price");
    }

    @Override
    protected Page nextPage() {
        if (back) {
            return new PageMainMenuEmployee().init(connection);
        } else if (correctFormat) {
            return new PageMainMenuEmployee().init(connection);
        } else {
            return new PageAddNewProduct().init(connection);
        }
    }

    @Override
    protected void act() throws SQLException {
        String[] columns = decision().split(", ");
        try {
            Integer.parseInt(columns[3]);
            Integer.parseInt(columns[2]);
        }catch (Exception _) {
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
        //Adds item to stock
        if (correctFormat) {
            Statement addItem = connection.createStatement();
            addItem.executeUpdate("INSERT INTO Products (Type, ItemName, Amount, Price) VALUES ('" + columns[0] + "', '" + columns[1] + "', " + columns[2] + ", " + columns[3] + ")");
            Main.productList = LoadData.loadProducts();
        }
    }

    private String decision() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
