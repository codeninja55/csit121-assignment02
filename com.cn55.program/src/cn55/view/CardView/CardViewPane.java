package cn55.view.CardView;

import cn55.model.CardModel.BasicCard;
import cn55.model.CardModel.Card;
import cn55.model.CardModel.PremiumCard;
import cn55.view.CustomComponents.Style;
import cn55.view.CustomComponents.ToolbarButton;
import cn55.view.DeleteForm.DeleteCardForm;
import cn55.view.SearchForm.SearchForm;
import cn55.view.ToolbarButtonListener;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CardViewPane extends JPanel {

    private CardTableModel cardTableModel;
    private JTable cardTablePanel;
    private JTextPane resultsPane;

    private SearchForm searchForm;
    private CardForm cardForm;
    private DeleteCardForm deleteForm;
    private CardsViewToolbar toolbar;

    private ToolbarButton createCardBtn;
    private ToolbarButton deleteCardBtn;
    private ToolbarButton searchBtn;

    private ToolbarButtonListener searchCardListener;
    private ToolbarButtonListener createCardListener;
    private ToolbarButtonListener deleteCardListener;

    /*============================== CONSTRUCTORS ==============================*/
    public CardViewPane() {
        cardTableModel = new CardTableModel();
        cardTablePanel = new JTable(cardTableModel);
        JScrollPane tableScrollPane = new JScrollPane(cardTablePanel);
        tableScrollPane.setName("CardsViewTableScrollPane");
        resultsPane = new JTextPane();
        //resultsScrollPane = new JScrollPane(resultsPane);
        toolbar = new CardsViewToolbar();

        setLayout(new BorderLayout());
        // Formatting for Table
        tableFormatter();
        cardTablePanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(toolbar, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        /* RESULTS PANE CUSTOMIZING */
        resultsPane.setName("ResultsPane");
        Dimension resultsDim = resultsPane.getPreferredSize();
        resultsDim.width = 700;
        resultsPane.setPreferredSize(resultsDim);
        resultsPane.setMinimumSize(resultsPane.getPreferredSize());
        resultsPane.setBorder(Style.resultsPaneBorder());
        resultsPane.setFont(Style.textPaneFont());
        resultsPane.setBackground(Style.blueGrey800());
        resultsPane.setForeground(Style.grey50());
        resultsPane.setVisible(false);
        add(resultsPane, BorderLayout.EAST);

        /* REGISTRATION OF LISTENERS */
        ToolbarListener handler = new ToolbarListener();
        searchBtn = toolbar.getSearchBtn();
        searchBtn.addActionListener(handler);
        createCardBtn = toolbar.getCreateCardBtn();
        createCardBtn.addActionListener(handler);
        deleteCardBtn = toolbar.getDeleteCardBtn();
        deleteCardBtn.addActionListener(handler);
    }

    /*============================== MUTATORS  ==============================*/
    public void setSearchCardListener(ToolbarButtonListener listener) { this.searchCardListener = listener; }

    public void setCreateCardListener(ToolbarButtonListener listener) {
        this.createCardListener = listener;
    }

    public void setDeleteCardListener(ToolbarButtonListener listener) {
        this.deleteCardListener = listener;
    }

    public void setSearchForm(SearchForm searchForm) {
        this.searchForm = searchForm;
    }

    public void setCardForm(CardForm cardForm) {
        this.cardForm = cardForm;
    }

    public void setDeleteForm(DeleteCardForm deleteForm) {
        this.deleteForm = deleteForm;
    }

    private void tableFormatter() {
        // FORMATTING FOR TABLE
        cardTablePanel.setRowHeight(45);
        cardTablePanel.setFont(Style.tableDataFont());
        cardTablePanel.getColumnModel().getColumn(0).setCellRenderer(Style.centerRenderer());
        cardTablePanel.getColumnModel().getColumn(0).setPreferredWidth(1);
        cardTablePanel.getColumnModel().getColumn(1).setCellRenderer(Style.centerRenderer());
        cardTablePanel.getColumnModel().getColumn(1).setPreferredWidth(5);
        cardTablePanel.getColumnModel().getColumn(2).setCellRenderer(Style.centerRenderer());
        cardTablePanel.getColumnModel().getColumn(3).setCellRenderer(Style.centerRenderer());
        cardTablePanel.getColumnModel().getColumn(4).setCellRenderer(Style.rightRenderer());
        cardTablePanel.getColumnModel().getColumn(4).setPreferredWidth(5);
        cardTablePanel.getColumnModel().getColumn(5).setCellRenderer(Style.rightRenderer());
        cardTablePanel.getColumnModel().getColumn(5).setPreferredWidth(5);
    }

    public void refreshCardsTable(ArrayList<Card> cards) {
        cardTableModel.setData(cards);
        cardTableModel.fireTableDataChanged();
    }

    /*============================== ACCESSORS  ==============================*/
    public CardsViewToolbar getCardToolbar() {
        return toolbar;
    }

    public SearchForm getSearchForm() {
        return searchForm;
    }

    public CardForm getCardForm() {
        return cardForm;
    }

    public DeleteCardForm getDeleteForm() {
        return deleteForm;
    }

    public JTextPane getResultsPane() {
        return resultsPane;
    }

    /*=========================================================================*/
    /*============================== INNER CLASS ==============================*/
    /*=========================================================================*/

    /*============================ TOOLBAR LISTENER ===========================*/
    public class ToolbarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == searchBtn) {
                if (searchCardListener != null)
                    searchCardListener.toolbarButtonEventOccurred();
            } else if (e.getSource() == createCardBtn) {
                if (createCardListener != null)
                    createCardListener.toolbarButtonEventOccurred();
            } else if (e.getSource() == deleteCardBtn) {
                if (deleteCardListener != null)
                    deleteCardListener.toolbarButtonEventOccurred();
            }
        }
    }

    /*=========================================================================*/
    /*============================== INNER CLASS ==============================*/
    /*=========================================================================*/

    /*============================= CardTableModel ============================*/
    class CardTableModel extends AbstractTableModel {

        private ArrayList<Card> cards;
        private String[] cardHeaders = {"Card ID", "Card Type", "Name", "Email", "Balance", "Points"};

        void setData(ArrayList<Card> cards) {
            this.cards = cards;
        }

        public String getColumnName(int column) {
            return cardHeaders[column];
        }

        public int getRowCount() {
            return cards.size();
        }

        public int getColumnCount() {
            return cardHeaders.length;
        }

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
                        return "S" + ((BasicCard) card).getBalance();
                    } else if (card instanceof PremiumCard) {
                        return "S" + ((PremiumCard) card).getBalance();
                    } else {
                        return "";
                    }

                case 5:
                    return card.getPoints();
            }
            return null;
        }
    }
}
