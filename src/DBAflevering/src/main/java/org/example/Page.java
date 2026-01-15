package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Page {
    protected Connection connection;

    public Page init(Connection connection) {
        this.connection = connection;
        return this;
    }

    protected abstract void display() throws SQLException;
    protected abstract Page nextPage();
    protected abstract void act() throws SQLException;

    public Page run() throws SQLException {
        display();
        act();
        return nextPage();
    }
}
