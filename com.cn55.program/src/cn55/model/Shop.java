package cn55.model;

import cn55.model.CardModel.*;

import java.util.*;

@SuppressWarnings("ConstantConditions")
public class Shop {

    private Database db;

    /*============================== CONSTRUCTORS  ==============================*/
    // default
    public Shop() {
        this.db = new Database();
        generateDefaultCategories();
        Database.mapCategoriesTotalMap(db.getCategories());
    }

    /*============================== MUTATORS  ==============================*/
    private void generateDefaultCategories() {
        ArrayList<Category> categories = db.getCategories();
        categories.add(new Category("Other", "#Description"));
        categories.add(new Category("Motors", "#Description"));
        categories.add(new Category("Electronics", "#Description"));
        categories.add(new Category("Fashion", "#Description"));
        categories.add(new Category("Toys", "#Description"));
        categories.add(new Category("Deals", "#Description"));

        categories.sort(new CategoriesComparator());
    }

    public void makeCategory(Category category) {
        db.addCategory(category);
    }

    public void makePurchase(String cardID, int receiptID, HashMap<Integer, Category> categories) {

        if (cardID.equals(CardType.Cash.getName())) {
            updateCategoriesTotalMap(categories);
            db.addPurchase(new Purchase(categories, receiptID));
        } else {
            if (cardExists(cardID)) {
                updateCategoriesTotalMap(categories);
                Card card = db.getCards().get(db.getCardMap().get(cardID));
                String cardType = card.getCardType();
                Purchase newPurchase = new Purchase(cardID, cardType, categories, receiptID);
                card.calcPoints(newPurchase.getCategoriesTotal());

                if(!cardType.equals(CardType.AnonCard.getName()))
                    card.calcBalance(newPurchase.getCategoriesTotal());

                db.addPurchase(newPurchase);
            }
        }
    }

    public void makeCard(HashMap<String, String> newCard) {

        String name = newCard.get("name");
        String email = newCard.get("email");
        String cardType = newCard.get("cardType");
        Card card;

        if (!cardType.equals(CardType.AnonCard.getName())) {
            if (cardType.equals(CardType.BasicCard.getName()))
                card = new BasicCard(name, email);
            else
                card = new PremiumCard(name, email);

            db.addCards(card);
        } else {
            card = new AnonCard();
            db.addCards(card);
        }
    }

    public boolean cardExists(String cardID) {
        db.mapCards();
        HashMap<String, Integer> cardMap = db.getCardMap();
        return cardMap.containsKey(cardID);
    }

    public void deleteCard(String cardID) {
        db.mapCards();
        int index = db.getCardMap().get(cardID);
        if (db.getCardMap().containsKey(cardID)) {
            try {
                db.getCards().remove(index);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    public void convertPurchase(String cardID) {
        db.mapPurchases();
        for (Purchase purchase : db.getPurchases()) {
            if (purchase.getCardID() != null && purchase.getCardID().equals(cardID)) {
                purchase.setCardID(null);
                purchase.setCardType(CardType.Cash.getName());
            }
        }
    }

    private void updateCategoriesTotalMap(HashMap<Integer, Category> categories) {

        for (HashMap.Entry<Integer, Category> item : categories.entrySet()) {
            Double newTotal = Database.categoriesTotalMap.get(item.getKey()) + item.getValue().getAmount();
            Database.categoriesTotalMap.put(item.getKey(), newTotal);
        }
    }

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

        *//*Populate the map with just the keys and initialize the value to 0*//*
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

    /*============================== ACCESSORS  ==============================*/
    public Database getDatabase() { return db; }

    /*######################### HELPERS #########################*/

    /*public void showPurchases() {
        for (Purchase purchase : db.getPurchases())
            System.out.println(purchase.toString());
    }*/

    /*public void showTotalPurchases() {
        System.out.printf("%n%n%-20s %s%n","Category","Total");

        *//*Create a Map of the default categories map with the same keys
        * and the value being an array. *//*
        Map<String, ArrayList<Double>> categoryTotal = new HashMap<>();

        for (Map.Entry<String, Double> item : db.getCategoriesMap().entrySet())
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
