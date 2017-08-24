package cn55.card;
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
        return String.format("%-12s %-10s %-10.2f %s%-14.2f %-20s %-20s",
                this.cardType,this.id,this.points,"$",this.balance,this.name,this.email);
    }
}
