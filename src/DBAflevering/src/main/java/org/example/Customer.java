package org.example;

public class Customer {
    private int ID;
    private String fullName;
    private String address;
    private int zipCode;
    private String city;
    private int balance;


    public Customer(int ID, String fullName, String address, int zipCode, String city, int balance) {
        this.ID = ID;
        this.fullName = fullName;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.balance = balance;
    }

    public int ID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String fullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String address() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int zipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String city() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int balance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
