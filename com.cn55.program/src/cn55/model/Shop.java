package cn55.model;

import cn55.model.CardModel.*;

import java.util.*;

@SuppressWarnings("ConstantConditions")
public class Shop {

    private Database db;

    /*============================== CONSTRUCTORS  ==============================*/

    public Shop() {
        this.db = Database.getDBInstance();
        generateDefaultCategories();
        Database.mapCategoriesTotalMap(db.getCategories());
    }

    /*============================== MUTATORS  ==============================*/
    private void generateDefaultCategories() {
        ArrayList<Category> categories = db.getCategories();
        categories.add(new Category("Other", "#Description"));
        categories.add(new Category("Motors", "#Description"));
        categories.add(new Category("Electronics", "#Description"));
        categories.add(new Category("Fashion", "#Description"));
        categories.add(new Category("Toys", "#Description"));
        categories.add(new Category("Deals", "#Description"));

        categories.sort(new CategoriesComparator());
    }

    public void makeCategory(Category category) {
        db.addCategory(category);
    }

    public void makePurchase(String cardID, int receiptID, HashMap<Integer, Category> categories) {

        if (cardID.equals(CardType.Cash.getName())) {
            updateCategoriesTotalMap(categories);
            db.addPurchase(new Purchase(categories, receiptID));
        } else {
            if (cardExists(cardID)) {
                updateCategoriesTotalMap(categories);
                Card card = db.getCards().get(db.getCardMap().get(cardID));
                String cardType = card.getCardType();
                Purchase newPurchase = new Purchase(cardID, cardType, categories, receiptID);
                card.calcPoints(newPurchase.getCategoriesTotal());

                if(!cardType.equals(CardType.AnonCard.getName()))
                    card.calcBalance(newPurchase.getCategoriesTotal());

                db.addPurchase(newPurchase);
            }
        }
    }

    public void makeCard(HashMap<String, String> newCard) {

        String name = newCard.get("name");
        String email = newCard.get("email");
        String cardType = newCard.get("cardType");
        Card card;

        if (!cardType.equals(CardType.AnonCard.getName())) {
            if (cardType.equals(CardType.BasicCard.getName()))
                card = new BasicCard(name, email);
            else
                card = new PremiumCard(name, email);

            db.addCards(card);
        } else {
            card = new AnonCard();
            db.addCards(card);
        }
    }

    public boolean cardExists(String cardID) {
        db.mapCards();
        HashMap<String, Integer> cardMap = db.getCardMap();
        return cardMap.containsKey(cardID);
    }

    public void deleteCard(String cardID) {
        db.mapCards();
        int index = db.getCardMap().get(cardID);
        if (db.getCardMap().containsKey(cardID)) {
            try {
                db.getCards().remove(index);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    public void convertPurchase(String cardID) {
        db.mapPurchases();
        for (Purchase purchase : db.getPurchases()) {
            if (purchase.getCardID() != null && purchase.getCardID().equals(cardID)) {
                purchase.setCardID(null);
                purchase.setCardType(CardType.Cash.getName());
            }
        }
    }

    public void deleteCategory(int categoryID) {
        db.mapCategories();
        // MOVE AMOUNT TO OTHERS FOR EACH PURCHASE
        for (Purchase purchase : db.getPurchases()) {
            // Get the Others Category
            Category other = purchase.getCategories().get(100);
            Category deletedCat = purchase.getCategories().get(categoryID);
            other.setAmount(other.getAmount() + deletedCat.getAmount());
            purchase.getCategories().remove(categoryID);
        }

        Double newValue = Database.getCategoriesTotalMap().get(100) + Database.getCategoriesTotalMap().get(categoryID);
        Database.getCategoriesTotalMap().replace(100, newValue);
        Database.getCategoriesTotalMap().remove(categoryID);

        db.removeCategory(db.getCategoriesMap().get(categoryID));
    }

    /*============================== ACCESSORS ==============================*/
    public Database getDatabase() { return db; }

}
