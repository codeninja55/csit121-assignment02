package cn55.view;

import cn55.model.Subject;
import cn55.model.CardModel.Card;
import cn55.model.Purchase;
import cn55.view.CardView.CardViewPane;
import cn55.view.CategoriesView.CategoriesViewPane;
import cn55.view.CustomComponents.Style;
import cn55.view.PurchaseView.PurchaseViewPane;

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
    private CardViewPane cardViewPane;
    private PurchaseViewPane purchaseViewPane;
    private CategoriesViewPane categoriesViewPane;
    //private SummaryPanel summaryPanel;

    public MainFrame() {
        super("Marvel Card Rewards");

        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        this.tabPane = new JTabbedPane();

        // Initialize panels for tabs
        JPanel welcomePane = new JPanel();
        // Without configuring, GridBagLayout automatically puts in middle
        welcomePane.setLayout(new GridBagLayout());
        JLabel welcomeLabel = new JLabel("Welcome to Marvel Rewards");
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD,56));
        welcomeLabel.setForeground(Style.red500());
        welcomePane.add(welcomeLabel);

        this.cardViewPane = new CardViewPane();
        this.purchaseViewPane = new PurchaseViewPane();
        this.categoriesViewPane = new CategoriesViewPane();

        // Add panels, toolbars, and panes to main Frame
        tabPane.setBackground(Style.blueGrey500());
        tabPane.setForeground(Style.btnTextColor());
        tabPane.setFont(Style.tabPaneFont());

        add(tabPane, BorderLayout.CENTER);

        // Add tabs to tabPane group
        tabPane.addTab("Welcome", welcomePane);
        tabPane.addTab("Cards", cardViewPane);
        tabPane.addTab("Purchases", purchaseViewPane);
        tabPane.addTab("Categories", categoriesViewPane);

        // DEFAULT PANE BEGIN AT
        tabPane.setSelectedIndex(3);
    }

    /*============================== ACCESSORS  ==============================*/
    public JTabbedPane getTabPane() { return tabPane; }

    public CardViewPane getCardViewPane() { return cardViewPane; }

    public PurchaseViewPane getPurchaseViewPane() { return purchaseViewPane; }

    public CategoriesViewPane getCategoriesViewPane() {
        return categoriesViewPane;
    }
}
