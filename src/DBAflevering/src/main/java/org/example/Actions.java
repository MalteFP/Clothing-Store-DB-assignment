package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Actions {

    
    //Lets the user choose between logging in as a customer and an employee
    static void startUp() throws SQLException {

        Page p = new PageLogin();
        UserType usertype = (UserType) p.run();

        switch (usertype) {
            case Customer:
                Actions.loginCustomer();
                break;
        }
    }

    //Runs mainmenuactions based on userchoice
    static void MainMenuActionTypes(int MainMenuActionType) throws SQLException {
        switch (MainMenuActionType) {
            case 1: MainMenuActions.browseProducts();
            break;
            case 2: MainMenuActions.viewCart();
            break;
            case 3: MainMenuActions.Exit();
            break;
        }
    }

    //Lets the customer login to their personal account
    static void loginCustomer() throws SQLException {
        DBConnect db = new DBConnect();
        Connection connection = db.getLocalConnection();


        Statement allFromCustomers = connection.createStatement();
        ResultSet User = allFromCustomers.executeQuery("SELECT * FROM Customers");

        int amountOfUsers = 0;
        while (User.next()) {
            amountOfUsers++;
            int ID = User.getInt("ID");
            String Name = User.getString("FullName");
            System.out.println(Name + ": " + ID);
        }
        System.out.println("What is your ID?");
        db.closeLocalConnection();
        Main.currentCustomerID = Utils.reader(1, amountOfUsers);
    }

    //Welcomes customer to the website and presents the users their options
    static void mainMenu() throws SQLException {
        while(true) {
            DBConnect db = new DBConnect();
            Connection connection = db.getLocalConnection();
            Statement getName = connection.createStatement();
            String name = getName.executeQuery("SELECT FullName FROM Customers WHERE ID = " + Main.currentCustomerID).getString("FullName");
            db.closeLocalConnection();
            System.out.println("Welcome to the TMM, " + name);

            System.out.println("1. Browse products");
            System.out.println("2. View cart");
            System.out.println("3. Exit");
            MainMenuActionTypes(Utils.reader(1, 3));
        }
    }
}
