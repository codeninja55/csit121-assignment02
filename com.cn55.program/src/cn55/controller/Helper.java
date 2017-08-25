package cn55.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

/* USE THIS HELPER CLASS FOR VALIDATION AND ALL EXCEPTION HANDLING*/

public class Helper {

    /*==================== NEW HUI SUPPORT METHODS ====================*/

    /*==================== OLD CONSOLE SUPPORT METHODS ====================*/
    /*public static int userSelection() {
        try {
            System.out.print("\nEnter your option:  ");
            int userChoice = input.nextInt();
            input.nextLine();

            if (userChoice > 6) {
                System.out.println("\nYou did not enter valid range from the menu.");
                System.out.println("Please select again with an integer shown in the menu...");
                input.nextLine();
                return userSelection(); // recursively run again until input correct
            } else {
                return userChoice;
            }
        } catch (InputMismatchException e) {
            System.out.println("\nYou did not enter an integer from the menu.");
            System.out.println("Please select again with an integer (i.e. 1)...");
            input.nextLine(); // discard previous input
            return userSelection();
        }
    }*/

    /*public static int confirm(String message) {

        System.out.print("\n" + message);

        String confirm = input.nextLine();

        if (confirm.isEmpty() || confirm.equalsIgnoreCase("y")) {
            return 1;
        } else if (confirm.equalsIgnoreCase("n")){
            return 0;
        } else {
            System.out.print("\nPlease only input y or n [press enter for default(Y)]: ");
            return confirm(message);
        }
    }*/

    /*public static int thresholdInput(String message) {
        System.out.printf("%n%s", message);

        String value = input.nextLine();

        try {
            if (value.isEmpty()) {
                System.out.printf("%n%s", "You provided an empty input. Please try again.");
                return thresholdInput(message);
            } else {
                return Integer.parseInt(value);
            }
        } catch (NumberFormatException nfe) {
            System.out.printf("%n%s", "You did not provide an integer. Please try again.");
            return thresholdInput(message);
        }
    }*/
}
