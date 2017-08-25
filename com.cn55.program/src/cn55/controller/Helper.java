package cn55.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

/* USE THIS HELPER CLASS FOR VALIDATION AND ALL EXCEPTION HANDLING*/

public class Helper {

    private static Scanner input = new Scanner(System.in);

    public static int userSelection() {
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
    }

    public static int confirm(String message) {

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
    }

    public static void printMenu() {
        System.out.println("\n******************************");
        System.out.println("********* Main Menu **********");
        System.out.println("******************************");
        System.out.printf("\nPlease choose from below:\n" +
                "[ 0 ] Exit\n" +
                "[ 1 ] Create Customer Categories\n" +
                "[ 2 ] Show All Cards\n" +
                "[ 3 ] Show All Purchases\n" +
                //"[ 4 ] Add cn55.model.Purchase\n" +
                "[ 4 ] Show Total Purchases\n" +
                "[ 5 ] Show Customer Points\n");
        System.out.println("\n******************************");
    }

    public static void createCardMenu() {
        System.out.printf("%nPlease select a Card Type choice from below:%n" +
                "[ 1 ] Anon Card%n" +
                "[ 2 ] Basic Card%n" +
                "[ 3 ] Premium Card%n" +
                "[ 0 ] Exit%n");
    }

    public static String cardSelection() {
        createCardMenu();

        int selection = userSelection();

        switch (selection) {
            case 0: return "";
            case 1: return "AnonCard";
            case 2: return "BasicCard";
            case 3: return "PremiumCard";
            default: return "";
        }
    }

    public static int thresholdInput(String message) {
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
    }
}
