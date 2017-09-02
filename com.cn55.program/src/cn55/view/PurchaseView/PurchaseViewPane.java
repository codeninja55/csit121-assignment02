package cn55.view.PurchaseView;

import cn55.model.Purchase;
import cn55.view.CustomComponents.ResultsPane;
import cn55.view.CustomComponents.Style;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PurchaseViewPane extends JPanel {
    private PurchaseTableModel purchaseTableModel;
    private JTable purchaseTablePane;
    private PurchaseForm purchaseForm;
    private PurchaseViewToolbar toolbar;
    private ResultsPane resultsPane;
    private JPopupMenu tablePopup;

    /*============================== CONSTRUCTORS ==============================*/
    public PurchaseViewPane() {
        purchaseTableModel = new PurchaseTableModel();
        purchaseTablePane = new JTable(purchaseTableModel);
        toolbar = new PurchaseViewToolbar();
        purchaseForm = new PurchaseForm();
        resultsPane = new ResultsPane("PurchaseViewResultsPane");
        tablePopup = new JPopupMenu();
        JMenuItem removePurchase = new JMenuItem("Delete Purchase");

        tablePopup.add(removePurchase);

        setLayout(new BorderLayout());

        add(toolbar, BorderLayout.NORTH);

        purchaseTablePane.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableFormatter();
        add(new JScrollPane(purchaseTablePane), BorderLayout.CENTER);

        add(purchaseForm, BorderLayout.WEST);
        //add(deletePurchaseForm, BorderLayout.WEST);
        add(resultsPane, BorderLayout.EAST);
    }

    /*============================== MUTATORS ==============================*/
    public void refreshPurchasesTable(ArrayList<Purchase> purchases) {
        purchaseTableModel.setData(purchases);
        purchaseTableModel.fireTableDataChanged();
    }

    private void tableFormatter() {
        purchaseTablePane.setRowHeight(45);
        purchaseTablePane.setFont(Style.tableDataFont());
        purchaseTablePane.getColumnModel().getColumn(0).setCellRenderer(Style.leftRenderer());
        purchaseTablePane.getColumnModel().getColumn(1).setCellRenderer(Style.centerRenderer());
        purchaseTablePane.getColumnModel().getColumn(2).setCellRenderer(Style.centerRenderer());
        purchaseTablePane.getColumnModel().getColumn(3).setCellRenderer(Style.rightRenderer());
        purchaseTablePane.getColumnModel().getColumn(4).setCellRenderer(Style.centerRenderer());
    }

    /*============================== ACCESSORS ==============================*/
    public PurchaseForm getPurchaseForm() { return purchaseForm; }

    public PurchaseViewToolbar getPurchaseToolbar() { return toolbar; }

    public JTable getPurchaseTablePane() {
        return purchaseTablePane;
    }

    public ResultsPane getResultsPane() {
        return resultsPane;
    }

    /*=========================================================================*/
    /*============================== INNER CLASS ==============================*/
    /*=========================================================================*/
    public class PurchaseTableModel extends AbstractTableModel {

        private ArrayList<Purchase> purchases;
        private String[] tableHeaders = {"Receipt ID","Card ID", "Card Type",
                "Total Amount","Purchase Time"};

        void setData (ArrayList<Purchase> purchases) { this.purchases = purchases; }

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
