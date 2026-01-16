package org.example;

import java.util.Scanner;

public class Utils {
    static int reader(int min, int max) {
        System.out.println("Please enter a number between " + min + " and " + max);
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                int number = sc.nextInt();
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max);
                }
            } catch (Exception e) {
                System.out.println("Please enter a number between " + min + " and " + max);
            }
        }
    }
}
