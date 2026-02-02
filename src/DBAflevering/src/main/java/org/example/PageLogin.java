package org.example;

import org.example.CustomerPages.PageLoginCustomerSelection;
import org.example.EmployeePages.PageMainMenuEmployee;

public class PageLogin extends Page {

    @Override
    protected void display() {
        System.out.println("How do you want to proceed?");
        System.out.println("1 Login as customer");
        System.out.println("2 Sign-Up as customer");
        System.out.println("3 Login as employee");
        System.out.println("4 View stores");
        System.out.println("5 Exit");
    }

    @Override
    protected Page nextPage() {
        switch (decision()) {
            case 1: return new PageLoginCustomerSelection().init(connection);
            case 2: return new PageAddNewCustomer().init(connection);
            case 3: return new PageMainMenuEmployee().init(connection);
            case 4: return new PageViewStores().init(connection);
            case 5: return null;
        }
        return null;
    }

    @Override
    protected void act() {
    }

    private int decision() {
        return Utils.reader(1,5);
    }

}
