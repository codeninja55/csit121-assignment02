package cn55.view;

import cn55.model.Shop;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    //private Database db;

    public MainFrame(Shop shop) {
        super("Marvel Rewards");

        setLayout(new BorderLayout());

        //mainToolbar = new MainToolbar();
        JTabbedPane tabPane = new JTabbedPane();
        tabPane.setFont(new Font("Verdana", Font.BOLD, 32));

        /*// Initialize panels for tabs
        JPanel welcomePanel = new JPanel();
        // Without configuring, GraibBayLayout automatically puts in middle
        welcomePanel.setLayout(new GridBagLayout());
        JLabel welcomeLabel = new JLabel("Welcome to Marvel Rewards");
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD,56));
        welcomePanel.add(welcomeLabel);*/

        CardPanel cardPanel = new CardPanel();
        PurchasesPanel purchasesPanel = new PurchasesPanel();
        CategoriesPanel categoriesPanel = new CategoriesPanel();
        SummaryPanel summaryPanel = new SummaryPanel();

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
                new CardForm(shop.generateCardID());
            }
        });

        cardPanel.setDeleteCardListener(new FormListener() {
            public void cardFormActionEvent() {
                new CardForm(shop.getDatabase().getCards());
            }
        });

        //setSize(2160,1440);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
