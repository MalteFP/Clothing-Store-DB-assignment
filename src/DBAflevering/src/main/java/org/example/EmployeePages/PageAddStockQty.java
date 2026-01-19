package org.example.EmployeePages;

import org.example.Page;
import org.example.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PageAddStockQty extends Page {

    private int productToAddStock;

    public PageAddStockQty(int productToAddStock) {
        this.productToAddStock = productToAddStock;
    }

    @Override
    protected void display() throws SQLException {
        System.out.println("How many would you like to add?");
    }

    @Override
    protected Page nextPage() {
        return new PageMainMenuEmployee().init(connection);
    }

    @Override
    protected void act() throws SQLException {
        Statement addStock = connection.createStatement();
        addStock.executeUpdate("UPDATE products SET Amount = Amount + " + decision() + " WHERE ID = " + productToAddStock);
        System.out.println("Updated product to add stock");
    }

    private int decision() throws SQLException {
        Statement getCurrentStock = connection.createStatement();
        ResultSet currentStock =  getCurrentStock.executeQuery("SELECT Amount FROM products WHERE ID = "+ this.productToAddStock);
        return Utils.reader(-currentStock.getInt("Amount"),10000);
    }
}
