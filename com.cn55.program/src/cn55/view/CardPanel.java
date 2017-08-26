package cn55.view;

import cn55.model.Card;
import cn55.model.PremiumCard;
import cn55.model.BasicCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

class CardPanel extends JPanel {

    private CardsToolbar toolbar;
    private SearchPanel searchPanel;
    // UNCOMMENT TO USE TEXTAREA private JTextArea cardTextArea;
    private CardTableModel cardTableModel;
    private JTable cardTablePanel;

    // Constructor
    CardPanel() {
        this.toolbar = new CardsToolbar();
        this.searchPanel = new SearchPanel();
        this.cardTableModel = new CardTableModel();
        this.cardTablePanel = new JTable(cardTableModel);
        // UNCOMMENT TO USE TEXTAREA this.cardTextArea = new JTextArea(cardTablePanel);
        // UNCOMMENT TO USE TEXTAREA cardTextArea.setFont(Style.textFieldFont());
        // UNCOMMENT TO USE TEXTAREA cardTextArea.setEditable(false);
        // UNCOMMENT TO USE TEXTAREA cardTextArea.setVisible(false);

        setLayout(new BorderLayout());

        // FORMATTING FOR TABLE
        cardTablePanel.setRowHeight(30);
        cardTablePanel.setFont(Style.tableDataFont());
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        cardTablePanel.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        cardTablePanel.getColumnModel().getColumn(0).setPreferredWidth(3);
        cardTablePanel.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        cardTablePanel.getColumnModel().getColumn(1).setPreferredWidth(5);
        cardTablePanel.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        cardTablePanel.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        cardTablePanel.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        cardTablePanel.getColumnModel().getColumn(4).setPreferredWidth(5);
        cardTablePanel.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        cardTablePanel.getColumnModel().getColumn(5).setPreferredWidth(5);

        add(searchPanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(new JScrollPane(cardTablePanel), BorderLayout.CENTER);
        // UNCOMMENT TO USE TEXTAREA add(new JScrollPane(cardTextArea), BorderLayout.CENTER);
    }

    // UNCOMMENT TO USE TEXTAREA
    /*oid appendCardTextArea(String text) {
        cardTextArea.setVisible(true);
        cardTextArea.setText(null);
        cardTextArea.append(text);
    }*/

    /*==================== EVENT LISTENERS METHODS ====================*/
    /* These listener methods just pass the listener event to their relevant
     * panels with the buttons where the Event Handler is placed. */
    void setCreateCardListener(FormListener listener) {
        toolbar.setCreateCardListener(listener);
    }

    void setDeleteCardListener(FormListener listener) {
        toolbar.setDeleteCardListener(listener);
    }

    void setSearchListener(SearchListener listener) { searchPanel.setSearchListener(listener); }

    /*void setCardData(ArrayList<Card> cards) { this.cards = cards; }*/

    void setCardData(ArrayList<Card> cards) {
        cardTableModel.setData(cards);
    }

    /*void refresh() {
        cardTextArea.setText(null);
        cardTextArea.setVisible(true);
        for (Card card : cards) {
            cardTextArea.append(card.toString());
        }
    }*/

    void refresh() {
        cardTableModel.fireTableDataChanged();
    }

    // INNER CLASS FOR CARD TABLE MODEL
    public class CardTableModel extends AbstractTableModel {

        private ArrayList<Card> cards;
        private String[] cardHeaders = {"Card ID", "Card Type", "Name", "Email", "Balance", "Points"};

        void setData(ArrayList<Card> cards) {
            this.cards = cards;
        }

        @Override
        public String getColumnName(int column) {
            return cardHeaders[column];
        }

        @Override
        public int getRowCount() {
            return cards.size();
        }

        @Override
        public int getColumnCount() {
            return cardHeaders.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Card card = cards.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return card.getID();
                case 1:
                    return card.getCardType();
                case 2:
                    if (card instanceof BasicCard) {
                        return ((BasicCard) card).getName();
                    } else if (card instanceof PremiumCard) {
                        return ((PremiumCard) card).getName();
                    } else {
                        return "";
                    }

                case 3:
                    if (card instanceof BasicCard) {
                        return ((BasicCard) card).getEmail();
                    } else if (card instanceof PremiumCard) {
                        return ((PremiumCard) card).getEmail();
                    } else {
                        return "";
                    }

                case 4:
                    if (card instanceof BasicCard) {
                        return ((BasicCard) card).getBalance();
                    } else if (card instanceof PremiumCard) {
                        return ((PremiumCard) card).getBalance();
                    } else {
                        return 0;
                    }

                case 5:
                    return card.getPoints();
            }
            return null;
        }
    }
}
