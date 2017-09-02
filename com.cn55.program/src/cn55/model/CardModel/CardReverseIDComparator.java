package cn55.model.CardModel;

import java.util.Comparator;

public class CardReverseIDComparator implements Comparator<Card> {
    @Override
    public int compare(Card c1, Card c2) {
        return c2.id.compareTo(c1.id);
    }
}
