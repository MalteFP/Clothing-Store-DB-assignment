package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static int currentCustomerID = -1;

    public static void main(String[] args) throws SQLException {
        DBConnect db = new DBConnect();
        Connection connection = db.getLocalConnection();

        Page page = new PageLogin();
        page.init(connection);

        while (page != null) {
            page = page.run();
        }

        System.out.println("Bye!");
        db.closeLocalConnection();


    }
}