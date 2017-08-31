package cn55.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class Purchase {
    private final int receiptID;
    private String cardID;
    private String cardType;
    private Date purchaseTime;
    private Map<String, Double> categories;

    /*============================== CONSTRUCTORS ==============================*/
    // Default constructor
    public Purchase() {
        this.receiptID = 0;
        this.cardID = "";
        this.cardType = null;
        this.purchaseTime = setPurchaseTime();
        this.categories = new HashMap<>();
    }

    // Constructor for cash purchases
    Purchase(Map<String, Double> categories, int receiptID) {
        this.receiptID = receiptID;
        this.cardID = null;
        this.cardType = CardType.Cash.getName();
        this.categories = categories;
        this.purchaseTime = setPurchaseTime();
    }

    // Constructor for model purchases
    Purchase(String cardID, String cardType, Map<String, Double> categories, int receiptID) {
        this.receiptID = receiptID;
        this.cardID = cardID;
        this.cardType = cardType;
        this.categories = categories;
        this.purchaseTime = setPurchaseTime();
    }

    /*============================== MUTATORS  ==============================*/

    private Date setPurchaseTime() {
        // create a java calendar instance and sets that to a Date object
        // REFERENCE: https://alvinalexander.com/java/java-timestamp-example-current-time-now
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    void setCardID(String cardID) {
        this.cardID = cardID;
    }

    void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /*============================== ACCESSORS  ==============================*/
    public int getReceiptID() { return receiptID; }

    public String getCardID() { return cardID; }

    public String getCardType() { return cardType; }

    public Date getPurchaseTime() { return purchaseTime; }

    public Map<String, Double> getCategories() { return categories; }

    public String toString() {

        String firstOutput = String.format(
                "%n%-20s %s" +
                "%n%-20s %s" +
                "%n%-20s %s" +
                "%n%-20s %-20tc",
                "Receipt ID", this.receiptID,
                "Card ID:",this.cardID,
                "Card Type:",this.cardType,
                "Purchase Time:",this.purchaseTime);

        String secondOutput = "";

        for (Map.Entry<String, Double> item : this.categories.entrySet())
            secondOutput += String.format("%n%-20s $%.2f", (item.getKey() + ":"), item.getValue());

        return firstOutput + secondOutput;
    }

    public double getCategoriesTotal() {
        double total = 0;
        for (Map.Entry<String, Double> item : this.categories.entrySet()) total += item.getValue();
        return total;
    }
}
