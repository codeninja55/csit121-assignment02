package cn55.model.CardModel;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

import cn55.model.CardType;

public class AnonCard extends Card {
    private static final double POINTS_RATE = 0.01;

    /*============================== CONSTRUCTORS  ==============================*/
    public AnonCard() {
        super();
        super.cardType = CardType.AnonCard.getName();
    }

    /*============================== MUTATORS  ==============================*/
    public void calcPoints(double totalAmount) {
        this.points += POINTS_RATE * totalAmount;
    }

    /*============================== ACCESSORS  ==============================*/
    public String getID() { return id; }

    public String toString() {
        return String.format("%n%s: %s%n%s: %s%n%s: %.2f%n",
                "Card Type", super.cardType,
                "Card ID", super.id,
                "Points", super.points);
    }
}
