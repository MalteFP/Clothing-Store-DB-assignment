package org.example;

import java.sql.SQLException;

public class Main {
    public static int currentCustomerID = -1;

    public static void main(String[] args) throws SQLException {
        Actions.startUp();
        System.out.println(currentCustomerID);
        Actions.mainMenu();







    }
}