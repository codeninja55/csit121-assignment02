package cn55.view.PurchaseView;

import java.util.EventListener;

public interface PurchaseListener extends EventListener{
    void formActionOccurred(PurchaseEvent event);
}
