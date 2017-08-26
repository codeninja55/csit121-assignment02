package cn55.view;

import cn55.model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class CardPanel extends JPanel {

    private CardsToolbar toolbar;
    private SearchPanel searchPanel;
    private JTextArea cardTextArea;
    private ArrayList<Card> cards;

    // Constructor
    CardPanel() {
        this.toolbar = new CardsToolbar();
        this.searchPanel = new SearchPanel();
        this.cardTextArea = new JTextArea();
        cardTextArea.setFont(Style.textFieldFont());
        cardTextArea.setEditable(false);
        cardTextArea.setVisible(false);

        setLayout(new BorderLayout());

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

    void setCardData(ArrayList<Card> cards) { this.cards = cards; }

    void refresh() {
        cardTextArea.setText(null);
        cardTextArea.setVisible(true);
        for (Card card : cards) {
            cardTextArea.append(card.toString());
        }
    }
}
