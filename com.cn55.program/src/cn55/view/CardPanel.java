package cn55.view;

import cn55.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

class CardPanel extends JPanel {

    private CardsToolbar toolbar;
    private SearchPanel searchPanel;
    private JTextArea cardTextArea;
    private CardTableModel cardTableModel;
    private ArrayList<Card> cards;

    // Constructor
    CardPanel() {
        this.toolbar = new CardsToolbar();
        this.searchPanel = new SearchPanel();
        this.cardTextArea = new JTextArea();
        this.card
        cardTextArea.setFont(Style.textFieldFont());
        cardTextArea.setEditable(false);
        cardTextArea.setVisible(false);

        setLayout(new BorderLayout());
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        cardTablePanel.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        cardTablePanel.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        cardTablePanel.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        cardTablePanel.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        cardTablePanel.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        cardTablePanel.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

        add(searchPanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(new JScrollPane(cardTextArea), BorderLayout.CENTER);
    }

    void appendCardTextArea(String text) {
        cardTextArea.setVisible(true);
        cardTextArea.setText(null);
        cardTextArea.append(text);
    }

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

    public void setCardData(ArrayList<Card> cards) {
        cardTableModel.setData(cards);
    }

    /*void refresh() {
        cardTextArea.setText(null);
        cardTextArea.setVisible(true);
        for (Card card : cards) {
            cardTextArea.append(card.toString());
        }
    }*/

    public void refresh() {
        cardTableModel.fireTableDataChanged();
    }

    // INNER CLASS
    public class CardTableModel extends AbstractTableModel {

        private ArrayList<Card> cards;
        private String[] cardHeaders = {"Card ID", "Card Type", "Name", "Email", "Balance", "Points"};

        public void setData(ArrayList<Card> cards) {
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
