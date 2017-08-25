package cn55.view;

import cn55.model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class CardPanel extends JPanel {

    private CardsToolbar toolbar;
    private JTextArea cardTextArea;
    private ArrayList<Card> cards;

    // Constructor
    CardPanel() {
        toolbar = new CardsToolbar();
        cardTextArea = new JTextArea();
        cardTextArea.setFont(new Font("Verdana",Font.BOLD, 20));

        setLayout(new BorderLayout());

        add(toolbar, BorderLayout.NORTH);
        add(new JScrollPane(cardTextArea), BorderLayout.CENTER);
    }

    void setCreateCardListener(FormListener listener) {
        toolbar.setCreateCardListener(listener);
    }

    void setDeleteCardListener(FormListener listener) {
        toolbar.setDeleteCardListener(listener);
    }

    void setCardData(ArrayList<Card> cards) { this.cards = cards; }

    void refresh() {
        cardTextArea.setText(null);
        for (Card card : cards) {
            cardTextArea.append(card.toString());
        }
    }
}
