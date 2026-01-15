package org.example;

public class PageLogin extends Page{

    @Override
    protected void display() {
        System.out.println("Do you want to login as");
        System.out.println("1 Customer");
        System.out.println("2 Employee");
    }

    @Override
    protected Object decision() {
        switch (Utils.reader(1,2)) {
            case 1: return UserType.Customer;
            case 2: return UserType.Employee;
        }
        return null;
    }


}
