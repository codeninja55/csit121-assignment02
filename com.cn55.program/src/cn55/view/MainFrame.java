package cn55.view;

import cn55.model.Shop;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MainFrame extends JFrame {

    private JTabbedPane tabbedPane;
    //private JPanel welcomePanel;
    private CardPanel cardPanel;
    private PurchasesPanel purchasesPanel;
    private CategoriesPanel categoriesPanel;
    private SummaryPanel summaryPanel;

    public MainFrame(Shop shop) {
        super("Marvel Rewards");

        setLayout(new BorderLayout());

        //mainToolbar = new MainToolbar();
        this.tabPane = new JTabbedPane();
        tabPane.setFont(new Font("Verdana", Font.BOLD, 32));

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
            } else {
                // TODO DO NOT LEAVE THIS LIKE THIS - USED FOR TESTING ONLY
                cardPanel.refresh();
            }
        });

        // Listens to events in Toolbar and handles the event
        cardPanel.setCreateCardListener(new FormListener() {
            public void cardFormActionEvent() {
                CardForm createCardForm = new CardForm(shop.generateCardID());
                HashMap<String,String> newCard = createCardForm.getCardMap();
                shop.makeCard(newCard);
            }
        });

        cardPanel.setDeleteCardListener(new FormListener() {
            public void cardFormActionEvent() {
                CardForm deleteCardForm = new CardForm();

                if (!shop.cardExists(deleteCardForm.getCardID())) {
                    System.out.println("MainFrame");
                    System.out.println(deleteCardForm.getCardID());
                    deleteCardForm.deleteForm(false);
                } else {
                    System.out.println("Deleting Card: " + deleteCardForm.getCardID());
                    shop.deleteCard(deleteCardForm.getCardID());
                }

            }
        });
    }
}
