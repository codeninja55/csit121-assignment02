package cn55.model.CardModel;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

import cn55.model.CardType;

public class AnonCard extends Card {
    private static final double POINTS_RATE = 0.01;

    /*########## CONSTRUCTORS ##########*/

    // default constructor
    public AnonCard() {
        super(null,0, CardType.Card.getName()); }

    public AnonCard(String id) {
        super(id,0,CardType.AnonCard.getName()); }

    /*########## SETTERS ##########*/
    @Override
    public void calcPoints(double totalAmount) {
        this.points += POINTS_RATE * totalAmount;
    }

    /*########## GETTERS ##########*/
    @Override
    public String toString() {
        return String.format("%n%s: %s %s: %s%s: %.2f%n",
                "Card Type", super.cardType,
                "Card ID", super.id,
                "Points", super.points);
    }

    public String getID() { return id; }

}
