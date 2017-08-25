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
        return String.format("%n%s: %-30s %s: %-30s%s: %-30.2f%n",
                "Card Type", this.cardType,
                "Card ID", this.id,
                "Points", this.points);
    }

    public String getID() { return id; }

}
