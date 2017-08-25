package cn55.model;

/* THIS CLASS IS A TEMP DATABASE */

/*
*
* Things to move here:
* - Cards ArrayList
* - Purchases ArrayList
* - Categories ArrayList
* - ReceiptID Set
* - CardID Set
*
* */

import java.util.*;

public class Database {

    private ArrayList<Card> cards;
    private ArrayList<Purchase> purchases;
    private Map<String, Double> categories;
    private Set<Integer> receiptSet;
    private Set<String> cardIDSet;

    Database() {
        cards = new ArrayList<>();
        purchases = new ArrayList<>();
        categories = new HashMap<>();
        receiptSet = new HashSet<>();
        cardIDSet = new HashSet<>();
    }

    /*==================== MUTATORS ====================*/
    public void addCards(Card card) {
        this.cards.add(card);
    }

    public void addPurchases(Purchase purchase) {
        this.purchases.add(purchase);
    }

    public void addCategory(String category, Double value) {
        this.categories.put(category, value);
    }

    public void addReceipt(int receiptID) {
        this.receiptSet.add(receiptID);
    }

    public void addCardID(String cardID) {
        this.cardIDSet.add(cardID);
    }

    /*==================== ACCESSORS ====================*/
    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public Map<String, Double> getCategories() {
        return categories;
    }

    public Set<Integer> getReceiptSet() {
        return receiptSet;
    }

    public Set<String> getCardIDSet() {
        return cardIDSet;
    }
}
