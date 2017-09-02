package cn55.model.CardModel;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

import cn55.model.Database;

import java.util.Comparator;

public abstract class Card implements Comparable<Card> {

    protected String id;
    double points;
    String cardType;

    /*============================== CONSTRUCTORS  ==============================*/
    // Default constructor
    public Card() {
        this.id = Database.generateCardID();
        Database.addCardIDSet(id);
        this.points = 0;
        this.cardType = null;
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
    public boolean equals(Card other) {
        return this.id.equals(other.id);
    }

    @Override
    public int compareTo(Card o) {
        int id = Integer.parseInt(this.getID().substring(2, this.getID().length()));
        int oid = Integer.parseInt(o.getID().substring(2, o.getID().length()));
        return Integer.compare(id, oid);
    }
}
