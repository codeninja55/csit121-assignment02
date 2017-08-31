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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CardPanel extends JPanel {

    private CardTableModel cardTableModel;
    private JTable cardTablePanel;
    private JScrollPane tableScrollPane;
    private JTextPane resultsPane;

    private SearchForm searchForm;
    private CardForm cardForm;
    private DeleteCardForm deleteForm;
    private CardsToolbar toolbar;

    private ToolbarButton createCardBtn;
    private ToolbarButton deleteCardBtn;
    private ToolbarButton sortCardsBtn;
    private ToolbarButton searchBtn;

    private ToolbarButtonListener searchCardListener;
    private ToolbarButtonListener createCardListener;
    private ToolbarButtonListener deleteCardListener;

    /*============================== CONSTRUCTORS ==============================*/
    public CardPanel() {
        cardTableModel = new CardTableModel();
        cardTablePanel = new JTable(cardTableModel);
        tableScrollPane = new JScrollPane(cardTablePanel);
        tableScrollPane.setName("TableScrollPane");
        resultsPane = new JTextPane();
        //resultsScrollPane = new JScrollPane(resultsPane);
        toolbar = new CardsToolbar();

        setLayout(new BorderLayout());
        // Formatting for Table
        tableFormatter();

        add(toolbar, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        /* RESULTS PANE CUSTOMIZING */
        resultsPane.setName("ResultsPane");
        Dimension resultsDim = resultsPane.getPreferredSize();
        resultsDim.width = 700;
        //resultsDim.height = 400;
        resultsPane.setPreferredSize(resultsDim);
        resultsPane.setMinimumSize(resultsPane.getPreferredSize());

        Border outInnerBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1,1,1,1, Style.red500()),
                "Results",
                TitledBorder.LEFT,
                TitledBorder.CENTER,
                new Font("Verdana",Font.BOLD,24),
                Style.red500());
        Border inInnerBorder = BorderFactory.createEmptyBorder(10,10,10,10);
        Border innerBorder = BorderFactory.createCompoundBorder(outInnerBorder, inInnerBorder);
        Border outerBorder = BorderFactory.createEmptyBorder(1,10,10,10);
        resultsPane.setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        resultsPane.setFont(Style.textPaneFont());
        resultsPane.setBackground(Style.blueGrey800());
        resultsPane.setForeground(Style.grey50());
        resultsPane.setVisible(false);
        //resultsScrollPane.setVisible(false);
        add(resultsPane, BorderLayout.EAST);

        /* REGISTRATION OF LISTENERS */
        ToolbarListener handler = new ToolbarListener();
        searchBtn = toolbar.getSearchBtn();
        searchBtn.addActionListener(handler);
        createCardBtn = toolbar.getCreateCardBtn();
        createCardBtn.addActionListener(handler);
        deleteCardBtn = toolbar.getDeleteCardBtn();
        deleteCardBtn.addActionListener(handler);
        sortCardsBtn = toolbar.getSortCardsBtn();
        sortCardsBtn.addActionListener(handler);
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

    /*============================== TOOLBAR LISTENER ==============================*/
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
            } else if (e.getSource() == sortCardsBtn) {
                // TODO Listen on ComboBox changes
                System.out.println("Sort Button Pressed");
            }
        }
    }

    /*============================== CardTableModel ==============================*/
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
