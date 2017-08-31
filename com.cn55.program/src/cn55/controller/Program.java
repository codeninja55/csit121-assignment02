package cn55.controller;

import cn55.controller.Validator.*;
import cn55.model.*;
import cn55.model.CardModel.AnonCard;
import cn55.model.CardModel.BasicCard;
import cn55.model.CardModel.PremiumCard;
import cn55.view.ButtonListener;
import cn55.view.CardView.*;
import cn55.view.CustomComponents.*;
import cn55.view.DeleteForm.DeleteCardForm;
import cn55.view.DeleteForm.DeleteEvent;
import cn55.view.DeleteForm.DeleteListener;
import cn55.view.MainFrame;
import cn55.view.PurchaseView.*;
import cn55.view.SearchForm.*;
import cn55.view.ToolbarButtonListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Program {

    private Database db;
    private MainFrame mainFrame;
    private Shop shop;
    private JTabbedPane tabPane;
    private CardPanel cardPanel;
    private CardsToolbar cardToolbar;
    private CardForm cardForm;
    private PurchasesPanel purchasePanel;
    private PurchaseToolbar purchaseToolbar;
    private PurchaseForm purchaseForm;

    Program() {
        shop = new Shop();
        db = shop.getDatabase();
        createTestCode(shop);
        db.mapCards();

        this.mainFrame = new MainFrame(shop.getDatabase().getPurchases(), shop.getDatabase().getCards());

        this.tabPane = mainFrame.getTabPane();

        this.cardPanel = mainFrame.getCardPanel();
        this.cardToolbar = cardPanel.getCardToolbar();

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

        shop.makePurchase("Cash", shop.generateReceiptID(), cat1);

        // AnonCard Test
        db.addCards(new AnonCard("111"));

        Map<String, Double> cat2 = new HashMap<>();
        cat2.put("Deals", 0D);
        cat2.put("Electronics", 200D);
        cat2.put("Fashion", 80D);
        cat2.put("Other", 0D);
        cat2.put("Toys", 100D);
        cat2.put("Motors", 0D);

        shop.makePurchase("111",shop.generateReceiptID(), cat2);

        db.addCards(new AnonCard("112"));

        Map<String, Double> cat8 = new HashMap<>();
        cat8.put("Deals", 100D);
        cat8.put("Electronics", 0D);
        cat8.put("Fashion", 80D);
        cat8.put("Other", 0D);
        cat8.put("Toys", 0D);
        cat8.put("Motors", 0D);

        shop.makePurchase("112",shop.generateReceiptID(), cat8);

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

        shop.makePurchase("69",shop.generateReceiptID(), cat3);

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

        shop.makePurchase("001",shop.generateReceiptID(), cat4);

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

        shop.makePurchase("75",shop.generateReceiptID(), cat5);

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

        shop.makePurchase("666",shop.generateReceiptID(), cat6);

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
                cardToolbar.disableToolbarButton("CreateButton",false);
                cardPanel.refresh(db.getCards());
            } else if (tabPane.getSelectedComponent() == purchasePanel) {
                purchaseToolbar.disableCreatePurchaseButton(false);
                purchasePanel.refresh(shop.getDatabase().getPurchases());
            }

            /* DESELECTED LISTENERS */
            if (tabPane.getSelectedComponent() != purchasePanel) {
                //IF PANEL CHANGED AWAY - REMOVE THE FORM
                if (purchaseForm.getCreatePurchaseForm() != null) {
                    removeCreatePurchaseForm();
                }
            } else if (tabPane.getSelectedComponent() != cardPanel) {

                cardPanel.getResultsPane().setVisible(false);
                // TODO - NOT WORKING FOR SOME REASON. RETURNING NullPointerException
                /*
                if (cardForm.getCreateCardForm() != null) {
                    removeCardForms();
                    reenableAllButtons(cardToolbar);
                }*/
            }
        });

        /* CARD VIEW TOOLBAR HANDLER - SEARCH BUTTON */
        cardPanel.setSearchCardListener(new ToolbarButtonListener() {
            public void toolbarButtonEventOccurred() {
                removeCardForms();
                reenableAllButtons(cardToolbar);
                cardPanel.setSearchForm(new SearchForm());
                cardPanel.add(cardPanel.getSearchForm(), BorderLayout.WEST);
                cardToolbar.disableToolbarButton("SearchButton", true);
                cardPanel.getSearchForm().setVisible(true);

                /* ADD a CANCEL BUTTON LISTENER AFTER CREATING FORM */
                cardPanel.getSearchForm().setCancelListener(new ButtonListener() {
                    public void buttonActionOccurred() {
                        cardPanel.getSearchForm().setVisible(false);
                        removeCardForms();
                        reenableAllButtons(cardToolbar);
                        cardPanel.getResultsPane().setVisible(false);
                    }
                });

                cardPanel.getSearchForm().setSearchListener(new SearchListener() {
                    public void searchEventOccurred(SearchEvent e) {
                        //JScrollPane resultsScrollPane = cardPanel.getResultsScrollPane();
                        JTextPane resultsPane = cardPanel.getResultsPane();
                        String cardID = e.getSearchIDTextField().getText();

                        /* SETUP VALIDATOR FOR CARD ID */
                        FormValidData input = new FormValidData();
                        input.setCardID(cardID);
                        FormRule cardIDRule = new CardIDRule();

                        if (!cardID.isEmpty() && shop.cardExists(cardID)) {
                            e.getErrorLabel().setVisible(false);
                            e.getRuleErrLabel().setVisible(false);

                            int cardIndex = db.getCardMap().get(cardID);
                            String cardText = db.getCards().get(cardIndex).toString();
                            StringBuilder purchaseText = new StringBuilder("");

                            for (Purchase purchase : db.getPurchases()) {
                                if (purchase.getCardID() != null) {
                                    if (purchase.getCardID().equals(cardID)) {
                                        purchaseText.append(purchase.toString());
                                    }
                                }
                            }

                            String results = String.format("%s%n%s%n%n%s%n%s","CARD FOUND",
                                    cardText,"PURCHASE(S)",purchaseText);

                            resultsPane.setText(results);
                            resultsPane.setVisible(true);
                            e.getSearchIDTextField().setText(null);
                        } else {
                            if (!cardIDRule.validate(input))
                                e.getRuleErrLabel().setVisible(true);
                            else
                                e.getErrorLabel().setVisible(true);

                            resultsPane.setText(null);
                            resultsPane.setVisible(false);
                            e.getSearchIDTextField().setForeground(Style.red900());
                            e.getSearchIDLabel().setForeground(Style.red900());
                        }
                    }
                });
            }
        });

        /* CARD VIEW TOOLBAR HANDLER - CREATE CARD BUTTON */
        cardPanel.setCreateCardListener(new ToolbarButtonListener() {
            public void toolbarButtonEventOccurred() {
                removeCardForms();
                reenableAllButtons(cardToolbar);
                cardToolbar.disableToolbarButton("CreateButton",true);
                cardPanel.setCardForm(new CardForm());
                cardPanel.add(cardPanel.getCardForm(), BorderLayout.WEST);
                cardPanel.getCardForm().createCardForm();
                cardPanel.getCardForm().setGeneratedCardID(shop.generateCardID());
                cardPanel.getCardForm().setVisible(true);

                /* ADD A CANCEL BUTTON LISTENER AFTER CREATING FORM */
                cardPanel.getCardForm().setCancelListener(new ButtonListener() {
                    public void buttonActionOccurred() {
                        cardPanel.getCardForm().setVisible(false);
                        removeCardForms();
                        reenableAllButtons(cardToolbar);
                    }
                });

                /* ADD A CREATE BUTTON LISTENER AFTER CREATING FORM */
                cardPanel.getCardForm().setCardListener(new CardListener() {
                    public void formActionOccurred() {
                        System.out.println("Create Card Pressed");
                    }
                });
            }
        });

        /* CARD VIEW TOOLBAR HANDLER - DELETE CARD BUTTON */
        cardPanel.setDeleteCardListener(new ToolbarButtonListener() {
            public void toolbarButtonEventOccurred() {
                removeCardForms();
                reenableAllButtons(cardToolbar);
                cardToolbar.disableToolbarButton("DeleteButton", true);
                cardPanel.setDeleteForm(new DeleteCardForm());
                cardPanel.add(cardPanel.getDeleteForm(), BorderLayout.WEST);
                cardPanel.getDeleteForm().setVisible(true);
                cardPanel.getResultsPane().setVisible(false);

                /* REGISTER A CANCEL BUTTON LISTENER AFTER CREATING FORM */
                cardPanel.getDeleteForm().setCancelListener(new ButtonListener() {
                    public void buttonActionOccurred() {
                        cardPanel.getDeleteForm().setVisible(false);
                        removeCardForms();
                        reenableAllButtons(cardToolbar);
                    }
                });

                /* REGISTER A CREATE BUTTON LISTENER AFTER CREATING FORM */
                cardPanel.getDeleteForm().setDeleteListener(new DeleteListener() {
                    public void deleteEventOccurred(DeleteEvent e) {
                        String cardID = e.getSearchIDTextField().getText();

                        /* SETUP VALIDATOR FOR CARD ID */
                        FormValidData input = new FormValidData();
                        input.setCardID(cardID);
                        FormRule rule = new CardIDRule();

                        if (!cardID.isEmpty() && shop.cardExists(cardID)) {
                            e.getErrorLabel().setVisible(false);
                            e.getRuleErrLabel().setVisible(false);
                            String[] btnOptions = {"Yes","Cancel"};
                            String message = "Are you sure you want to DELETE card: " + cardID +
                                    "\nThis cannot be undone." +
                                    "\n\nAll purchases for this card will be changed to CASH status.\n\n";

                            int confirm = JOptionPane.showOptionDialog(mainFrame, // frame, can be null
                                    message, // message
                                    "Confirm Delete?", // title
                                    JOptionPane.OK_CANCEL_OPTION, // button options
                                    JOptionPane.WARNING_MESSAGE, // icon
                                    null, // do not use custom icon
                                    btnOptions, // title of buttons
                                    btnOptions[1] // default button title
                            );

                            if (confirm == JOptionPane.OK_OPTION) {
                                shop.deleteCard(cardID);
                                cardPanel.refresh(db.getCards());
                                /* Purchase by this card will be changed to cash */
                                shop.convertPurchase(cardID);
                                purchasePanel.refresh(db.getPurchases());
                            } else {
                                e.getSearchIDTextField().setText(null);
                            }
                        } else {
                            if (!rule.validate(input))
                                e.getRuleErrLabel().setVisible(true);
                            else
                                e.getErrorLabel().setVisible(true);

                            e.getSearchIDTextField().setForeground(Style.red900());
                            e.getSearchIDLabel().setForeground(Style.red900());
                        }
                    }
                });
            }
        });

        /* PURCHASE VIEW TOOLBAR HANDLER - CREATE PURCHASE BUTTON */
        purchaseToolbar.setCreatePurchaseListener(new ToolbarButtonListener() {
            public void toolbarButtonEventOccurred() {
                purchaseToolbar.disableCreatePurchaseButton(true);
                purchaseForm.setGeneratedReceiptID(shop.generateReceiptID());
                purchaseForm.setCardModel(shop.getDatabase().getCardModel());
                purchaseForm.setCategoriesList(shop.getDatabase().getCategoriesList());
                purchaseForm.createPurchaseForm();
                purchaseForm.setVisible(true);
            }
        });

        /* PURCHASE FORM CANCEL HANDLER */
        purchaseForm.setCancelPurchaseListener(new ButtonListener() {
            public void buttonActionOccurred() {
                removeCreatePurchaseForm();
            }
        });

        /* PURCHASE FORM CREATE HANDLER */
        purchaseForm.setCreatePurchaseListener(new PurchaseListener() {
            public void formActionOccurred(PurchaseEvent event) {
                JComboBox<String> type = event.getPurchaseTypeCombo();

                String receiptIDStr = event.getReceiptIDTextField().getText();
                int receiptID = Integer.parseInt(receiptIDStr);

                /* SETUP VALID DATA OBJECT */
                FormValidData input = new FormValidData();

                String cardType = "";
                String name = "";
                String email = "";
                if (event.getAnonCardRB().isSelected()) {
                    cardType = CardType.AnonCard.getName();
                } else if (event.getBasicCardRB().isSelected()) {
                    cardType = CardType.BasicCard.getName();
                    name = event.getCardNameTextField().getText();
                    email = event.getCardEmailTextField().getText();
                } else if (event.getPremiumCardRB().isSelected()) {
                    cardType = CardType.PremiumCard.getName();
                    name = event.getCardNameTextField().getText();
                    email = event.getCardEmailTextField().getText();
                }

                /* CATEGORIES */
                // TODO - Validation required
                /* SETUP VALIDATOR FOR CATEGORY AMOUNT */
                FormRule catAmountRule = new CategoryAmountRule();
                HashMap<String, Double> categories = new HashMap<>();

                for (HashMap.Entry<FormLabel, FormTextField> item : event.getCategoriesMap().entrySet()) {
                    String labelStr = item.getKey().getText();
                    String catName = labelStr.substring(0, labelStr.indexOf(":"));

                    Double catValue = 0D;
                    String textFieldStr = item.getValue().getText();
                    input.setCatValueStr(textFieldStr);

                    /*if (catAmountRule.validate(input)) {
                        if (!(textFieldStr.isEmpty())) {
                            catValue = Double.parseDouble(textFieldStr);
                        }
                    } else {

                    }*/


                    categories.put(catName, catValue);
                }


                // TODO - Validation required if existing card used
                // TODO - Validation required if new card created
                String cardID;

                if (type.getSelectedItem() != null) {
                    if (type.getSelectedItem().equals(PurchaseType.ExistingCardPurchase.getName())) {
                        cardID = (String)event.getExistingCardCombo().getSelectedItem();

                        shop.makePurchase(cardID, receiptID, categories);
                        purchasePanel.refresh(db.getPurchases());
                        removeCreatePurchaseForm();
                    } else if (type.getSelectedItem().equals(PurchaseType.NewCardPurchase.getName())) {
                        cardID = event.getCardIDTextField().getText();

                        /* VALIDATE CARD ID */
                        FormRule cardIDRule = new CardIDRule();
                        input.setCardID(cardID);

                        if (!cardIDRule.validate(input)) {
                            event.getCardIDTextField().setForeground(Style.red900());
                            event.getCardIDLabel().setForeground(Style.red900());
                            event.getCardIDErrorLabel().setVisible(true);
                        } else {
                            HashMap<String, String> newCard = new HashMap<>();
                            newCard.put("name", name);
                            newCard.put("email",email);
                            newCard.put("cardID", cardID);
                            newCard.put("cardType",cardType);

                            shop.makeCard(newCard);
                            cardPanel.refresh(db.getCards());
                            shop.makePurchase(cardID, receiptID, categories);
                            purchasePanel.refresh(db.getPurchases());
                            removeCreatePurchaseForm();
                        }

                    } else if (type.getSelectedItem().equals(PurchaseType.CashPurchase.getName())) {
                        cardID = CardType.Cash.getName();

                        shop.makePurchase(cardID, receiptID, categories);
                        purchasePanel.refresh(db.getPurchases());
                        removeCreatePurchaseForm();
                    }
                }

            }
        });

    }

    private void removeCardForms() {
        if (cardPanel.getComponents().length >= 4) {
            cardPanel.remove(3);
        }
    }

    private void reenableAllButtons(JPanel toolbar) {
        for (Component btn : toolbar.getComponents()) {
            if (!btn.isEnabled()) {
                btn.setEnabled(true);
            }
        }
    }

    private void removeCreatePurchaseForm() {
        /* REMOVE THE FORM FROM PURCHASE */
        purchaseForm.getPurchaseTypeCombo().setSelectedIndex(0);
        purchaseForm.setVisible(false);
        purchaseForm.remove(purchaseForm.getCreatePurchaseForm());
        purchasePanel.getPurchaseToolbar().disableCreatePurchaseButton(false);
    }
}
