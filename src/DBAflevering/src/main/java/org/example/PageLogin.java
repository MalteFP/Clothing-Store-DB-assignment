package org.example;

import org.example.CustomerPages.PageLoginCustomerSelection;
import org.example.EmployeePages.PageMainMenuEmployee;

public class PageLogin extends Page {

    @Override
    protected void display() {
        System.out.println("Do you want to login as");
        System.out.println("1 Customer");
        System.out.println("2 Employee");
    }

    @Override
    protected Page nextPage() {
        UserType usertype = decision();

        if (usertype != null) {
            switch (usertype) {
                case Customer:
                    return new PageLoginCustomerSelection().init(connection);
                case Employee:
                    return new PageMainMenuEmployee().init(connection);
            }
        }
        return null;
    }

    @Override
    protected void act() {
    }

    private UserType decision() {
        switch (Utils.reader(1,2)) {
            case 1: return UserType.Customer;
            case 2: return UserType.Employee;
        }
        return null;
    }

}
