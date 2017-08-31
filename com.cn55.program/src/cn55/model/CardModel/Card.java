package cn55.model.CardModel;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

import cn55.model.CardType;

public abstract class Card {

    private static int idCounter = 10000;
    protected String id;
    double points;
    String cardType;

    /*============================== CONSTRUCTORS  ==============================*/
    // default constructor
    public Card() {
        this.id = "MC" + ++idCounter;
        this.points = 0;
        this.cardType = CardType.Card.getName();
    }

    /*============================== MUTATORS  ==============================*/
    /*Abstract method to force implementation in all subclasses*/
    public abstract void calcPoints(double totalAmount);

    public void calcBalance(double totalAmount) {}

    /*============================== ACCESSORS  ==============================*/
    public String getCardType() { return cardType; }
    public String getID() { return id; }
    public double getPoints() { return points; }
    public abstract String toString();
}
