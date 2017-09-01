package cn55.view.PurchaseView;

import cn55.model.Purchase;
import cn55.view.CustomComponents.Style;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class PurchaseViewPanel extends JPanel {
    private PurchaseTableModel purchaseTableModel;
    private JTable purchaseTablePanel;
    private PurchaseForm purchaseForm;
    private PurchaseToolbar toolbar;
    private JTextPane resultsPane;
    JPopupMenu tablePopup;

    /*============================== CONSTRUCTORS ==============================*/
    public PurchaseViewPanel() {
        purchaseTableModel = new PurchaseTableModel();
        purchaseTablePanel = new JTable(purchaseTableModel);
        toolbar = new PurchaseToolbar();
        purchaseForm = new PurchaseForm();
        resultsPane = new JTextPane();
        tablePopup = new JPopupMenu();
        JMenuItem removePurchase = new JMenuItem("Delete Purchase");

        tablePopup.add(removePurchase);

        setLayout(new BorderLayout());

        add(toolbar, BorderLayout.NORTH);

        purchaseTablePanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableFormatter();
        add(new JScrollPane(purchaseTablePanel), BorderLayout.CENTER);

        add(purchaseForm, BorderLayout.WEST);
        //add(deletePurchaseForm, BorderLayout.WEST);

        /* RESULTS PANE CUSTOMIZING */
        resultsPane.setName("ResultsPane");
        Dimension resultsDim = resultsPane.getPreferredSize();
        resultsDim.width = 700;
        //resultsDim.height = 400;
        resultsPane.setPreferredSize(resultsDim);
        resultsPane.setMinimumSize(resultsPane.getPreferredSize());
        resultsPane.setBorder(Style.resultsPaneBorder());
        resultsPane.setFont(Style.textPaneFont());
        resultsPane.setBackground(Style.blueGrey800());
        resultsPane.setForeground(Style.grey50());
        resultsPane.setVisible(false);
        add(resultsPane, BorderLayout.EAST);
    }

    /*============================== MUTATORS ==============================*/
    public void refresh(ArrayList<Purchase> purchases) {
        purchaseTableModel.setData(purchases);
        purchaseTableModel.fireTableDataChanged();
    }

    private void tableFormatter() {
        purchaseTablePanel.setRowHeight(45);
        purchaseTablePanel.setFont(Style.tableDataFont());
        purchaseTablePanel.getColumnModel().getColumn(0).setCellRenderer(Style.leftRenderer());
        purchaseTablePanel.getColumnModel().getColumn(1).setCellRenderer(Style.centerRenderer());
        purchaseTablePanel.getColumnModel().getColumn(2).setCellRenderer(Style.centerRenderer());
        purchaseTablePanel.getColumnModel().getColumn(3).setCellRenderer(Style.rightRenderer());
        purchaseTablePanel.getColumnModel().getColumn(4).setCellRenderer(Style.centerRenderer());
    }

    /*============================== ACCESSORS ==============================*/
    public PurchaseForm getPurchaseForm() { return purchaseForm; }

    public PurchaseToolbar getPurchaseToolbar() { return toolbar; }

    public JTable getPurchaseTablePanel() {
        return purchaseTablePanel;
    }

    public JTextPane getResultsPane() {
        return resultsPane;
    }

    /*============================== INNER CLASS ==============================*/
    public class PurchaseTableModel extends AbstractTableModel {

        private ArrayList<Purchase> purchases;
        private String[] tableHeaders = {"Receipt ID","Card ID", "Card Type",
                "Total Amount","Purchase Time"};

        public void setData (ArrayList<Purchase> purchases) { this.purchases = purchases; }

        public String getColumnName(int column) {
            return tableHeaders[column];
        }

        public int getRowCount() { return purchases.size(); }

        public int getColumnCount() { return tableHeaders.length; }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Purchase purchase = purchases.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return purchase.getReceiptID();
                case 1:
                    return purchase.getCardID();
                case 2:
                    return purchase.getCardType();
                case 3:
                    return "$" + purchase.getCategoriesTotal();
                case 4:
                    return purchase.getPurchaseTime();
            }

            return null;
        }
    }
}
