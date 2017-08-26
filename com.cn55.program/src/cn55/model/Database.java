package cn55.model;

/* THIS CLASS IS A TEMP DATABASE OBJECT */

import java.util.*;

public class Database {

    private ArrayList<Card> cards;
    private HashMap<String, Integer> cardMap;
    private ArrayList<Purchase> purchases;
    private Map<String, Double> categories;
    private ArrayList<String> categoriesList;
    private Set<Integer> receiptSet;
    private Set<String> cardIDSet;

    Database() {
        this.cards = new ArrayList<>();
        this.cardMap = new HashMap<>();
        this.purchases = new ArrayList<>();
        this.categories = new HashMap<>();
        this.createCategoriesList();
        this.receiptSet = new HashSet<>();
        this.cardIDSet = new HashSet<>();
    }

    /*==================== MUTATORS ====================*/
    public void mapCards() {
        HashMap<String, Integer> cardMap = new HashMap<>();
        for (int index = 0 ; index < cards.size() ; index++) {
            cardMap.put(cards.get(index).getID(), index);
        }

        this.cardMap = cardMap;
    }

    public void addCards(Card card) {
        this.cards.add(card);
    }

    public void addPurchases(Purchase purchase) {
        this.purchases.add(purchase);
    }

    private void createCategoriesList() {
        this.categoriesList = new ArrayList<>();

        categoriesList.add("Motors");
        categoriesList.add("Electronics");
        categoriesList.add("Fashion");
        categoriesList.add("Toys");
        categoriesList.add("Sporting Goods");
        categoriesList.add("Deals");
        categoriesList.add("Other");
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

    public HashMap<String, Integer> getCardMap() { return cardMap; }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public ArrayList<String> getCategoriesList() { return categoriesList; }

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
