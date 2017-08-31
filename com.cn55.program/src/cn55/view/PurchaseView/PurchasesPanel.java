package cn55.view.PurchaseView;

import cn55.model.Purchase;
import cn55.view.CustomComponents.Style;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class PurchasesPanel extends JPanel {
    private PurchaseTableModel purchaseTableModel;
    private JTable purchaseTablePanel;
    private PurchaseForm purchaseForm;
    private PurchaseToolbar toolbar;

    /*============================== CONSTRUCTORS ==============================*/
    public PurchasesPanel() {
        purchaseTableModel = new PurchaseTableModel();
        purchaseTablePanel = new JTable(purchaseTableModel);
        toolbar = new PurchaseToolbar();
        purchaseForm = new PurchaseForm();

        setLayout(new BorderLayout());
        tableFormatter();

        add(toolbar, BorderLayout.NORTH);
        add(new JScrollPane(purchaseTablePanel), BorderLayout.CENTER);
        add(purchaseForm, BorderLayout.WEST);
        //add(deletePurchaseForm, BorderLayout.WEST);
    }

    /*============================== MUTATORS ==============================*/
    public void refresh(ArrayList<Purchase> purchases) {
        purchaseTableModel.setData(purchases);
        purchaseTableModel.fireTableDataChanged();
    }

    private void tableFormatter() {
        purchaseTablePanel.setRowHeight(30);
        purchaseTablePanel.setFont(Style.tableDataFont());

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        purchaseTablePanel.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        purchaseTablePanel.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        purchaseTablePanel.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        purchaseTablePanel.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        purchaseTablePanel.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
    }

    /*============================== ACCESSORS ==============================*/
    public PurchaseForm getPurchaseForm() { return purchaseForm; }

    public PurchaseToolbar getPurchaseToolbar() { return toolbar; }

    public PurchaseTableModel getPurchaseTableModel() { return purchaseTableModel; }

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
                    return purchase.getCategoriesTotal();
                case 4:
                    return purchase.getPurchaseTime();
            }

            return null;
        }
    }
}
