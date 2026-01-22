package org.example;

public class Store {
    private int ID;
    private String address;
    private int zipCode;
    private String city;

    public Store(int ID, String address, int zipCode, String city) {
        this.ID = ID;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
    }

    public int ID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
}
