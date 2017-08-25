package cn55.view;

import cn55.model.Shop;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    //private MainToolbar mainToolbar;
    private JTabbedPane tabPane;
    private JPanel welcomePanel;
    private CardPanel cardPanel;
    private PurchasesPanel purchasesPanel;
    private CategoriesPanel categoriesPanel;
    private SummaryPanel summaryPanel;

    public MainFrame(Shop shop) {
        super("Marvel Rewards");

        setLayout(new BorderLayout());

        //mainToolbar = new MainToolbar();
        tabPane = new JTabbedPane();

        // Initialize panels for tabs
        welcomePanel = new JPanel();
        // Without configuring, GraibBayLayout automatically puts in middle
        welcomePanel.setLayout(new GridBagLayout());
        JLabel welcomeLabel = new JLabel("Welcome to Marvel Rewards");
        welcomeLabel.setFont(new Font("Verdana",1,56));
        welcomePanel.add(welcomeLabel);

        cardPanel = new CardPanel();
        purchasesPanel = new PurchasesPanel();
        categoriesPanel = new CategoriesPanel();
        summaryPanel = new SummaryPanel();

        cardPanel.setCardData(shop.getCards());

        // Add panels, toolbars, and panes to main Frame
        //add(mainToolbar, BorderLayout.NORTH);
        add(tabPane, BorderLayout.CENTER);

        // Add tabs to tabPane group
        tabPane.addTab("Welcome", welcomePanel);
        tabPane.addTab("Cards", cardPanel);
        tabPane.addTab("Purchases", purchasesPanel);
        tabPane.addTab("Categories", categoriesPanel);
        tabPane.addTab("Summary", summaryPanel);

        /*tabPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabPane.getSelectedComponent() == cardPanel) {
                    cardPanel.refresh();
                }
            }
        });*/

        //setSize(2160,1440);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
