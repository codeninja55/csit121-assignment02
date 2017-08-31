package cn55.model.CardModel;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

import cn55.model.CardType;

public class BasicCard extends AdvancedCard {
    private static final double POINTS_RATE = 0.015;

    /*============================== CONSTRUCTORS  ==============================*/
    public BasicCard(String name, String email) {
        super(name, email);
        super.cardType = CardType.BasicCard.getName();
    }

    // constructor with for new cards without purchase
    public BasicCard(String name, String email, double totalAmount) {
        super(name, email);
        super.cardType = CardType.BasicCard.getName();
        super.balance = totalAmount;
    }

    /*============================== MUTATORS  ==============================*/
    public void calcPoints(double totalAmount) { this.points += POINTS_RATE * totalAmount; }

    public void calcBalance(double totalAmount) { this.balance += totalAmount; }

    /*============================== ACCESSORS  ==============================*/
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
