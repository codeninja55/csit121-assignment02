package cn55.model.CardModel;

import java.util.Comparator;

public class CardNameComparator implements Comparator<Card> {
    @Override
    public int compare(Card c1, Card c2) {
        if (c1 instanceof AdvancedCard && c2 instanceof AdvancedCard)
            return ((AdvancedCard) c1).name.compareTo(((AdvancedCard) c2).name);

        if (c1 instanceof AnonCard && c2 instanceof AdvancedCard)
            return -1;
        else
            return 1;
    }

}
