//package assignment1;
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
        return String.format("%-12s %-10s %-10.2f %s%-14.2f %-20s %-20s",
                this.cardType,this.id,this.points,"$",this.balance,this.name,this.email);
    }
}
