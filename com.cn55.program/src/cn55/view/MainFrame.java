package cn55.view;

import cn55.model.CardModel.Card;
import cn55.model.Purchase;
import cn55.view.CardView.*;
import cn55.view.CategoriesView.*;
import cn55.view.CustomComponents.Style;
import cn55.view.PurchaseView.*;
import cn55.view.SummaryView.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/* CONTROLLER CLASS */
/* GUI view package will only interact
 * with data through this controller class
 * and Program controller class */

@SuppressWarnings("Convert2Lambda")
public class MainFrame extends JFrame {

    private JTabbedPane tabPane;
    private JPanel welcomePanel;
    private CardPanel cardPanel;
    private PurchasesPanel purchasePanel;
    private CategoriesPanel categoriesPanel;
    private SummaryPanel summaryPanel;

    public MainFrame(ArrayList<Purchase> purchases, ArrayList<Card> cards) {
        super("Marvel Rewards");

        setLayout(new BorderLayout());

        this.tabPane = new JTabbedPane();

        // Initialize panels for tabs
        this.welcomePanel = new JPanel();
        // Without configuring, GridBagLayout automatically puts in middle
        welcomePanel.setLayout(new GridBagLayout());
        JLabel welcomeLabel = new JLabel("Welcome to Marvel Rewards");
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD,56));
        welcomePanel.add(welcomeLabel);

        this.cardPanel = new CardPanel();
        this.purchasePanel = new PurchasesPanel();
        this.categoriesPanel = new CategoriesPanel();
        this.summaryPanel = new SummaryPanel();

        /* Pass in copies of the ArrayList instead of hte db data */
        cardPanel.setCardData(cards);
        purchasePanel.getPurchaseTableModel().setData(purchases);

        // Add panels, toolbars, and panes to main Frame
        tabPane.setBackground(Style.blueGrey500());
        tabPane.setForeground(Style.btnTextColor());
        tabPane.setFont(Style.tabPaneFont());

        add(tabPane, BorderLayout.CENTER);

        // Add tabs to tabPane group
        tabPane.addTab("Welcome", welcomePanel);
        tabPane.addTab("Cards", cardPanel);
        tabPane.addTab("Purchases", purchasePanel);
        tabPane.addTab("Categories", categoriesPanel);
        tabPane.addTab("Summary", summaryPanel);

        // DEFAULT PANE BEGIN AT
        tabPane.setSelectedIndex(2);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /*============================== ACCESSORS  ==============================*/
    public JTabbedPane getTabPane() { return tabPane; }

    public CardPanel getCardPanel() { return cardPanel; }

    public PurchasesPanel getPurchasePanel() { return purchasePanel; }

    public CategoriesPanel getCategoriesPanel() {
        return categoriesPanel;
    }

    public SummaryPanel getSummaryPanel() {
        return summaryPanel;
    }

    public JPanel getWelcomePanel() {
        return welcomePanel;
    }
}
