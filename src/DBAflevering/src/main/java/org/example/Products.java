package org.example;

public class Products {
    private int ID;
    private String type;
    private String itemName;
    private int amount;
    private int price;

    public Products(int ID, String type, String itemName, int amount, int price) {
        this.ID = ID;
        this.type = type;
        this.itemName = itemName;
        this.amount = amount;
        this.price = price;
    }
}
