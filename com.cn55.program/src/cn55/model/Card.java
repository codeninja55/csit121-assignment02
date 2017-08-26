package cn55.model;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public abstract class Card {
    protected String id;
    protected double points;
    protected String cardType;

    /*########## CONSTRUCTORS ##########*/

    // default constructor
    public Card() {
        this.id = null;
        this.points = 0;
        this.cardType = "Card";
    }

    // constructor to overload
    public Card(String id, double points, String cardType) {
        this.id = id;
        this.points = points;
        this.cardType = cardType;
    }

    /*########## SETTERS ##########*/
    /*Abstract method to force implementation in all subclasses*/
    public abstract void calcPoints(double totalAmount);

    public void calcBalance(double totalAmount) {}

    /*########## GETTERS ##########*/
    public String getCardType() { return cardType; }
    public String getID() { return id; }
    public double getPoints() { return points; }
    public abstract String toString();
}
