package cn55.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

@SuppressWarnings("SameParameterValue")
public class Purchase {
    private final int receiptID;
    private String cardID;
    private String cardType;
    private final Date purchaseTime;
    private final HashMap<Integer, Category> categories;

    /*============================== CONSTRUCTORS ==============================*/
    // Constructor for cash purchases
    Purchase(HashMap<Integer, Category> categories, int receiptID) {
        this.receiptID = receiptID;
        this.cardID = null;
        this.cardType = CardType.Cash.getName();
        this.categories = categories;
        this.purchaseTime = setPurchaseTime();
    }

    // Constructor for Card purchases
    Purchase(String cardID, String cardType, HashMap<Integer, Category> categories, int receiptID) {
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

    void convertPurchase() {
        this.cardType = CardType.Cash.getName();
        this.cardID = null;
    }

    /*============================== ACCESSORS  ==============================*/
    public int getReceiptID() { return receiptID; }

    public String getCardID() { return cardID; }

    public String getCardType() { return cardType; }

    public HashMap<Integer, Category> getCategories() {
        return categories;
    }

    public Date getPurchaseTime() { return purchaseTime; }

    public double getCategoriesTotal() {
        double total = 0;
        for (HashMap.Entry<Integer, Category> item : this.categories.entrySet())
            total = total + item.getValue().getAmount();

        return total;
    }

    public String toString() {
        StringBuilder secondOutput = new StringBuilder();
        String firstOutput = String.format("%n%-20s %s%n%-20s %s%n%-20s %s%n%-20s %-20tc",
                "Receipt ID", this.receiptID,
                "Card ID:",this.cardID,
                "Card Type:",this.cardType,
                "Purchase Time:",this.purchaseTime);

        categories.forEach((k, v) -> secondOutput.append(String.format("%n%-20s $%.2f", (v.getName() + ":"), v.getAmount())));
        return firstOutput + secondOutput;
    }
}
