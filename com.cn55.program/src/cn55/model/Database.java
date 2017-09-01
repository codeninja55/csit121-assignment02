package cn55.model;

/* THIS CLASS IS A TEMP DATABASE OBJECT */

import cn55.model.CardModel.Card;

import javax.swing.*;
import java.util.*;

public class Database {

    private ArrayList<Card> cards;
    private HashMap<String, Integer> cardMap;
    private ArrayList<Purchase> purchases;
    private HashMap<String, Integer> purchaseMap;
    private Map<String, Double> categories;
    private ArrayList<String> categoriesList;
    private static int idCounter = 10000;
    private static Set<Integer> receiptSet = new HashSet<>();
    private static Set<String> cardIDSet = new HashSet<>();

    /*============================== CONSTRUCTORS  ==============================*/
    Database() {
        this.cards = new ArrayList<>();
        this.cardMap = new HashMap<>();
        this.purchases = new ArrayList<>();
        this.categories = new HashMap<>();
        createCategoriesList();
    }

    /*============================== MUTATORS  ==============================*/
    public void mapCards() {
        HashMap<String, Integer> cardMap = new HashMap<>();
        for (int index = 0 ; index < cards.size() ; index++) cardMap.put(cards.get(index).getID(), index);

        this.cardMap = cardMap;
    }

    void mapPurchases() {
        HashMap<String, Integer> purchaseMap = new HashMap<>();
        for (int index = 0 ; index < purchases.size() ; index++)
            purchaseMap.put(purchases.get(index).getCardID(), index);

        this.purchaseMap = purchaseMap;
    }

    public static int generateReceiptID() {
        Random randomObj = new Random();

        int receiptID;
        receiptID = randomObj.ints(10000000,99999999).findFirst().getAsInt();

        if (receiptSet.contains(receiptID)) {
            return generateReceiptID();
        } else {
            receiptSet.add(receiptID);
            return receiptID;
        }
    }

    public static String generateCardID() {
        String cardID = "MC" + (++idCounter);

        if (cardIDSet.contains(cardID)) {
            return generateCardID();
        } else {
            return cardID;
        }
    }

    public void addCards(Card card) {
        this.cards.add(card);
        mapCards();
    }

    void addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
        mapPurchases();
    }

    public static void addReceiptID(int receiptID) { Database.receiptSet.add(receiptID); }

    public static void addCardIDSet(String cardID) { Database.cardIDSet.add(cardID); }

    private void createCategoriesList() {
        this.categoriesList = new ArrayList<>();

        categoriesList.add("Motors");
        categoriesList.add("Electronics");
        categoriesList.add("Fashion");
        categoriesList.add("Toys");
        categoriesList.add("Deals");
        categoriesList.add("Other");
    }

    public void addCategory(String category, Double value) {
        this.categories.put(category, value);
    }

    /*============================== ACCESSORS  ==============================*/
    public ArrayList<Card> getCards() {
        return cards;
    }

    public HashMap<String, Integer> getCardMap() { return cardMap; }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public HashMap<String, Integer> getPurchaseMap() {
        return purchaseMap;
    }

    public ArrayList<String> getCategoriesList() { return categoriesList; }

    public Map<String, Double> getCategories() {
        return categories;
    }

    Set<Integer> getReceiptSet() {
        return receiptSet;
    }

    Set<String> getCardIDSet() {
        return cardIDSet;
    }

    public DefaultComboBoxModel<String> getCardModel() {
        DefaultComboBoxModel<String> cardModel = new DefaultComboBoxModel<>();
        cardModel.addElement("Please Select");
        for (Card card : cards) cardModel.addElement(card.getID());
        return cardModel;
    }
}
