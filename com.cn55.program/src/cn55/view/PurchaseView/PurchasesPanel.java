package cn55.view.PurchaseView;

import cn55.model.Purchase;
import cn55.view.CustomComponents.Style;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PurchasesPanel extends JPanel {
    private PurchaseTableModel purchaseTableModel;
    private PurchaseForm purchaseForm;
    private PurchaseToolbar toolbar;

    public PurchasesPanel() {
        purchaseTableModel = new PurchaseTableModel();
        JTable purchaseTablePanel = new JTable(purchaseTableModel);
        toolbar = new PurchaseToolbar();
        purchaseForm = new PurchaseForm();

        setLayout(new BorderLayout());

        purchaseTablePanel.setRowHeight(30);
        purchaseTablePanel.setFont(Style.tableDataFont());

        add(toolbar, BorderLayout.NORTH);
        add(new JScrollPane(purchaseTablePanel), BorderLayout.CENTER);
        add(purchaseForm, BorderLayout.WEST);
        //add(deletePurchaseForm, BorderLayout.WEST);
    }

    /*==================== MUTATORS ====================*/

    public void refresh() {
        purchaseTableModel.fireTableDataChanged();
    }

    /*==================== ACCESSORS ====================*/

    public PurchaseForm getPurchaseForm() { return purchaseForm; }

    public PurchaseToolbar getPurchaseToolbar() { return toolbar; }

    public PurchaseTableModel getPurchaseTableModel() { return purchaseTableModel; }

    public class PurchaseTableModel extends AbstractTableModel {

        private ArrayList<Purchase> purchases;
        private String[] tableHeaders = {"Receipt ID","Card ID", "Card Type",
                "Total Amount","Purchase Time"};

        public void setData (ArrayList<Purchase> purchases) { this.purchases = purchases; }

        public String getColumnName(int column) {
            // TODO - Implement method to loop through categoriesList to print out headers
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
                    return purchase.getCategoriesTotal();
                case 4:
                    return purchase.getPurchaseTime();
            }

            return null;
        }
    }
}