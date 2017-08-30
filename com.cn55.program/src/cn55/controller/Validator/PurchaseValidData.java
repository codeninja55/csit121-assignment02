package cn55.controller.Validator;

import java.util.HashMap;

public class PurchaseValidData {
    private int receiptID;
    private String cardID;
    private HashMap<String, Double> categories;
    private String cardType;

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public void setCategories(HashMap<String, Double> categories) {
        this.categories = categories;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public String getCardID() {
        return cardID;
    }

    public HashMap<String, Double> getCategories() {
        return categories;
    }

    public String getCardType() {
        return cardType;
    }
}
