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

    // TODO - Can store these in a HashSet / TreeSet because no two can be same
    private ArrayList<Category> categories;
    private HashMap<Integer, Integer> categoriesMap;

    // TODO - Fix this problem... if u use a static method for form, this counter will go up
    //private static int cardIDCounter = 10000;

    static HashMap<Integer, Double> categoriesTotalMap = new HashMap<>();
    private static Set<Integer> receiptSet = new HashSet<>();
    private static Set<String> cardIDSet = new HashSet<>();

    /*============================== CONSTRUCTORS  ==============================*/
    Database() {
        this.cards = new ArrayList<>();
        this.cardMap = new HashMap<>();
        this.purchases = new ArrayList<>();
        this.purchaseMap = new HashMap<>();
        this.categories = new ArrayList<>();
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
   /* public static String generateCardID() {
        String cardID = "MC" + (++cardIDCounter);

        if (cardIDSet.contains(cardID)) {
            return generateCardID();
        } else {
            return cardID;
        }
    }*/

    /*public static int generateCategoryIDCounter() {
        if (getCategoriesTotalMap().size() > 0 && )
    }*/

    private static void addReceiptID(int receiptID) { Database.receiptSet.add(receiptID); }

    public static void addCardIDSet(String cardID) { Database.cardIDSet.add(cardID); }

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

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void addCards(Card card) {
        this.cards.add(card);
        mapCards();
    }

    void addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
        mapPurchases();
    }

    public void removeCategory(int index) {
        categories.remove(index);
        mapCategories();
    }

    /*============================== ACCESSORS  ==============================*/
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
