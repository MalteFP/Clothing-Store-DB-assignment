package org.example.CustomerPages;

import org.example.*;
import org.example.PageLogin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PageLoginCustomerSelection extends Page {

    private int decision;
    @Override
    protected void display() throws SQLException {

        System.out.println("0. Back");
        for (int i = 0; i < Main.customerList.size(); i++) {
            System.out.println(i + 1 + ": " + Main.customerList.get(i).fullName());
        }


    }

    @Override
    protected Page nextPage() {
        switch (decision)   {
            case 0: return new PageLogin().init(connection);
        }
        return new PageMainMenuCustomer().init(connection);
    }

    @Override
    protected void act() throws SQLException {
        decision = decision();
        if (decision != 1) {
            Main.currentCustomer = Main.customerList.get(decision() - 1);
        }
    }


    private int decision() {
        return Utils.reader(0,Main.customerList.size());
    }
}
