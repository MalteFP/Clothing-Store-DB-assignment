package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private Connection connection;

    public DBConnect() {
        String url = "jdbc:sqlite:Database.db";
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Database connection could not be established");
            throw new RuntimeException(e);
        }
    }

    public Connection getLocalConnection() {
        return connection;
    }

    public void closeLocalConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Database connection could not be closed");
                throw new RuntimeException(e);
            }
        }

    }

}
