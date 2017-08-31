package cn55.model.CardModel;

import java.util.Comparator;

public class CardIDComparator implements Comparator<Card> {
    @Override
    public int compare(Card c1, Card c2) {
        return c1.id.compareTo(c2.id);
    }
}
