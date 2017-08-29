package cn55.view.PurchaseView;

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

    public void testEventMethod(String text) {
        System.out.println(text);
    }

}
