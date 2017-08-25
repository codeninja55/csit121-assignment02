package cn55.view;

import cn55.model.Database;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(Database db) {
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

        cardPanel.setCardData(db.getCards());

        // Add panels, toolbars, and panes to main Frame
        //add(mainToolbar, BorderLayout.NORTH);
        add(tabPane, BorderLayout.CENTER);

        // Add tabs to tabPane group
        //tabPane.addTab("Welcome", welcomePanel);
        tabPane.addTab("Cards", cardPanel);
        tabPane.addTab("Purchases", purchasesPanel);
        tabPane.addTab("Categories", categoriesPanel);
        tabPane.addTab("Summary", summaryPanel);

        tabPane.addChangeListener(e -> {
            if (tabPane.getSelectedComponent() == cardPanel) {
                cardPanel.refresh();
            }
        });

        //setSize(2160,1440);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
