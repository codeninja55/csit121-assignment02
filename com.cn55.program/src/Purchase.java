//package assignment1;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.Calendar;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class Purchase {
    private final int receiptID;
    private final String cardID;
    private final String cardType;

    private Date purchaseTime;

    private Map<String, Double> categories;

    /*########## CONSTRUCTORS ##########*/

    // default constructor
    public Purchase() {
        this.receiptID = 0;
        this.cardID = "";
        this.cardType = null;
        this.purchaseTime = setPurchaseTime();
        this.categories = new HashMap<>();
    }

    // constructor for cash purchases
    public Purchase(Map<String, Double> categories, int receiptID) {
        this.receiptID = receiptID;
        this.cardID = null;
        this.cardType = "Cash";
        this.categories = categories;
        this.purchaseTime = setPurchaseTime();
    } // end of constructor for cash

    // constructor for card purchases
    public Purchase(String cardID, String cardType, Map<String, Double> categories, int receiptID) {
        this.receiptID = receiptID;
        this.cardID = cardID;
        this.cardType = cardType;
        this.categories = categories;
        this.purchaseTime = setPurchaseTime();
    } // end of constructor for card

    /*########## SETTERS ##########*/

    private final Date setPurchaseTime() {
        // create a java calendar instance and sets that to a Date object
        // REFERENCE: https://alvinalexander.com/java/java-timestamp-example-current-time-now
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        return now;
    } // end of setPurchaseTime method

    /*########## GETTERS ##########*/

    @Override
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

    public double calcCategoriesTotal() {
        double total = 0;

        for (Map.Entry<String, Double> item : this.categories.entrySet())
            total += item.getValue();

        return total;
    }

    public Map<String, Double> getCategoriesMap() { return this.categories; }
}
