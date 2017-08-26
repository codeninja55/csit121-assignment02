package cn55.view;

import cn55.model.Shop;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MainFrame extends JFrame {

    private Shop shop;
    private JTabbedPane tabPane;
    //private JPanel welcomePanel;
    private CardPanel cardPanel;
    private PurchasesPanel purchasesPanel;
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
        this.purchasesPanel = new PurchasesPanel();
        this.categoriesPanel = new CategoriesPanel();
        this.summaryPanel = new SummaryPanel();

        cardPanel.setCardData(shop.getDatabase().getCards());

        // Add panels, toolbars, and panes to main Frame
        //add(mainToolbar, BorderLayout.NORTH);
        tabPane.setBackground(Style.red500());
        tabPane.setForeground(Style.btnTextColor());
        tabPane.setFont(Style.tabPaneFont());

        add(tabPane, BorderLayout.CENTER);

        // Add tabs to tabPane group
        //tabPane.addTab("Welcome", welcomePanel);
        tabPane.addTab("Cards", cardPanel);
        tabPane.addTab("Purchases", purchasesPanel);
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
