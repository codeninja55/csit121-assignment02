package cn55.controller;

import cn55.controller.Validator.CardIDRule;
import cn55.controller.Validator.CategoryAmountRule;
import cn55.controller.Validator.FormRule;
import cn55.controller.Validator.FormValidData;
import cn55.model.CardModel.*;
import cn55.model.*;
import cn55.view.ButtonListener;
import cn55.view.CardView.CardForm;
import cn55.view.CardView.CardListener;
import cn55.view.CardView.CardViewPane;
import cn55.view.CardView.CardsViewToolbar;
import cn55.view.CategoriesView.CategoriesForm;
import cn55.view.CategoriesView.CategoriesViewPane;
import cn55.view.CategoriesView.CategoryEvent;
import cn55.view.CategoriesView.CategoryListener;
import cn55.view.CustomComponents.FormTextField;
import cn55.view.CustomComponents.ResultsPane;
import cn55.view.CustomComponents.Style;
import cn55.view.DeleteForm.DeleteCardForm;
import cn55.view.DeleteForm.DeleteEvent;
import cn55.view.DeleteForm.DeleteListener;
import cn55.view.MainFrame;
import cn55.view.PurchaseView.*;
import cn55.view.SearchForm.SearchEvent;
import cn55.view.SearchForm.SearchForm;
import cn55.view.SearchForm.SearchListener;
import cn55.view.ToolbarButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

class Program {

    private MainFrame mainFrame;
    private Shop shop;
    private Database db;
    private JTabbedPane tabPane;
    private CardViewPane cardViewPane;
    private CardsViewToolbar cardToolbar;
    private CardForm cardForm;
    private PurchaseViewPane purchaseViewPane;
    private PurchaseViewToolbar purchaseViewToolbar;
    private PurchaseForm purchaseForm;
    private CategoriesViewPane categoriesViewPane;

    private static Set<Double> testAmountSet = new HashSet<>();

    Program() {
        shop = new Shop();
        db = shop.getDatabase();
        createTestCode(shop);
        db.mapCards();

        this.mainFrame = new MainFrame(shop.getDatabase().getPurchases(),
                                    shop.getDatabase().getCards(),
                                    shop.getDatabase().getCategories());

        this.tabPane = mainFrame.getTabPane();

        this.cardViewPane = mainFrame.getCardViewPane();
        this.cardToolbar = cardViewPane.getCardToolbar();

        this.purchaseViewPane = mainFrame.getPurchaseViewPane();
        this.purchaseForm = purchaseViewPane.getPurchaseForm();
        this.purchaseViewToolbar = purchaseViewPane.getPurchaseToolbar();

        this.categoriesViewPane = mainFrame.getCategoriesViewPane();

        eventControlling();
    }

    private ArrayList<Component> getAllComponents(final Container container) {
        // REFERENCE: http://www.java2s.com/Code/Java/Swing-JFC/GetAllComponentsinacontainer.html
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

    /* TODO - TEST CODE */
    private Double generateRandomValue() {
        Random randomValueObj = new Random();
        int value1, value2, result;
        Double finalVal = 0D;
        value1 = randomValueObj.ints(0,99).findFirst().getAsInt();
        value2 = randomValueObj.ints(0,9).findFirst().getAsInt();

        result = value1 * value2;
        if (result < 100) {
            finalVal = result * 10D;
        } else if (result > 100 && result < 300) {
            finalVal = result / 5D;
        } else if (result > 300) {
            finalVal = result / 2D;
        } else {
            finalVal = result * 5D;
        }

        if (testAmountSet.contains(finalVal)) {
            return generateRandomValue();
        } else {
            testAmountSet.add(finalVal);
            return finalVal;
        }
    }

    /* TODO - TEST CODE */
    private HashMap<Integer, Category> generateRandomCategoriesMap() {
        HashMap<Integer, Category> testingCategoryMap = new HashMap<>();

        for (Category c : db.getCategories()) {
            testingCategoryMap.put(c.getId(), new Category(c));
        }

        for (HashMap.Entry<Integer, Category> item : testingCategoryMap.entrySet()) {
            item.getValue().setAmount(generateRandomValue());
        }

        return testingCategoryMap;
    }

    /* TODO - TEST CODE */
    private void testMakePurchases(int numOfPurchases, String id) {
        for (int i = 0; i < numOfPurchases; i++) {
            shop.makePurchase(id, Database.generateReceiptID(), generateRandomCategoriesMap());
        }
    }

    /* TODO - TEST CODE */
    private void createTestCode(Shop shop) {

        // Cash purchase test
        testMakePurchases(4, "Cash");

        // AnonCard Test
        db.addCards(new AnonCard()); // MC10001
        testMakePurchases(3, "MC10001");

        db.addCards(new AnonCard()); // MC10002
        testMakePurchases(2, "MC10002");

        // BasicCard Test
        db.addCards(new BasicCard("Natasha Romanov",
                "blackwidow@avengers.team", 0));
        testMakePurchases(3,"MC10003");

        // More Cash purchases
        testMakePurchases(4, "Cash");

        // BasicCard Test 2
        db.addCards(new BasicCard("Steve Rogers",
                "captain_a@avengers.team",0D));
        testMakePurchases(2, "MC10004");

        // More Cash purchases
        testMakePurchases(2, "Cash");

        // More Romanov purchases
        testMakePurchases(5,"MC10003");

        // PremiumCard Test
        db.addCards(new PremiumCard("Tony Stark",
                "ironman@avengers.team",0));

        testMakePurchases(5,"MC10005");

        // More Cash purchases
        testMakePurchases(1, "Cash");

        // PremiumCard Test 2
        db.addCards(new PremiumCard("Nick Fury",
                "nick@shield.com",0));

        testMakePurchases(3, "MC10006");

        db.addCards(new AnonCard()); //MC10007
        testMakePurchases(2,"MC10007");

        db.addCards(new AnonCard()); //MC10008
        db.getCards().get(7).calcPoints(300D);

        db.addCards(new BasicCard("Hank Pym","ants@avengers.team",0)); // MC10009
        db.addCards(new BasicCard("Peter Parker", "spidey@avengers.team",0)); // MC10010
        db.addCards(new PremiumCard("Danny Rand","danny@randcorp.com",5000)); // MC10011
        testMakePurchases(3, "MC10011");
        db.addCards(new PremiumCard("Professor Charles Xavier", "x@xmen.com", 1238798)); // MC100012
        testMakePurchases(2,"MC10012");

        db.addCards(new BasicCard("Matthew Murdock","thedevil@hellskitchen.com", 666));
        db.addCards(new BasicCard("Thor Odinson", "thor@asgard.com",9000));

        shop.makePurchase("Cash", Database.generateReceiptID(),
                generateRandomCategoriesMap());

        testMakePurchases(2,"MC10015");
        testMakePurchases(2,"MC10018");

        shop.makePurchase("Cash", Database.generateReceiptID(),
                generateRandomCategoriesMap());

        db.addCards(new BasicCard("Clint Barton", "better_than_arrow@marvel.com", 500));
        db.addCards(new AnonCard());
        testMakePurchases(2,"MC10016");
        db.addCards(new PremiumCard("Oliver Queen", "the_best_arrow_user@dc.comics", 1500));
        testMakePurchases(5,"MC10017");
        db.addCards(new BasicCard("Loki", "the-baddest-dude@asgard.com", 0));
        testMakePurchases(2,"MC10016");
        db.addCards(new AnonCard());
        db.addCards(new AnonCard());
        db.addCards(new PremiumCard("Pepper Potts", "potter@marvel.com", 1987234));
        db.addCards(new AnonCard());
        db.addCards(new AnonCard());
        db.addCards(new PremiumCard("T'Challa", "tokenman@wakanda.africa", 105023));
        db.addCards(new AnonCard());
        db.addCards(new BasicCard("Logan", "wolverine@xmen.com", 0));
        /* TEST CODE */
        /*System.err.println("TESTING CODE");
        for (Purchase p : db.getPurchases()) {
            System.out.println(p.getCategoriesTotal());
        }*/
    }

    /*============================== REGISTER AND HANDLE EVENTS ==============================*/
    private void eventControlling() {
        /* TAB PANE LISTENER */
        tabPane.addChangeListener(e -> {

            /* SELECTED LISTENERS */
            if (tabPane.getSelectedComponent() == cardViewPane) {
                cardViewPane.refreshCardsTable(db.getCards());
            } else if (tabPane.getSelectedComponent() == purchaseViewPane) {
                purchaseViewToolbar.disableCreatePurchaseButton(false);
                purchaseViewPane.refreshPurchasesTable(shop.getDatabase().getPurchases());
            } if (tabPane.getSelectedComponent() == categoriesViewPane) {
                categoriesViewPane.refreshCategoriesTable(shop.getDatabase().getCategories());
            }

            /* DESELECTED LISTENERS */
            if (tabPane.getSelectedComponent() != purchaseViewPane) {
                purchaseViewPane.getResultsPane().setVisible(false);
                if (purchaseForm.getCreatePurchaseForm() != null) {
                    removeCreatePurchaseForm();
                }
            } else if (tabPane.getSelectedComponent() != cardViewPane) {
                cardViewPane.getResultsPane().setVisible(false);
                removeCardForms();
            } else if (tabPane.getSelectedComponent() != categoriesViewPane) {
                removeCategoryForms();
            }
        });

        /*============================== CARD VIEW ==============================*/
        /* TOOLBAR REGISTRATION & HANDLER - CREATE CARD BUTTON */
        cardViewPane.setCreateCardListener(new ToolbarButtonListener() {
            public void toolbarButtonEventOccurred() {
                removeCardForms();
                cardViewPane.setCardForm(new CardForm());
                cardViewPane.add(cardViewPane.getCardForm(), BorderLayout.WEST);
                cardViewPane.getCardForm().createCardForm();
                cardViewPane.getCardForm().setVisible(true);

                /* ADD A CANCEL BUTTON LISTENER AFTER CREATING FORM */
                cardViewPane.getCardForm().setCancelListener(new ButtonListener() {
                    public void buttonActionOccurred() {
                        cardViewPane.getCardForm().setVisible(false);
                        removeCardForms();
                    }
                });

                /* ADD A CREATE BUTTON LISTENER AFTER CREATING FORM */
                cardViewPane.getCardForm().setCardListener(new CardListener() {
                    public void formActionOccurred() {
                        System.out.println("Create Card Pressed");
                    }
                });
            }
        });

        /* TOOLBAR REGISTRATION & HANDLER - DELETE CARD BUTTON */
        cardViewPane.setDeleteCardListener(new ToolbarButtonListener() {
            public void toolbarButtonEventOccurred() {
                System.out.println("DELETE BUTTON PRESSED");
                removeCardForms();
                cardViewPane.setDeleteForm(new DeleteCardForm());
                cardViewPane.add(cardViewPane.getDeleteForm(), BorderLayout.WEST);
                cardViewPane.getDeleteForm().setVisible(true);
                cardViewPane.getResultsPane().setVisible(false);

                /* REGISTER A CANCEL BUTTON LISTENER AFTER CREATING FORM */
                cardViewPane.getDeleteForm().setCancelListener(new ButtonListener() {
                    public void buttonActionOccurred() {
                        cardViewPane.getDeleteForm().setVisible(false);
                        removeCardForms();
                    }
                });

                /* REGISTER A CREATE BUTTON LISTENER AFTER CREATING FORM */
                cardViewPane.getDeleteForm().setDeleteListener(new DeleteListener() {
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
                                cardViewPane.refreshCardsTable(db.getCards());
                                /* Purchase by this card will be changed to cash */
                                shop.convertPurchase(cardID);
                                purchaseViewPane.refreshPurchasesTable(db.getPurchases());
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

        /* TOOLBAR REGISTRATION & HANDLER - SEARCH BUTTON */
        cardViewPane.setSearchCardListener(new ToolbarButtonListener() {
            public void toolbarButtonEventOccurred() {
                removeCardForms();
                cardViewPane.setSearchForm(new SearchForm());
                cardViewPane.add(cardViewPane.getSearchForm(), BorderLayout.WEST);
                cardViewPane.getSearchForm().setVisible(true);

                /* ADD A CANCEL BUTTON LISTENER AFTER CREATING FORM */
                cardViewPane.getSearchForm().setCancelListener(new ButtonListener() {
                    public void buttonActionOccurred() {
                        cardViewPane.getSearchForm().setVisible(false);
                        cardViewPane.getResultsPane().setVisible(false);
                        removeCardForms();
                    }
                });

                /* ADD A CANCEL BUTTON LISTENER AFTER CREATING FORM */
                cardViewPane.getSearchForm().setSearchListener(new SearchListener() {
                    public void searchEventOccurred(SearchEvent e) {
                        //JScrollPane resultsScrollPane = cardViewPane.getResultsScrollPane();

                        /* Setup a text pane to put all the necessary data into */
                        ResultsPane resultsPane = cardViewPane.getResultsPane();
                        resultsPane.setResultsTextPane();
                        ResultsPane.ResultsTextPane resultsTextPane = resultsPane.getResultsTextPane();

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
                                        purchaseText.append("\n");
                                        purchaseText.append(purchaseText.append(purchase.toString()));
                                    }
                                }
                            }

                            String results = String.format("%s%n%s%n%n%s%n%s","CARD FOUND",
                                    cardText,"PURCHASE(S)",purchaseText);

                            /* Create the inner class ResultsTextPane and popular first.
                            * Then set the ResultsPane to visible and add the new ScrollPane
                            * Achieved in method below - showResultsPane() */
                            showResultsPane(results, resultsPane, resultsTextPane);

                            e.getSearchIDTextField().setText(null);
                        } else {
                            if (!cardIDRule.validate(input))
                                e.getRuleErrLabel().setVisible(true);
                            else
                                e.getErrorLabel().setVisible(true);

                            removeResultsPane(resultsPane);
                            resultsPane.setVisible(false);
                            e.getSearchIDTextField().setForeground(Style.redA700());
                            e.getSearchIDLabel().setForeground(Style.redA700());
                        }
                    }
                });
            }
        });

        /* TOOLBAR REGISTRATION & HANDLER - SORT */
        cardViewPane.getCardToolbar().getSortedCombo().addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (e.getItem().equals("Sort..")) {
                        cardViewPane.refreshCardsTable(db.getCards());
                    } else if (e.getItem().equals(SortCardType.ReverseCreatedOrder.getName())) {
                        cardViewPane.refreshCardsTable(sortCards(SortCardType.ReverseCreatedOrder));
                    } else if (e.getItem().equals(SortCardType.CreatedOrder.getName())) {
                        cardViewPane.refreshCardsTable(sortCards(SortCardType.CreatedOrder));
                    } else if (e.getItem().equals(SortCardType.Name.getName())) {
                        cardViewPane.refreshCardsTable(sortCards(SortCardType.Name));
                    } else if (e.getItem().equals(SortCardType.Points.getName())) {
                        cardViewPane.refreshCardsTable(sortCards(SortCardType.Points));
                    }
                }
            }
        });

        /*============================== PURCHASE VIEW ==============================*/
        /* TOOLBAR CREATE PURCHASE BUTTON */
        purchaseViewToolbar.setCreatePurchaseListener(new ToolbarButtonListener() {
            public void toolbarButtonEventOccurred() {
                //purchaseViewToolbar.disableCreatePurchaseButton(true);
                purchaseForm.setGeneratedReceiptID(Database.generateReceiptID());
                purchaseForm.setCardModel(shop.getDatabase().getCardModel());
                purchaseForm.setCategoriesList(shop.getDatabase().getCategories());
                purchaseForm.createPurchaseForm();
                purchaseForm.setVisible(true);
            }
        });

        /* TOOLBAR VIEW DETAILS BUTTON */
        purchaseViewToolbar.setViewPurchaseListener(new ToolbarButtonListener() {
            public void toolbarButtonEventOccurred() {
                int selectedRow = purchaseViewPane.getPurchaseTablePane().getSelectedRow();
                Integer receiptID = (Integer) purchaseViewPane.getPurchaseTablePane().getValueAt(selectedRow, 0);

                ResultsPane resultsPane = purchaseViewPane.getResultsPane();
                resultsPane.setResultsTextPane();
                ResultsPane.ResultsTextPane resultsTextPane = resultsPane.getResultsTextPane();

                String resultsText = "";
                for (Purchase purchase : db.getPurchases()) {
                    if (purchase.getReceiptID() == receiptID) {
                        resultsText = purchase.toString();
                    }
                }

                showResultsPane(resultsText, resultsPane, resultsTextPane);

                /* SET UP A MOUSE LISTENER TO CLOSE PANEL WHEN CLICKING ON TABLE OR OUTER PANEL*/
                purchaseViewPane.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        removeResultsPane(purchaseViewPane.getResultsPane());
                        purchaseViewPane.getResultsPane().setVisible(false);
                    }
                });

                purchaseViewPane.getPurchaseTablePane().addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        removeResultsPane(purchaseViewPane.getResultsPane());
                        purchaseViewPane.getResultsPane().setVisible(false);
                    }
                });
            }
        });

        /* SORT COMBOBOX */
        purchaseViewToolbar.setSortPurchaseListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (e.getItem().equals(SortPurchaseType.All.getName())) {
                        purchaseViewPane.refreshPurchasesTable(sortPurchases(SortPurchaseType.All));
                    } else if (e.getItem().equals(SortPurchaseType.Card.getName())) {
                        purchaseViewPane.refreshPurchasesTable(sortPurchases(SortPurchaseType.Card));
                    } else if (e.getItem().equals(SortPurchaseType.Cash.getName())) {
                        purchaseViewPane.refreshPurchasesTable(sortPurchases(SortPurchaseType.Cash));
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
                HashMap<Integer, Category> categories = getPurchaseFormCategories(event);

                if (type.getSelectedItem() != null && cardID != null && categories != null) {
                    if (type.getSelectedItem().equals(PurchaseType.ExistingCardPurchase.getName())) {
                        shop.makePurchase(cardID, receiptID, categories);
                        purchaseViewPane.refreshPurchasesTable(db.getPurchases());
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
                        cardViewPane.refreshCardsTable(db.getCards());
                        shop.makePurchase(cardID, receiptID, categories);
                        purchaseViewPane.refreshPurchasesTable(db.getPurchases());
                        removeCreatePurchaseForm();

                    } else if (type.getSelectedItem().equals(PurchaseType.CashPurchase.getName())) {
                        shop.makePurchase(cardID, receiptID, categories);
                        purchaseViewPane.refreshPurchasesTable(db.getPurchases());
                        removeCreatePurchaseForm();
                    }
                } else {
                    event.getPurchaseErrorLabel().setVisible(true);
                }
            }
        });

        /*============================== CATEGORIES VIEW ==============================*/
        /* TOOLBAR REGISTRATION & HANDLER - CREATE CARD BUTTON */
        categoriesViewPane.setCreateCategoryListener(new ToolbarButtonListener() {
            public void toolbarButtonEventOccurred() {
                categoriesViewPane.setCreateCategoryForm(new CategoriesForm());
                categoriesViewPane.add(categoriesViewPane.getCreateCategoryForm(), BorderLayout.WEST);
                categoriesViewPane.getCreateCategoryForm().setVisible(true);

                /* ADD A CANCEL BUTTON LISTENER AFTER CREATING FORM */
                categoriesViewPane.getCreateCategoryForm().setCancelListener(new ButtonListener() {
                    public void buttonActionOccurred() {
                        categoriesViewPane.getCreateCategoryForm().setVisible(false);
                        removeCategoryForms();
                    }
                });

                /* ADD A CREATE BUTTON LISTENER AFTER CREATING FORM */
                categoriesViewPane.getCreateCategoryForm().setCreateCategoryListener(new CategoryListener() {
                    public void createCategoryEventOccurred(CategoryEvent e) {
                        shop.makeCategory(new Category(e.getCategoryNameTextField().getText(),
                                e.getCategoryDescTextField().getText()));
                        categoriesViewPane.refreshCategoriesTable(shop.getDatabase().getCategories());
                        removeCategoryForms();
                    }
                });

            }
        });

    }

    /*============================== MUTATORS  ==============================*/
    private void showResultsPane(String text, ResultsPane resultsPane,
                                   ResultsPane.ResultsTextPane resultsTextPane) {
        resultsTextPane.setText(text);
        resultsPane.setVisible(true);
        resultsPane.setScrollPane(resultsTextPane);
        resultsPane.add(resultsPane.getResultsTextPane());
        resultsPane.getResultsTextPane().grabFocus();
        resultsPane.getResultsTextPane().setCaretPosition(0);
    }

    private void removeCardForms() {
        for (Component comp : cardViewPane.getComponents()) {
            if (comp instanceof CardForm || comp instanceof DeleteCardForm || comp instanceof SearchForm) {
                cardViewPane.remove(comp);
            }
        }
    }

    private void removeCreatePurchaseForm() {
        /* REMOVE THE FORM FROM PURCHASE */
        purchaseForm.getPurchaseTypeCombo().setSelectedIndex(0);
        purchaseForm.setVisible(false);
        purchaseForm.remove(purchaseForm.getCreatePurchaseForm());
        //purchaseViewPane.getPurchaseToolbar().disableCreatePurchaseButton(false);
    }

    private void removeResultsPane(ResultsPane resultsPane) {
        resultsPane.remove(resultsPane.getResultsTextPane());
        resultsPane.remove(resultsPane.getScrollPane());
    }

    private void removeCategoryForms() {
        for (Component comp : cardViewPane.getComponents()) {
            if (comp instanceof CategoriesForm) {
                categoriesViewPane.remove(comp);
            }
        }
    }

    private void reenableAllButtons(JPanel toolbar) {
        /*for (Component btn : toolbar.getComponents()) {
            if (!btn.isEnabled()) {
                btn.setEnabled(true);
            }
        }*/

        if (toolbar instanceof CardsViewToolbar) {

        } else if (toolbar instanceof PurchaseViewToolbar) {

        }
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

    private HashMap<Integer, Category> getPurchaseFormCategories(PurchaseEvent event) {
        HashMap<Integer, Category> categories = new HashMap<>();

        if (validateCatValueFields(event.getCategoriesMap())) {
            for (HashMap.Entry<JLabel[], FormTextField> item : event.getCategoriesMap().entrySet()) {
                String labelStr = item.getKey()[0].getText();
                String catName = labelStr.substring(0, labelStr.indexOf(":"));

                Double catValue = 0D;
                String textFieldStr = item.getValue().getText();

                if (!(textFieldStr.isEmpty())) {
                    catValue = Double.parseDouble(textFieldStr);
                }

//                categories.put(catName, catValue);
            }
            return categories;
        } else {
            return null;
        }
    }

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
            Collections.sort(cards);
            return cards;
        } else if (sortType.equals(SortCardType.ReverseCreatedOrder)) {
            cards.sort(new CardReverseIDComparator());
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
