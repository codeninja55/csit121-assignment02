package cn55.controller;

import cn55.controller.Validator.*;
import cn55.model.CardModel.*;
import cn55.model.*;
import cn55.view.*;
import cn55.view.CardView.*;
import cn55.view.CustomComponents.*;
import cn55.view.DeleteForm.*;
import cn55.view.MainFrame;
import cn55.view.PurchaseView.*;
import cn55.view.SearchForm.*;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Program {

    private Database db;
    private MainFrame mainFrame;
    private Shop shop;
    private JTabbedPane tabPane;
    private CardViewPanel cardViewPanel;
    private CardsToolbar cardToolbar;
    private CardForm cardForm;
    private PurchaseViewPanel purchaseViewPanel;
    private PurchaseToolbar purchaseToolbar;
    private PurchaseForm purchaseForm;

    Program() {
        shop = new Shop();
        db = shop.getDatabase();
        createTestCode(shop);
        db.mapCards();

        this.mainFrame = new MainFrame(shop.getDatabase().getPurchases(), shop.getDatabase().getCards());

        this.tabPane = mainFrame.getTabPane();

        this.cardViewPanel = mainFrame.getCardViewPanel();
        this.cardToolbar = cardViewPanel.getCardToolbar();

        this.purchaseViewPanel = mainFrame.getPurchasePanel();
        this.purchaseForm = purchaseViewPanel.getPurchaseForm();
        this.purchaseToolbar = purchaseViewPanel.getPurchaseToolbar();

        for (Component c : getAllComponents(mainFrame)) {
            if (c instanceof JButton) {
                System.out.println(c);
            }
        }

        eventControlling();
    }

    private ArrayList<Component> getAllComponents(final Container container) {
        /* REFERENCE: http://www.java2s.com/Code/Java/Swing-JFC/GetAllComponentsinacontainer.html */
        Component[] comps = container.getComponents();
        ArrayList<Component> compList = new ArrayList<>();

        for (Component c : comps) {
            compList.add(c);
            if (c instanceof Container) {
                compList.addAll(getAllComponents((Container) c));
            }
        }
        return compList;
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

        shop.makePurchase("Cash", db.generateReceiptID(), cat1);

        Map<String, Double> cat7 = new HashMap<>();
        cat7.put("Toys", 300D);
        cat7.put("Other", 100D);
        cat7.put("Electronics", 0D);
        cat7.put("Motors", 0D);
        cat7.put("Fashion", 20D);
        cat7.put("Deals", 50D);

        shop.makePurchase("Cash", db.generateReceiptID(), cat7);

        Map<String, Double> cat8 = new HashMap<>();
        cat8.put("Toys", 100D);
        cat8.put("Other", 1000D);
        cat8.put("Electronics", 100D);
        cat8.put("Motors", 100D);
        cat8.put("Fashion", 0D);
        cat8.put("Deals", 50D);

        shop.makePurchase("Cash", db.generateReceiptID(), cat8);

        // AnonCard Test
        db.addCards(new AnonCard()); // MC10001

        Map<String, Double> cat2 = new HashMap<>();
        cat2.put("Deals", 0D);
        cat2.put("Electronics", 200D);
        cat2.put("Fashion", 80D);
        cat2.put("Other", 0D);
        cat2.put("Toys", 100D);
        cat2.put("Motors", 0D);

        shop.makePurchase("MC10002",db.generateReceiptID(), cat2);

        db.addCards(new AnonCard()); // MC10002

        Map<String, Double> cat9 = new HashMap<>();
        cat9.put("Deals", 100D);
        cat9.put("Electronics", 0D);
        cat9.put("Fashion", 80D);
        cat9.put("Other", 0D);
        cat9.put("Toys", 0D);
        cat9.put("Motors", 0D);

        shop.makePurchase("MC10002",db.generateReceiptID(), cat9);

        // BasicCard Test
        db.addCards(new BasicCard("Natasha Romanov",
                "blackwidow@avengers.team", 0));

        Map<String, Double> cat3 = new HashMap<>();
        cat3.put("Electronics", 3000D);
        cat3.put("Fashion", 5000D);
        cat3.put("Other", 500D);
        cat3.put("Motors", 0D);
        cat3.put("Toys", 1000D);
        cat3.put("Deals", 2000D);

        shop.makePurchase("MC10003",db.generateReceiptID(), cat3);

        // BasicCard Test 2
        db.addCards(new BasicCard("Steve Rogers",
                "captain_a@avengers.team",0D));

        Map<String, Double> cat4 = new HashMap<>();
        cat4.put("Electronics", 100D);
        cat4.put("Fashion", 0D);
        cat4.put("Other", 500D);
        cat4.put("Motors", 0D);
        cat4.put("Toys", 100D);
        cat4.put("Deals", 2000D);

        shop.makePurchase("MC10004",db.generateReceiptID(), cat4);

        // PremiumCard Test
        db.addCards(new PremiumCard("Tony Stark",
                "ironman@avengers.team",0));

        Map<String, Double> cat5 = new HashMap<>();
        cat5.put("Electronics", 1000000D);
        cat5.put("Deals", 500000D);
        cat5.put("Toys", 300D);
        cat5.put("Motors", 10000D);
        cat5.put("Other", 500D);
        cat5.put("Fashion", 2000D);

        shop.makePurchase("MC10005",db.generateReceiptID(), cat5);

        // PremiumCard Test 2
        db.addCards(new PremiumCard("Nick Fury",
                "nick@shield.com",0));

        Map<String, Double> cat6 = new HashMap<>();
        cat6.put("Electronics", 10000D);
        cat6.put("Deals", 0D);
        cat6.put("Toys", 300D);
        cat6.put("Motors", 1000000D);
        cat6.put("Other", 500D);
        cat6.put("Fashion", 2000D);

        shop.makePurchase("MC10006",db.generateReceiptID(), cat6);

        db.addCards(new AnonCard()); //MC10007
        db.addCards(new AnonCard()); //MC10008
        db.getCards().get(7).calcPoints(300D);

        db.addCards(new BasicCard("Hank Pym","ants@avengers.team",0));
        db.addCards(new BasicCard("Peter Parker", "spidey@avengers.team",0));
        db.addCards(new PremiumCard("Danny Rand","danny@randcorp.com",5000));
        db.addCards(new BasicCard("Matthew Murdock","thedevil@hellskitchen.com", 666));
        db.addCards(new BasicCard("Thor Odinson", "thor@asgard.com",9000));

        Map<String, Double> cat10 = new HashMap<>();
        cat10.put("Toys", 0D);
        cat10.put("Other", 0D);
        cat10.put("Electronics", 100D);
        cat10.put("Motors", 0D);
        cat10.put("Fashion", 0D);
        cat10.put("Deals", 0D);

        shop.makePurchase("Cash", db.generateReceiptID(), cat10);

        Map<String, Double> cat11 = new HashMap<>();
        cat11.put("Toys", 0D);
        cat11.put("Other", 0D);
        cat11.put("Electronics", 0D);
        cat11.put("Motors", 0D);
        cat11.put("Fashion", 0D);
        cat11.put("Deals", 50D);

        shop.makePurchase("Cash", db.generateReceiptID(), cat11);
    }

    /*============================== REGISTER AND HANDLE EVENTS ==============================*/
    private void eventControlling() {
        /* TAB PANE LISTENER */
        tabPane.addChangeListener(e -> {

            /* SELECTED LISTENERS */
            if (tabPane.getSelectedComponent() == cardViewPanel) {
                cardViewPanel.refresh(db.getCards());
            } else if (tabPane.getSelectedComponent() == purchaseViewPanel) {
                purchaseToolbar.disableCreatePurchaseButton(false);
                purchaseViewPanel.refresh(shop.getDatabase().getPurchases());
            }

            /* DESELECTED LISTENERS */
            /*if (tabPane.getSelectedComponent() != purchaseViewPanel) {
                if (purchaseForm.getCreatePurchaseForm() != null) {
                    removeCreatePurchaseForm();
                    purchaseViewPanel.getResultsPane().setVisible(false);
                }
            } else if (tabPane.getSelectedComponent() != cardViewPanel) {
                cardViewPanel.getResultsPane().setVisible(false);
                if (cardForm.getCreateCardForm() != null) {
                    removeCardForms();
                }
            }*/
        });

        /* CARD VIEW TOOLBAR REGISTRATION & HANDLER - SEARCH BUTTON */
        cardViewPanel.setSearchCardListener(new ToolbarButtonListener() {
            public void toolbarButtonEventOccurred() {
                removeCardForms();
                cardViewPanel.setSearchForm(new SearchForm());
                cardViewPanel.add(cardViewPanel.getSearchForm(), BorderLayout.WEST);
                cardViewPanel.getSearchForm().setVisible(true);

                /* ADD a CANCEL BUTTON LISTENER AFTER CREATING FORM */
                cardViewPanel.getSearchForm().setCancelListener(new ButtonListener() {
                    public void buttonActionOccurred() {
                        cardViewPanel.getSearchForm().setVisible(false);
                        removeCardForms();
                        cardViewPanel.getResultsPane().setVisible(false);
                    }
                });

                cardViewPanel.getSearchForm().setSearchListener(new SearchListener() {
                    public void searchEventOccurred(SearchEvent e) {
                        //JScrollPane resultsScrollPane = cardViewPanel.getResultsScrollPane();
                        JTextPane resultsPane = cardViewPanel.getResultsPane();
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
                            e.getSearchIDTextField().setForeground(Style.redA700());
                            e.getSearchIDLabel().setForeground(Style.redA700());
                        }
                    }
                });
            }
        });

        /* CARD VIEW TOOLBAR REGISTRATION & HANDLER - CREATE CARD BUTTON */
        cardViewPanel.setCreateCardListener(new ToolbarButtonListener() {
            public void toolbarButtonEventOccurred() {
                removeCardForms();
                cardViewPanel.setCardForm(new CardForm());
                cardViewPanel.add(cardViewPanel.getCardForm(), BorderLayout.WEST);
                cardViewPanel.getCardForm().createCardForm();
                cardViewPanel.getCardForm().setGeneratedCardID(Database.generateCardID());
                cardViewPanel.getCardForm().setVisible(true);

                /* ADD A CANCEL BUTTON LISTENER AFTER CREATING FORM */
                cardViewPanel.getCardForm().setCancelListener(new ButtonListener() {
                    public void buttonActionOccurred() {
                        cardViewPanel.getCardForm().setVisible(false);
                        removeCardForms();
                    }
                });

                /* ADD A CREATE BUTTON LISTENER AFTER CREATING FORM */
                cardViewPanel.getCardForm().setCardListener(new CardListener() {
                    public void formActionOccurred() {
                        System.out.println("Create Card Pressed");
                    }
                });
            }
        });

        /* CARD VIEW TOOLBAR REGISTRATION & HANDLER - DELETE CARD BUTTON */
        cardViewPanel.setDeleteCardListener(new ToolbarButtonListener() {
            public void toolbarButtonEventOccurred() {
                removeCardForms();
                cardViewPanel.setDeleteForm(new DeleteCardForm());
                cardViewPanel.add(cardViewPanel.getDeleteForm(), BorderLayout.WEST);
                cardViewPanel.getDeleteForm().setVisible(true);
                cardViewPanel.getResultsPane().setVisible(false);

                /* REGISTER A CANCEL BUTTON LISTENER AFTER CREATING FORM */
                cardViewPanel.getDeleteForm().setCancelListener(new ButtonListener() {
                    public void buttonActionOccurred() {
                        cardViewPanel.getDeleteForm().setVisible(false);
                        removeCardForms();
                    }
                });

                /* REGISTER A CREATE BUTTON LISTENER AFTER CREATING FORM */
                cardViewPanel.getDeleteForm().setDeleteListener(new DeleteListener() {
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
                                cardViewPanel.refresh(db.getCards());
                                /* Purchase by this card will be changed to cash */
                                shop.convertPurchase(cardID);
                                purchaseViewPanel.refresh(db.getPurchases());
                            } else {
                                e.getSearchIDTextField().setText(null);
                            }
                        } else {
                            if (!rule.validate(input))
                                e.getRuleErrLabel().setVisible(true);
                            else
                                e.getErrorLabel().setVisible(true);

                            e.getSearchIDTextField().setForeground(Style.redA700());
                            e.getSearchIDLabel().setForeground(Style.redA700());
                        }
                    }
                });
            }
        });

        /* CARD VIEW TOOLBAR REGISTRATION & HANDLER - SORT */
        cardViewPanel.getCardToolbar().getSortedCombo().addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (e.getItem().equals("Sort")) {
                        cardViewPanel.refresh(db.getCards());
                    } else if (e.getItem().equals(SortCardType.CreatedOrder.getName())) {
                        cardViewPanel.refresh(sortCards(SortCardType.CreatedOrder));
                    } else if (e.getItem().equals(SortCardType.Name.getName())) {
                        cardViewPanel.refresh(sortCards(SortCardType.Name));
                    } else if (e.getItem().equals(SortCardType.Points.getName())) {
                        cardViewPanel.refresh(sortCards(SortCardType.Points));
                    }
                }
            }
        });

        /*============================== PURCHASE VIEW ==============================*/
        /* TOOLBAR CREATE PURCHASE BUTTON */
        purchaseToolbar.setCreatePurchaseListener(new ToolbarButtonListener() {
            public void toolbarButtonEventOccurred() {
                purchaseToolbar.disableCreatePurchaseButton(true);
                purchaseForm.setGeneratedReceiptID(db.generateReceiptID());
                purchaseForm.setCardModel(shop.getDatabase().getCardModel());
                purchaseForm.setCategoriesList(shop.getDatabase().getCategoriesList());
                purchaseForm.createPurchaseForm();
                purchaseForm.setVisible(true);
            }
        });

        /* TOOLBAR VIEW DETAILS BUTTON */
        purchaseToolbar.getViewPurchaseBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = purchaseViewPanel.getPurchaseTablePanel().getSelectedRow();
                Integer receiptID = (Integer) purchaseViewPanel.getPurchaseTablePanel().getValueAt(selectedRow, 0);

                for (Purchase purchase : db.getPurchases()) {
                    if (purchase.getReceiptID() == receiptID) {
                        purchaseViewPanel.getResultsPane().setText(purchase.toString());
                        purchaseViewPanel.getResultsPane().setVisible(true);
                    }
                }
            }
        });

        /* RESULTS PANE MOUSE LISTENER */
        purchaseViewPanel.getResultsPane().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                purchaseViewPanel.getResultsPane().setVisible(false);
            }
        });

        /* SORT COMBOBOX */
        purchaseToolbar.setSortPurchaseListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (e.getItem().equals(SortPurchaseType.All.getName())) {
                        purchaseViewPanel.refresh(sortPurchases(SortPurchaseType.All));
                    } else if (e.getItem().equals(SortPurchaseType.Card.getName())) {
                        purchaseViewPanel.refresh(sortPurchases(SortPurchaseType.Card));
                    } else if (e.getItem().equals(SortPurchaseType.Cash.getName())) {
                        purchaseViewPanel.refresh(sortPurchases(SortPurchaseType.Cash));
                    }
                }
            }
        });

        /* FORM CANCEL BUTTON */
        purchaseForm.setCancelPurchaseListener(new ButtonListener() {
            public void buttonActionOccurred() {
                removeCreatePurchaseForm();
            }
        });

        /* FORM CREATE BUTTON */
        purchaseForm.setCreatePurchaseListener(new PurchaseListener() {
            public void formActionOccurred(PurchaseEvent event) {
                JComboBox<String> type = event.getPurchaseTypeCombo();

                String receiptIDStr = event.getReceiptIDTextField().getText();
                int receiptID = Integer.parseInt(receiptIDStr);

                String cardID = getPurchaseFormCardID(event);
                HashMap<String, Double> categories = getPurchaseFormCategories(event);

                if (type.getSelectedItem() != null && cardID != null && categories != null) {
                    if (type.getSelectedItem().equals(PurchaseType.ExistingCardPurchase.getName())) {
                        shop.makePurchase(cardID, receiptID, categories);
                        purchaseViewPanel.refresh(db.getPurchases());
                        removeCreatePurchaseForm();
                    } else if (type.getSelectedItem().equals(PurchaseType.NewCardPurchase.getName())) {
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

                        HashMap<String, String> newCard = new HashMap<>();
                        newCard.put("name", name);
                        newCard.put("email", email);
                        newCard.put("cardType", cardType);
                        newCard.put("cardID", cardID);

                        shop.makeCard(newCard);
                        cardViewPanel.refresh(db.getCards());
                        shop.makePurchase(cardID, receiptID, categories);
                        purchaseViewPanel.refresh(db.getPurchases());
                        removeCreatePurchaseForm();

                    } else if (type.getSelectedItem().equals(PurchaseType.CashPurchase.getName())) {
                        shop.makePurchase(cardID, receiptID, categories);
                        purchaseViewPanel.refresh(db.getPurchases());
                        removeCreatePurchaseForm();
                    }
                } else {
                    event.getPurchaseErrorLabel().setVisible(true);
                }
            }
        });

    }

    /*============================== MUTATORS  ==============================*/
    private void removeCardForms() {
        if (cardViewPanel.getComponents().length >= 4) {
            cardViewPanel.remove(3);
        }
    }

    private void reenableAllButtons(JPanel toolbar) {
        /*for (Component btn : toolbar.getComponents()) {
            if (!btn.isEnabled()) {
                btn.setEnabled(true);
            }
        }*/

        if (toolbar instanceof CardsToolbar) {

        } else if (toolbar instanceof PurchaseToolbar) {

        }
    }

    private void removeCreatePurchaseForm() {
        /* REMOVE THE FORM FROM PURCHASE */
        purchaseForm.getPurchaseTypeCombo().setSelectedIndex(0);
        purchaseForm.setVisible(false);
        purchaseForm.remove(purchaseForm.getCreatePurchaseForm());
        purchaseViewPanel.getPurchaseToolbar().disableCreatePurchaseButton(false);
    }

    private boolean validateCatValueFields(HashMap<JLabel[], FormTextField> rawCategories) {
        boolean proceed = true;
        /* SETUP VALIDATOR FOR CATEGORY AMOUNT */
        FormValidData input = new FormValidData();
        FormRule catAmountRule = new CategoryAmountRule();

        for (HashMap.Entry<JLabel[], FormTextField> item : rawCategories.entrySet()) {
            input.setCatValueStr(item.getValue().getText());
            if (!catAmountRule.validate(input)) {
                item.getKey()[0].setForeground(Style.redA700());
                item.getKey()[1].setVisible(true);
                item.getValue().setForeground(Style.redA700());
                proceed = false;
            } else {
                item.getKey()[0].setForeground(Color.BLACK);
                item.getKey()[1].setVisible(false);
                item.getValue().setForeground(Color.BLACK);
            }
        }

        return proceed;
    }

    @Nullable
    private HashMap<String, Double> getPurchaseFormCategories(PurchaseEvent event) {
        HashMap<String, Double> categories = new HashMap<>();

        if (validateCatValueFields(event.getCategoriesMap())) {
            for (HashMap.Entry<JLabel[], FormTextField> item : event.getCategoriesMap().entrySet()) {
                String labelStr = item.getKey()[0].getText();
                String catName = labelStr.substring(0, labelStr.indexOf(":"));

                Double catValue = 0D;
                String textFieldStr = item.getValue().getText();

                if (!(textFieldStr.isEmpty())) {
                    catValue = Double.parseDouble(textFieldStr);
                }

                categories.put(catName, catValue);
            }
            return categories;
        } else {
            return null;
        }
    }

    @Nullable
    private String getPurchaseFormCardID(PurchaseEvent event) {
        JComboBox<String> type = event.getPurchaseTypeCombo();

        if (type.getSelectedItem() != null) {
            if (type.getSelectedItem().equals(PurchaseType.ExistingCardPurchase.getName())) {
                return (String)event.getExistingCardCombo().getSelectedItem();
            } else if (type.getSelectedItem().equals(PurchaseType.NewCardPurchase.getName())) {
                String newCardID = event.getCardIDTextField().getText();

                /* SETUP VALIDATOR FOR CARD ID */
                FormValidData input = new FormValidData();
                FormRule cardIDRule = new CardIDRule();
                input.setCardID(newCardID);

                if (!cardIDRule.validate(input)) {
                    event.getCardIDTextField().setForeground(Style.redA700());
                    event.getCardIDLabel().setForeground(Style.redA700());
                    event.getCardIDErrorLabel().setVisible(true);
                    return null;
                } else {
                    return newCardID;
                }
            } else if (type.getSelectedItem().equals(PurchaseType.CashPurchase.getName())) {
                return CardType.Cash.getName();
            }
        }
        return null;
    }

    private ArrayList<Purchase> sortPurchases(SortPurchaseType sortType) {
        ArrayList<Purchase> tempPurchases = new ArrayList<>();

        if (sortType.equals(SortPurchaseType.All)) {
            return db.getPurchases();
        } else if (sortType.equals(SortPurchaseType.Card)) {
            for (Purchase item : db.getPurchases()) {
                /* NEGATIVE CASH VALIDATION */
                if (item.getCardID() != null) {
                    tempPurchases.add(item);
                }
            }
            return tempPurchases;
        } else if (sortType.equals(SortPurchaseType.Cash)) {
            for (Purchase item : db.getPurchases()) {
                /* POSITIVE CASH Validation */
                if (item.getCardID() == null) {
                    tempPurchases.add(item);
                }
            }
            return tempPurchases;
        } else {
            return null;
        }
    }

    private ArrayList<Card> sortCards(SortCardType sortType) {
        ArrayList<Card> cards = new ArrayList<>(db.getCards());
        if (sortType.equals(SortCardType.CreatedOrder)) {
            cards.sort(new CardIDComparator());
            return cards;
        } else if (sortType.equals(SortCardType.Name)) {
            cards.sort(new CardNameComparator());
            return cards;
        } else if (sortType.equals(SortCardType.Points)) {
            cards.sort(new CardPointsComparator());
            return cards;
        }
        return null;
    }
}
