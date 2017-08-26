package cn55.view;

import cn55.model.Card;
import cn55.model.Purchase;
import cn55.model.Shop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/* CONTROLLER CLASS */
/* GUI view package will only interact
 * with data through this controller class
 * and Program controller class */

public class MainFrame extends JFrame {

    private Shop shop;
    private JTabbedPane tabPane;
    //private JPanel welcomePanel;
    private CardPanel cardPanel;
    private PurchasesPanel purchasePanel;
    private CategoriesPanel categoriesPanel;
    private SummaryPanel summaryPanel;

    public MainFrame(Shop shop) {
        super("Marvel Rewards");

        this.shop = shop;

        setLayout(new BorderLayout());

        //mainToolbar = new MainToolbar();
        this.tabPane = new JTabbedPane();

        /*// Initialize panels for tabs
        this.welcomePanel = new JPanel();
        // Without configuring, GridBagLayout automatically puts in middle
        welcomePanel.setLayout(new GridBagLayout());
        JLabel welcomeLabel = new JLabel("Welcome to Marvel Rewards");
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD,56));
        welcomePanel.add(welcomeLabel);*/

        this.cardPanel = new CardPanel();
        this.purchasePanel = new PurchasesPanel();
        this.categoriesPanel = new CategoriesPanel();
        this.summaryPanel = new SummaryPanel();

        /* Pass in copies of the ArrayList instead of hte db data */
        ArrayList<Card> cards = new ArrayList<>(shop.getDatabase().getCards());
        cardPanel.setCardData(cards);
        ArrayList<Purchase> purchases = new ArrayList<>(shop.getDatabase().getPurchases());
        purchasePanel.setPurchaseData(purchases);


        // Add panels, toolbars, and panes to main Frame
        //add(mainToolbar, BorderLayout.NORTH);
        tabPane.setBackground(Style.red500());
        tabPane.setForeground(Style.btnTextColor());
        tabPane.setFont(Style.tabPaneFont());

        add(tabPane, BorderLayout.CENTER);

        // Add tabs to tabPane group
        //tabPane.addTab("Welcome", welcomePanel);
        tabPane.addTab("Cards", cardPanel);
        tabPane.addTab("Purchases", purchasePanel);
        tabPane.addTab("Categories", categoriesPanel);
        tabPane.addTab("Summary", summaryPanel);

        //setSize(2160,1440);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        this.eventControlling();
    }

    /*==================== HANDLE EVENTS ====================*/
    private void eventControlling() {

        // Listens to change events for tabs and handles the events
        tabPane.addChangeListener(e -> {
            if (tabPane.getSelectedComponent() == cardPanel) {
                cardPanel.refresh();
            } else if (tabPane.getSelectedComponent() == purchasePanel) {
                purchasePanel.refresh();
            }
        });

        // Listens to events in Toolbar and handles the event
        cardPanel.setCreateCardListener(new FormListener() {
            public void formActionOccurred() {
                CardForm createCardForm = new CardForm(shop.generateCardID());
                HashMap<String,String> newCard = createCardForm.getCardMap();
                shop.makeCard(newCard);
                cardPanel.refresh();
            }
        });

        cardPanel.setDeleteCardListener(new FormListener() {
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
                    int cardIndex = shop.getDatabase().getCardMap().get(cardID);
                    String cardText = shop.getDatabase().getCards().get(cardIndex).toString();
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
    }
}
