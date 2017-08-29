package cn55.controller;

import cn55.model.*;
import cn55.view.*;
import cn55.model.CardModel.*;
import cn55.view.CardView.*;
import cn55.view.PurchaseView.*;
import cn55.view.SearchPanel.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Program {

    private Database db;
    private MainFrame mainFrame;
    private Shop shop;

    /* ASK MARK SIFER ABOUT THIS */
    private JTabbedPane tabPane;
    private CardPanel cardPanel;
    private PurchasesPanel purchasePanel;
    private PurchaseForm purchaseForm;
    private PurchaseToolbar purchaseToolbar;

    Program() {

        shop = new Shop();
        db = shop.getDatabase();
        createTestCode(shop);
        db.mapCards();

        this.mainFrame = new MainFrame(new ArrayList<>(shop.getDatabase().getPurchases()),
                new ArrayList<>(shop.getDatabase().getCards()));

        this.tabPane = mainFrame.getTabPane();

        this.cardPanel = mainFrame.getCardPanel();

        this.purchasePanel = mainFrame.getPurchasePanel();
        this.purchaseForm = purchasePanel.getPurchaseForm();
        this.purchaseToolbar = purchasePanel.getPurchaseToolbar();

        eventControlling();
    }

    private void createTestCode(Shop shop) {
        /*########## TESTING CODE ##########*/

        // Cash purchase test
        Map<String, Double> cat1 = new HashMap<>();
        cat1.put("Toys", 0D);
        cat1.put("Other", 800D);
        cat1.put("Electronics", 0D);
        cat1.put("Motors", 100D);
        cat1.put("Fashion", 0D);
        cat1.put("Deals", 500D);

        shop.makePurchase("cash", cat1);

        // AnonCard Test
        db.addCards(new AnonCard("111"));

        Map<String, Double> cat2 = new HashMap<>();
        cat2.put("Deals", 0D);
        cat2.put("Electronics", 200D);
        cat2.put("Fashion", 80D);
        cat2.put("Other", 0D);
        cat2.put("Toys", 100D);
        cat2.put("Motors", 0D);

        shop.makePurchase("111", cat2);

        db.addCards(new AnonCard("112"));

        Map<String, Double> cat8 = new HashMap<>();
        cat8.put("Deals", 100D);
        cat8.put("Electronics", 0D);
        cat8.put("Fashion", 80D);
        cat8.put("Other", 0D);
        cat8.put("Toys", 0D);
        cat8.put("Motors", 0D);

        shop.makePurchase("112", cat8);

        // BasicCard Test
        db.addCards(new BasicCard("69", "Natasha Romanov",
                "blackwidow@avengers.team", 0));

        Map<String, Double> cat3 = new HashMap<>();
        cat3.put("Electronics", 3000D);
        cat3.put("Fashion", 5000D);
        cat3.put("Other", 500D);
        cat3.put("Motors", 0D);
        cat3.put("Toys", 1000D);
        cat3.put("Deals", 2000D);

        shop.makePurchase("69", cat3);

        // BasicCard Test 2
        db.addCards(new BasicCard("001", "Steve Rogers",
                "captain_a@avengers.team",0D));

        Map<String, Double> cat4 = new HashMap<>();
        cat4.put("Electronics", 100D);
        cat4.put("Fashion", 0D);
        cat4.put("Other", 500D);
        cat4.put("Motors", 0D);
        cat4.put("Toys", 100D);
        cat4.put("Deals", 2000D);

        shop.makePurchase("001", cat4);

        // PremiumCard Test
        db.addCards(new PremiumCard("75", "Tony Stark",
                "ironman@avengers.team",0));

        Map<String, Double> cat5 = new HashMap<>();
        cat5.put("Electronics", 1000000D);
        cat5.put("Deals", 500000D);
        cat5.put("Toys", 300D);
        cat5.put("Motors", 10000D);
        cat5.put("Other", 500D);
        cat5.put("Fashion", 2000D);

        shop.makePurchase("75", cat5);

        // PremiumCard Test 2
        db.addCards(new PremiumCard("666", "Nick Fury",
                "nick@shield.com",0));

        Map<String, Double> cat6 = new HashMap<>();
        cat6.put("Electronics", 10000D);
        cat6.put("Deals", 0D);
        cat6.put("Toys", 300D);
        cat6.put("Motors", 1000000D);
        cat6.put("Other", 500D);
        cat6.put("Fashion", 2000D);

        shop.makePurchase("666", cat6);

        db.addCards(new AnonCard("113"));
        db.addCards(new AnonCard("114"));
        db.getCards().get(7).calcPoints(300D);

        db.addCards(new BasicCard("444","Hank Pym","ants@avengers.team",0));
        db.addCards(new BasicCard("88","Peter Parker", "spidey@avengers.team",0));
        db.addCards(new PremiumCard("5000","Danny Rand","danny@randcorp.com",5000));
        db.addCards(new BasicCard("33","Matthew Murdock","thedevil@hellskitchen.com", 666));
        db.addCards(new BasicCard("9000", "Thor Odinson", "thor@asgard.com",9000));
    }

    /*==================== HANDLE EVENTS ====================*/
    private void eventControlling() {
        /* TAB PANE LISTENER */
        tabPane.addChangeListener(e -> {

            /* SELECTED LISTENERS */
            if (tabPane.getSelectedComponent() == cardPanel) {
                cardPanel.refresh();
                purchaseForm.setVisible(false);

            } else if (tabPane.getSelectedComponent() == purchasePanel) {
                purchaseToolbar.disableCreatePurchaseButton(false);
                purchasePanel.refresh();
            }

            /* DESELECTED LISTENERS */
            /*if (tabPane.getSelectedComponent() != purchasePanel) {
                *//* IF PANEL CHANGED AWAY - REMOVE THE FORM *//*
                purchaseForm.getPurchaseTypeCombo().setSelectedIndex(0);
                purchaseForm.setVisible(false);
                purchaseForm.remove(purchaseForm.getCreatePurchaseFormPanel());
            }*/
        });

        /* TOOLBAR LISTENERS */
        cardPanel.setCreateCardListener(new CardListener() {
            public void formActionOccurred() {
                CardForm createCardForm = new CardForm(shop.generateCardID());
                HashMap<String,String> newCard = createCardForm.getCardMap();
                shop.makeCard(newCard);
                mainFrame.getCardPanel().refresh();
            }
        });

        cardPanel.setDeleteCardListener(new CardListener() {
            public void formActionOccurred() {
                CardForm deleteCardForm = new CardForm();

                if (!shop.cardExists(deleteCardForm.getCardID())) {
                    System.out.println("MainFrame");
                    System.out.println(deleteCardForm.getCardID());
                    deleteCardForm.deleteForm(true);
                } else {
                    System.out.println("MainFrame");
                    System.out.println("Deleting Card: " + deleteCardForm.getCardID());
                    shop.deleteCard(deleteCardForm.getCardID());
                    cardPanel.setCardData(shop.getDatabase().getCards());
                    cardPanel.refresh();
                }
            }
        });

        cardPanel.setSearchListener(new SearchListener() {
            public void searchEventOccurred(SearchEvent e) {
                String cardID = e.getSearchID();

                if (!cardID.isEmpty() && shop.cardExists(cardID)) {
                    int cardIndex = db.getCardMap().get(cardID);
                    String cardText = db.getCards().get(cardIndex).toString();
                    //String cardText = String.format("");

                    JOptionPane.showMessageDialog(
                            null,
                            cardText,
                            "Card",
                            JOptionPane.INFORMATION_MESSAGE);
                    // UNCOMMENT TO USE TEXTAREA  cardPanel.appendCardTextArea(cardText);
                } else if (!shop.cardExists(cardID)) {
                    JOptionPane.showMessageDialog(null,
                            "Card Does Not Exist",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /* LISTENER FOR ADD PURCHASE BUTTON ON PURCHASE PANEL TOOLBAR */
        purchaseToolbar.setCreatePurchaseListener(new ToolbarButtonListener() {
            public void toolbarButtonEventOccurred() {
                purchaseToolbar.disableCreatePurchaseButton(true);

                purchaseForm.setGeneratedReceiptID(shop.generateReceiptID());
                purchaseForm.setCardModel(shop.getDatabase().getCardModel());
                purchaseForm.setCategoriesList(shop.getDatabase().getCategoriesList());
                purchaseForm.setGeneratedCardID(shop.generateCardID());

                purchaseForm.createPurchaseFormPanel();
                purchaseForm.setVisible(true);
            }
        });

        /* LISTENER FOR CANCEL BUTTON IN PURCHASE FORM */
        purchaseForm.setCancelPurchaseListener(new ButtonListener() {
            public void buttonActionOccurred() {
                purchaseForm.getPurchaseTypeCombo().setSelectedIndex(0);
                purchaseForm.setVisible(false);
                purchaseForm.remove(purchaseForm.getCreatePurchaseFormPanel());

                purchasePanel.getPurchaseToolbar().disableCreatePurchaseButton(false);
            }
        });
    }
}
