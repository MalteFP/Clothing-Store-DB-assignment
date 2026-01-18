package org.example;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PageAddNewCustomer extends Page{
    @Override
    protected void display() throws SQLException {
        //Formatting for split
        System.out.println("Please enter the relevant customer information in the following format: ");
        System.out.println("FullName, Address, ZipCode, City, Balance");
    }

    @Override
    protected Page nextPage() {
        return new PageMainMenuEmployee().init(connection);
    }

    @Override
    protected void act() throws SQLException {
        //Split for database
        boolean correctFormat = true;
        String[] columns = decision().split(", "); //ChatGPT told us about split
        try {
            int tester = Integer.parseInt(columns[2]);
            tester = Integer.parseInt(columns[4]);
        }catch (Exception e){
            System.out.println("Formating error");
            correctFormat = false;
        }
        //Adds customer to database
        if(correctFormat){
            Statement addItem = connection.createStatement();
            addItem.executeUpdate(
                    "INSERT INTO Customers (FullName, Address, ZipCode, City, Balance) VALUES ('" + columns[0] + "', '" + columns[1] + "', '" + columns[2] + "', '" + columns[3] + "', " + columns[4] + ")"
            );

        }
    }

    private String decision() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
