package cn55.view.CardView;

import cn55.model.CardModel.Card;
import cn55.model.CardModel.PremiumCard;
import cn55.model.CardModel.BasicCard;
import cn55.view.SearchPanel.SearchListener;
import cn55.view.SearchPanel.SearchPanel;
import cn55.view.CustomComponents.Style;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class CardPanel extends JPanel {

    private CardsToolbar toolbar;
    private SearchPanel searchPanel;
    private CardForm cardForm;
    private CardTableModel cardTableModel;
    private JTable cardTablePanel;

    // Constructor
    public CardPanel() {
        this.cardTableModel = new CardTableModel();
        this.cardTablePanel = new JTable(cardTableModel);
        this.toolbar = new CardsToolbar();
        this.searchPanel = new SearchPanel();
        //this.cardForm = new CardForm();

        setLayout(new BorderLayout());
        // Formatting for Table
        tableFormatter();

        add(toolbar, BorderLayout.NORTH);
        add(new JScrollPane(cardTablePanel), BorderLayout.CENTER);
        // TODO - Add the different forms panels
    }

    /*============================== MUTATORS  ==============================*/
    private void tableFormatter() {
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
    }

    public void setCardData(ArrayList<Card> cards) {
        cardTableModel.setData(cards);
    }

    public void refresh(ArrayList<Card> cards) {
        setCardData(cards);
        cardTableModel.fireTableDataChanged();
    }

    /*============================== ACCESSORS  ==============================*/

    public CardsToolbar getCardToolbar() {
        return toolbar;
    }

    public SearchPanel getSearchPanel() {
        return searchPanel;
    }

    public CardForm getCardForm() {
        return cardForm;
    }

    public CardTableModel getCardTableModel() {
        return cardTableModel;
    }

    public JTable getCardTablePanel() {
        return cardTablePanel;
    }

    // INNER CLASS FOR CARD TABLE MODEL
    class CardTableModel extends AbstractTableModel {

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
