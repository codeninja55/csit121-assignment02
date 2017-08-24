package cn55.model;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class AnonCard extends Card {
    private static final double POINTS_RATE = 0.01;

    /*########## CONSTRUCTORS ##########*/

    // default constructor
    public AnonCard() {
        super(null,0,"AnonCard"); }

    public AnonCard(String id) {
        super(id,0,"AnonCard"); }

    /*########## SETTERS ##########*/
    @Override
    public void calcPoints(double totalAmount) {
        this.points += POINTS_RATE * totalAmount;
    }

    /*########## GETTERS ##########*/
    @Override
    public String toString() {
        return String.format("%-12s %-10s %-10.2f",this.cardType, this.id, this.points);
    }

    public String getID() { return id; }

}
