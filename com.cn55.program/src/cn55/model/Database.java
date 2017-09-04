package cn55.model;

/* THIS CLASS IS A TEMP DATABASE OBJECT */

import cn55.model.CardModel.Card;

import javax.swing.*;
import java.util.*;

/* SINGLETON DESIGN PATTERN */

public class Database {

    private static Database db;
    private ArrayList<Card> cards;
    private HashMap<String, Integer> cardMap;
    private ArrayList<Purchase> purchases;
    private HashMap<String, Integer> purchaseMap;

    // TODO - Can store these in a HashSet / TreeSet because no two can be same
    private ArrayList<Category> categories;
    private HashMap<Integer, Integer> categoriesMap;

    // TODO - Fix this problem... if u use a static method for form, this counter will go up
    private static int cardIDCounter = 10000;
    private static int categoryIDCounter = 100;

    static HashMap<Integer, Double> categoriesTotalMap = new HashMap<>();
    private static Set<Integer> receiptSet = new HashSet<>();

    /*============================== CONSTRUCTORS  ==============================*/
    // Private modifier prevents any other class from instantiating
    private Database() {
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

    /* SEE ABOVE FOR COMMENT ABOUT cardIDCounter */
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
    }

    public void addCards(Card card) {
        this.cards.add(card);
        mapCards();
    }

    void addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
        mapPurchases();
    }

    public void removeCard(int index) {
        cards.remove(index);
        mapCards();
    }

    public void removeCategory(int index) {
        categories.remove(index);
        mapCategories();
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
}
