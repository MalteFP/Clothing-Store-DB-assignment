package org.example;

import java.sql.SQLException;

public class PageViewStores extends Page{
    @Override
    protected void display() throws SQLException {
        for (int i = 0; i < Main.storeList.size(); i++) {
            System.out.printf("Address: " + Main.storeList.get(i).address() + " ZipCode: " + Main.storeList.get(i).zipCode() + " City: " + Main.storeList.get(i).city());
        }
        System.out.printf("%n");

    }

    @Override
    protected Page nextPage() {
        return new PageLogin().init(connection);
    }

    @Override
    protected void act() throws SQLException {

    }
}
