package cn55.model;

import java.util.*;
import cn55.controller.Helper;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class Shop {

    private Database db;
    private Scanner input = new Scanner(System.in);

    /*#################### CONSTRUCTORS #########################*/

    // default
    public Shop() {
        this.db = new Database();
        this.createCategories(userCategories(true));
    }

    // constructor to initialize shop with custom categories
    public Shop(boolean auto) {
        this.db = new Database();
        this.createCategories(userCategories(auto));
    }

    /*######################### SETTERS #########################*/

    /*This method generates a receiptID and checks if that ID has already been generated
    * and placed in the receiptSet instance variable*/
    public int generateReceiptID() {
        Random randomObj = new Random();

        int receiptID = randomObj.ints(10000000,99999999).findFirst().getAsInt();

        if (db.getReceiptSet().contains(receiptID)) {
            return generateReceiptID();
        } else {
            db.addReceipt(receiptID);
            return receiptID;
        }
    }

    public String generateCardID() {
        Random randomObj = new Random();
        int cardID = randomObj.ints(10000,99999).findFirst().getAsInt();

        if (db.getCardIDSet().contains(cardID)) {
            return generateCardID();
        } else {
            String cardIDStr = Integer.toString(cardID);
            db.addCardID(cardIDStr);
            return cardIDStr;
        }
    }

    public void makePurchase(String cardID, Map<String, Double> categories) {

        /*NOTE: If using inputs, then setCategories() would allow user to input
        *       Total Amount for each category*/
        //setCategories();

        ArrayList<Card> cardsCopy = new ArrayList<>(db.getCards());

        boolean newCard = true; // flag to see if new model required

        if (cardID.equalsIgnoreCase("cash")) {
            /* If it just a cash purchase, no updates required to model */
            db.addPurchases(new Purchase(categories, generateReceiptID()));
        } else {
            /* Loop through cards ArrayList to validate for existing cards
             * if the model does not exist, prompt user to make one. */
            for (Card card : cardsCopy) {

                if (card.getID().equals(cardID)) {
                    String cardType = card.getCardType();
                    Purchase newPurchase = new Purchase(cardID,cardType,categories,generateReceiptID());
                    card.calcPoints(newPurchase.calcCategoriesTotal());

                    if (!cardType.equalsIgnoreCase("AnonCard"))
                        card.calcBalance(newPurchase.calcCategoriesTotal());

                    db.addPurchases(newPurchase);
                    newCard = false; // set flag so new model not created
                    break;
                }
            }

            if (newCard) {
                System.out.print("\nPlease create a new model for this purchase\n");
                //makeCard(categories);
            }
        }
    } // end of makePurchase method

    public void makeCard(HashMap<String, String> newCard) {

        String name = newCard.get("name");
        String email = newCard.get("email");
        String cardType = newCard.get("cardType");
        String cardID = newCard.get("cardID");
        Card card;

        if (cardType.equalsIgnoreCase("AnonCard")) {
            card = new AnonCard(cardID);
            db.addCards(card);
        } else {

            if (cardType.equalsIgnoreCase("BasicCard"))
                card = new BasicCard(cardID, name, email);
            else
                card = new PremiumCard(generateCardID(), name, email);

            db.addCards(card);
        }
    } // end of createCard method

    public boolean cardExists(String cardID) {
        HashMap<String, Integer> cardMap = db.getCardMap();
        return cardMap.containsKey(cardID);
    }

    public void deleteCard(String cardID) {
        if (db.getCardMap().containsKey(cardID)) {
            int index = db.getCardMap().get(cardID);
            db.getCards().remove(index);
        }
    }

    /*This method allows users to create a whole new set of categories
    * or add to the currently stored categories after putting the ArrayList
    * through the createCategories method to store them as a HashMap.*/
    private ArrayList<String> userCategories(boolean auto) {
        ArrayList<String> categoriesList = new ArrayList<>();
        String option;

        if (auto) {
            /*If the auto flag is true, the categories list will automatically
            * populate with the default*/
            categoriesList.add("Motors");
            categoriesList.add("Electronics");
            categoriesList.add("Fashion");
            categoriesList.add("Toys");
            categoriesList.add("Sporting Goods");
            categoriesList.add("Deals");

            return categoriesList;
        } else {
            System.out.printf("%s%n%s%n%s%n%s%n%n",
                    "Please type the names for a category to add to the list.",
                    "Type [ default ] to use a template list.",
                    "Template: Deals, Electronics, Toys, Sporting Goods, Fashion, Motors",
                    "***** Type [ finished ] or [ x ] to save and quit *****");
            System.out.print(">  ");

            while (input.hasNextLine()) {
                System.out.print(">  ");
                option = input.nextLine();

                if (option.equalsIgnoreCase("x") ||
                        option.equalsIgnoreCase("finished")) {
                    break;
                } else if (option.equalsIgnoreCase("default") || option.equals("")) {
                    userCategories(true);
                } else {
                    categoriesList.add(option);
                }
            }

            System.out.println("\nYou have typed the following list:");

            for (String item : categoriesList)
                System.out.println(item);

            return categoriesList; }
    } // end of userCategories method

    /*This method takes the ArrayList from the userCategories method and adds or creates
    * them to store in the HashMap instance variable categories*/
    private void createCategories(ArrayList<String> categoriesList) {
        for (String item : categoriesList)
            db.addCategory(item, 0D);
    }

    /*This method allows user to input the total amount for each category*/
    /*private void setCategories() {

        *//*Creates a new HashMap for the Menu
        * Loop through the categories HashMap, retrieve the keys and place them in
        * a new HashMap with an int as the key. This way the menu option has an int
        * to print to the screen. *//*
        Map<Integer, String> categoriesMenu = new HashMap<>();
        int counter = 1;

        for (Map.Entry<String, Double> item : db.getCategories().entrySet()) {
            categoriesMenu.put(counter,item.getKey());
            counter++;
        }

        *//*Displays the menu to the screen and through options of categories for user
        * to select with an int input. It will then remove than item fromm the menu
        * HashMap so the user cannot input a value twice. Exits loop when user enters 0
        * or presses enter on a blank newline character *//*
        while (true) {
            System.out.printf("%nPlease select cn55.model.Purchase Category from below to add amount:%n");
            System.out.printf("[ 0 ] %s%n", "Finished");

            for (Map.Entry<Integer, String> item : categoriesMenu.entrySet())
                System.out.printf("[ %d ] %s%n", item.getKey(), item.getValue());

            String selection = "";
            boolean sentinel = true;

            for (Map.Entry<Integer, String> item : categoriesMenu.entrySet()) {
                selection = item.getValue();
                sentinel = false;
            }

            if (sentinel)
                selection = "";

            if (selection.isEmpty()) {
                break;
            } else {
                System.out.printf("Enter Total Amount for %s Category:  ", selection);
                double categoryAmount = input.nextDouble();
                db.addCategory(selection, categoryAmount);
                categoriesMenu.remove(choice);
            }
        }
    } // end of setCategories method*/

    /*This method creates a container to store the number of thresholds with
     *each being a String for the key and an 2 int Array for min and max values*/
    /*private Map<String, int[]> createThresholdContainer() {

        System.out.printf("%n%s%n%s%n%s%n%s%n%s",
                "You can set the Points Threshold as follows:",
                "1. Give a number of thresholds.",
                "2. Give the minimum value for a threshold (for Threshold 1, 0 for no minimum).",
                "3. Give the maximum value for a threshold (for final Threshold, 0 for no maximum).",
                "4. Repeat until number of thresholds have a min and max value.");

        int thresholdNumber = Helper.thresholdInput("\nInput the number of thresholds:  ");

        *//*HashMap to store each threshold and its min and max values*//*
        Map<String, int[]> thresholdList = new HashMap<>();

        // Grab input from user for a min and max and store them as elements in int[]
        for (int counter = 1 ; counter <= thresholdNumber ; counter++ ) {
            int[] valArr = new int[2];
            String name = "Threshold " + Integer.toString(counter);

            *//*Using the helper method to check for validation and empty strings*//*
            int min = Helper.thresholdInput(String.format("%nInput %s minimum value:  ", name));
            int max = Helper.thresholdInput(String.format("Input %s maximum value:  ", name));

            // TODO Need to do some validation checking if numbers are fine
            valArr[0] = min;

            *//*Checks the last Threshold value if it is set to 0 and sets the value to
             *the MAX_VALUE allowed for an integer *//*
            if (counter == thresholdNumber && max == 0)
                valArr[1] = Integer.MAX_VALUE;
            else
                valArr[1] = max;

            thresholdList.put(name, valArr);
        }
        return thresholdList;
    }*/

    /*private Map<String, Integer> calcPointsThreshold(Map<String, int[]> thresholdList) {

        Map<String, Integer> thresholdResults = new HashMap<>();

        *//*Populate the map with just the keys and iniatialize the value to 0*//*
        for (Map.Entry<String, int[]> item : thresholdList.entrySet())
            thresholdResults.put(item.getKey(), 0);

        for (Card card : db.getCards()) {
            for (Map.Entry<String, int[]> item : thresholdList.entrySet()) {

                *//*If the model.points is in the range of the min at index 0 and max at index 1
                * increase the thresholdResults value by 1*//*
                if (card.getPoints() >= item.getValue()[0] && card.getPoints() < item.getValue()[1])
                    thresholdResults.put(item.getKey(), thresholdResults.get(item.getKey()) + 1);
            }
        }
        return thresholdResults;
    }*/

    /*######################### GETTERS #########################*/

    public Database getDatabase() { return db; }

    /*######################### HELPERS #########################*/

    public void showPurchases() {
        for (Purchase purchase : db.getPurchases())
            System.out.println(purchase.toString());
    }

    /*public void showTotalPurchases() {
        System.out.printf("%n%n%-20s %s%n","Category","Total");

        *//*Create a Map of the default categories map with the same keys
        * and the value being an array. *//*
        Map<String, ArrayList<Double>> categoryTotal = new HashMap<>();

        for (Map.Entry<String, Double> item : db.getCategories().entrySet())
            categoryTotal.put(item.getKey(), new ArrayList<>());

        // Loop through purchase ArrayList and get the categories from each purchase
        for (Purchase purchase : db.getPurchases()) {
            Map<String, Double> categoriesMap = purchase.getCategoriesMap();

            *//* Loop through the categories stored in purchase and add them to the ArrayList of
             * of the default categories map *//*
            for (Map.Entry<String, Double> item : categoriesMap.entrySet())
                categoryTotal.get(item.getKey()).add(item.getValue());
        }

        *//*Loop through each List and sum them together to print*//*
        for (Map.Entry<String, ArrayList<Double>> item : categoryTotal.entrySet()) {
            double sum = 0;

            for (Double values : item.getValue())
                sum += values;

            System.out.printf("%n%-20s $%.2f", (item.getKey() + ":"), sum);
        }
        System.out.println("\n\n");
    }*/

    /*public void showPoints() {

        double totalPoints = 0;

        // prompt user if they would like to make a new threshold
        // otherwise default to the ones already created below

        System.out.printf("%n%s%n%s%n%s%n%s","Default Points Threshold Levels:",
                "Low (less than 500", "Medium (between 500 and 2000", "High (more than 2000)");

        int confirm = Helper.confirm("\nDo you wish to change the points threshold levels? [Y/n]: ");

        if (confirm == 1) {
            Map<String, int[]> thresholdList = createThresholdContainer();
            Map<String, Integer> thresholdResults = calcPointsThreshold(thresholdList);

            for (Card card : db.getCards())
                totalPoints += card.getPoints();

            System.out.printf("%n%nTotal Points for All Customers: %.2f%n%n", totalPoints);
            System.out.println("Customers by Thresholds");

            *//*Print out the list based on the stored results in thresholdResults HashMap*//*
            for (Map.Entry<String, Integer> item : thresholdResults.entrySet()) {
                System.out.printf("%n%s (Min: %d; Max: %d): %d",
                        item.getKey(), thresholdList.get(item.getKey())[0],
                        thresholdList.get(item.getKey())[1], item.getValue());
            }
            System.out.print("\n\n");
        } else if (confirm == 0) {
            // Default points thresholds by customer
            int low = 0;
            int medium = 0;
            int high = 0;

            for (Card card : db.getCards()) {
                totalPoints += card.getPoints();

                if (card.getPoints() < 500D) {
                    low++;
                } else if (card.getPoints() > 500D && card.getPoints() < 2000D) {
                    medium++;
                } else {
                    high++;
                }
            }

            System.out.printf("%n%nTotal Points for All Customers: %.2f%n%n", totalPoints);
            System.out.println("Customers by Thresholds");
            System.out.printf("Low (less than 500): %d%nMedium (500 to 2000): %d%n" +
                    "High (more than 2000): %d%n%n", low, medium, high);
        }
    }*/
}
