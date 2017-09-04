package cn55.model;

/* THIS CLASS IS A TEMP DATABASE OBJECT */

import cn55.model.CardModel.Card;

import javax.swing.*;
import java.util.*;

/* SINGLETON DESIGN PATTERN */

public class Database implements Subject {

    private static Database db;
    private ArrayList<Observer> observers;
    private ArrayList<Card> cards;
    private HashMap<String, Integer> cardMap;
    private ArrayList<Purchase> purchases;
    private HashMap<String, Integer> purchaseMap;

    private ArrayList<Category> categories;
    private HashMap<Integer, Integer> categoriesMap;

    private static int cardIDCounter = 10000;
    private static int categoryIDCounter = 100;

    static HashMap<Integer, Double> categoriesTotalMap = new HashMap<>();
    private static Set<Integer> receiptSet = new HashSet<>();

    /*============================== CONSTRUCTORS  ==============================*/
    // Private modifier prevents any other class from instantiating
    private Database() {
        this.observers = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.cardMap = new HashMap<>();
        this.purchases = new ArrayList<>();
        this.purchaseMap = new HashMap<>();
        this.categories = new ArrayList<>();
        this.categoriesMap = new HashMap<>();
    }

    // Provide global point of access
    // Double check locking mechanism but only with the initial call
    public static synchronized Database getDBInstance() {
        if (db == null)
            db = new Database();

        return db;
    }

    /*============================== STATIC METHODS ==============================*/
    public static int generateReceiptID() {
        Random randomObj = new Random();

        int receiptID;
        receiptID = randomObj.ints(10000000,99999999).findFirst().getAsInt();

        if (receiptSet.contains(receiptID)) {
            return generateReceiptID();
        } else {
            addReceiptID(receiptID);
            return receiptID;
        }
    }

    public static String generateCardID() {
        return "MC" + (++cardIDCounter);
    }

    public static int generateCategoryID() {
        return categoryIDCounter++;
    }

    private static void addReceiptID(int receiptID) { Database.receiptSet.add(receiptID); }

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

    void mapCategories() {
        HashMap<Integer, Integer> categoriesMap = new HashMap<>();
        for (int index = 0 ; index < categories.size() ; index++)
            categoriesMap.put(categories.get(index).getId(), index);

        this.categoriesMap = categoriesMap;
    }

    static void mapCategoriesTotalMap(ArrayList<Category> categories) {
        if (categoriesTotalMap.size() == 0) {
            for (Category item : categories) {
                categoriesTotalMap.put(item.getId(), 0D);
            }
        }
    }

    static void updateCategoriesTotalMap(HashMap<Integer, Category> categories) {
        for (HashMap.Entry<Integer, Category> item : categories.entrySet()) {
            Double newTotal = Database.categoriesTotalMap.get(item.getKey()) + item.getValue().getAmount();
            Database.categoriesTotalMap.put(item.getKey(), newTotal);
        }
    }

    void addCategory(Category category) {
        categories.add(category);
        mapCategories();
        notifyObservers();
    }

    public void addCards(Card card) {
        this.cards.add(card);
        mapCards();
        notifyObservers();
    }

    void addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
        mapPurchases();
        notifyObservers();
    }

    public void removeCard(int index) {
        cards.remove(index);
        mapCards();
        notifyObservers();
    }

    public void removeCategory(int index) {
        categories.remove(index);
        mapCategories();
        notifyObservers();
    }

    /*============================== ACCESSORS  ==============================*/
    public static String getNextCardID() {
        return "MC" + (cardIDCounter + 1);
    }

    public static int getNextCategoryID() {
        return categoryIDCounter;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public HashMap<String, Integer> getCardMap() { return cardMap; }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public ArrayList<Category> getCategories() { return categories; }

    public HashMap<Integer, Integer> getCategoriesMap() {
        return categoriesMap;
    }

    public static HashMap<Integer, Double> getCategoriesTotalMap() {
        return categoriesTotalMap;
    }

    public DefaultComboBoxModel<String> getCardModel() {
        DefaultComboBoxModel<String> cardModel = new DefaultComboBoxModel<>();
        cardModel.addElement("Please Select");
        for (Card card : cards) cardModel.addElement(card.getID());
        return cardModel;
    }

    /*============================== OBSERVER DESIGN PATTERN ==============================*/
    /* Implement Subject interface making this object instance a Subject */
    @Override
    public void register(Observer obj) {
        observers.add(obj);
    }

    @Override
    public void unregister(Observer obj) {
        observers.remove(obj);
    }

    @Override
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update();
        }
    }

    @Override
    public ArrayList<Card> getCardsUpdate(Observer who) {
        return cards;
    }

    @Override
    public ArrayList<Purchase> getPurchaseUpdate(Observer who) {
        return purchases;
    }

    @Override
    public ArrayList<Category> getCategoriesUpdate(Observer who) {
        return categories;
    }
}
