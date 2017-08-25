package cn55.view;

import cn55.model.BasicCard;
import cn55.model.Card;
import cn55.model.PremiumCard;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class CardPanel extends JPanel {

    private CardsToolbar toolbar;
    private JTable cardTablePanel;
    private CardTableModel cardTableModel;

    public CardPanel() {
        toolbar = new CardsToolbar();
        cardTableModel = new CardTableModel();
        cardTablePanel = new JTable(cardTableModel);

        cardTablePanel.setRowHeight(30);

        setLayout(new BorderLayout());

        add(toolbar, BorderLayout.NORTH);
        add(new JScrollPane(cardTablePanel), BorderLayout.CENTER);

        //setBorder(BorderFactory.createEtchedBorder());

    }

    public void setCardData(ArrayList<Card> cards) {
        cardTableModel.setData(cards);
    }

    /* Will tell the DataModel that data has changed
    *  on an event from a listener for form button that
    *  has yet to be implemented */
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
