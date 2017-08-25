package cn55.model;

/*
 *
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 *
 */

public class BasicCard extends Card {
    private static final double POINTS_RATE = 0.015;

    private String name;
    private String email;
    private double balance;

    /*########## CONSTRUCTORS ##########*/

    // default constructor
    public BasicCard() {
        super(null,0, "BasicCard");
        this.name = "";
        this.email = "";
        this.balance = 0;
    }

    public BasicCard(String id, String name, String email) {
        super(id,0,"BasicCard");
        this.name = name;
        this.email = email;
        this.balance = 0;
    }

    // constructor with for new cards without purchase
    public BasicCard(String id, String name, String email, double totalAmount) {
        super(id,0,"BasicCard");
        this.name = name;
        this.email = email;
        this.balance = totalAmount;
    }

    /*########## SETTERS ##########*/
    @Override
    public void calcPoints(double totalAmount) { this.points += POINTS_RATE * totalAmount; }

    @Override
    public void calcBalance(double totalAmount) { this.balance += totalAmount; }

    /*########## GETTERS ##########*/
    public String toString() {
        return String.format("%n%s: %-30s%s: %-30s%s: %-30.2f%s: $%-30.2f%s: %-30s%s: %-30s%n",
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
