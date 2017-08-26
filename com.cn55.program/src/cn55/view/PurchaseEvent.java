package cn55.view;

import java.util.EventObject;

public class PurchaseEvent extends EventObject {

    private String cardID;

    public PurchaseEvent(Object source) {
        super(source);
    }

    public PurchaseEvent(Object source, String cardID) {
        super(source);
        this.cardID = cardID;
    }

}
