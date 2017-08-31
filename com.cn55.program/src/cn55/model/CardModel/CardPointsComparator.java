package cn55.model.CardModel;

import java.util.Comparator;

public class CardPointsComparator implements Comparator<Card> {
    @Override
    public int compare(Card c1, Card c2) {
        /* ASCENDING ORDER */
        return Double.compare(c1.points, c2.points);
        /* DESCENDING ORDER */
        //return Double.compare(c2.points, c1.points);
    }
}
