package org.example;

import org.example.CustomerPages.PageLoginCustomerSelection;
import org.example.EmployeePages.PageMainMenuEmployee;

public class PageLogin extends Page {

    @Override
    protected void display() {
        System.out.println("Do you want to login as");
        System.out.println("1 Customer");
        System.out.println("2 Sign-Up as customer");
        System.out.println("3 Employee");
        System.out.println("4 View stores");
    }

    @Override
    protected Page nextPage() {
        switch (decision()) {
            case 1: return new PageLoginCustomerSelection().init(connection);
            case 2: return new PageAddNewCustomer().init(connection);
            case 3: return new PageMainMenuEmployee().init(connection);
            case 4: return new PageViewStores().init(connection);
        }
        return null;
    }

    @Override
    protected void act() {
    }

    private int decision() {
        return Utils.reader(1,4);
    }

}
