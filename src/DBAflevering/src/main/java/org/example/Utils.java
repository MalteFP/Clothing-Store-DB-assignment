package org.example;

import java.util.Scanner;

public class Utils {
    public static int reader(int min, int max) {
        System.out.printf("%nPlease enter a number between " + min + " and " + max + "%n");
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                int number = sc.nextInt();
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.printf("%nPlease enter a number between " + min + " and " + max + "%n");
                }
            } catch (Exception e) {
                System.out.printf("%nPlease enter a number between " + min + " and " + max + "%n");
            }
        }
    }
}
