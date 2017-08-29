package cn55.view.PurchaseView;

import java.util.EventObject;

public class PurchaseEvent extends EventObject {

    private int receiptID;
    private String cardID;

    public PurchaseEvent(Object source) {
        super(source);
    }

    public PurchaseEvent(Object source, int receiptID, String cardID) {
        super(source);
        this.receiptID = receiptID;
        this.cardID = cardID;
    }

}
