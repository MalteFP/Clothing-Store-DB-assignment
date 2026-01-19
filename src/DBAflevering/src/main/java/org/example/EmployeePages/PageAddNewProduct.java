package org.example.EmployeePages;

import org.example.Page;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PageAddNewProduct extends Page {
    @Override
    protected void display() throws SQLException {
        //Formatting for split
        System.out.println("Please enter the product description in the following format: ");
        System.out.println("Type, Name, Stock, Price");
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
            int tester = Integer.parseInt(columns[3]);
            tester = Integer.parseInt(columns[2]);
        }catch (Exception e){
            System.out.println("Formating error");
            correctFormat = false;
        }
        //Adds item to stock
        if(correctFormat){
            Statement addItem = connection.createStatement();
            addItem.executeUpdate(
                    "INSERT INTO Products (Type, ItemName, Amount, Price) VALUES ('" + columns[0] + "', '" + columns[1] + "', " + columns[2] + ", " + columns[3] + ")"
            );

        }
    }

    private String decision() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
