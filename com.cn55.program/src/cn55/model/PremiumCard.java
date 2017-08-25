package cn55.model;
/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class PremiumCard extends Card {
    private static final double POINTS_RATE_LOW = 0.025;
    private static final double POINTS_RATE_HIGH = 0.03;
    private static final double SIGNUP_FEE = 25.0;

    private String name;
    private String email;
    private double balance;

    /*########## CONSTRUCTORS ##########*/

    // default constructor
    public PremiumCard() {
        super(null,0,"PremiumCard");
        this.name = "";
        this.email = "";
        this.balance = 0;
    }

    // constructor with details
    public PremiumCard(String id, String name, String email, double totalAmount) {
        super(id,0,"PremiumCard");
        this.name = name;
        this.email = email;
        this.balance = totalAmount; //- SIGNUP_FEE; - we were told to ignore this
    }

    /*########## SETTERS ##########*/
    @Override
    public void calcPoints(double totalAmount) {
        if (totalAmount < 40 && this.balance < 1000)
            this.points += totalAmount * POINTS_RATE_LOW;
        else
            this.points += totalAmount * POINTS_RATE_HIGH;
    }

    @Override
    public void calcBalance(double totalAmount) { this.balance += totalAmount; }

    /*########## GETTERS ##########*/
    @Override
    public String toString() {
        return String.format("%n%s: %s%n%s: %s%n%s: %.2f%n%s: $%.2f%n%s: %s%n%s: %s%n",
                "Card Type",this.cardType,
                "Card ID",this.id,
                "Points",this.points,
                "Balance",this.balance,
                "Customer Name",this.name,
                "Customer Email",this.email);
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public double getBalance() { return balance; }
}
