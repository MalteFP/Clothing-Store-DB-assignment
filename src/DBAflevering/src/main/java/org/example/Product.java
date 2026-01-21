package org.example;

public class Product {
    private int ID;
    private String type;
    private String itemName;
    private int amount;
    private int price;

    public Product(int ID, String type, String itemName, int amount, int price) {
        this.ID = ID;
        this.type = type;
        this.itemName = itemName;
        this.amount = amount;
        this.price = price;
    }

    public int ID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String type() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String itemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int amount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int price() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

