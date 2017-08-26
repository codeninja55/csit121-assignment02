package cn55.view;

import java.util.EventObject;

public class CardEvent extends EventObject {

    private String cardID;

    public CardEvent(Object source) {
        super(source);
    }

    public CardEvent(Object source, String cardID) {
        super(source);
        this.cardID = cardID;
    }

}
