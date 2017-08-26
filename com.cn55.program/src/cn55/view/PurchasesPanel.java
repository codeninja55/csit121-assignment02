package cn55.view;

import cn55.model.Purchase;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class PurchasesPanel extends JPanel {
    private PurchaseToolbar toolbar;
    private PurchaseTableModel purchaseTableModel;
    private JTable purchaseTablePanel;

    PurchasesPanel() {
        toolbar = new PurchaseToolbar();
        purchaseTableModel = new PurchaseTableModel();
        purchaseTablePanel = new JTable(purchaseTableModel);

        setLayout(new BorderLayout());

        purchaseTablePanel.setRowHeight(30);
        purchaseTablePanel.setFont(Style.tableDataFont());

        add(toolbar, BorderLayout.NORTH);
        add(new JScrollPane(purchaseTablePanel), BorderLayout.CENTER);
    }

    void setPurchaseData(ArrayList<Purchase> purchases) { purchaseTableModel.setData(purchases); }

    void refresh() {
        purchaseTableModel.fireTableDataChanged();
    }

    public class PurchaseTableModel extends AbstractTableModel {

        private ArrayList<Purchase> purchases;
        private String[] tableHeaders = {"Receipt ID","Card ID", "Card Type",
                "Total Amount","Purchase Time"};

        void setData (ArrayList<Purchase> purchases) { this.purchases = purchases; }

        public String getColumnName(int column) {
            // TODO - Implement method to loop through categoriesList to print out headers
            return tableHeaders[column];
        }

        public int getRowCount() {
            return purchases.size();
        }

        public int getColumnCount() { return tableHeaders.length; }

        @Override
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
