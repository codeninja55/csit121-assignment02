package cn55.view;

import cn55.model.BasicCard;
import cn55.model.Card;
import cn55.model.PremiumCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardPanel extends JPanel {

    private CardsToolbar toolbar;
    private JTextArea cardTextArea;
    private ArrayList<Card> cards;

    public CardPanel() {
        toolbar = new CardsToolbar();
        cardTextArea = new JTextArea();
        cardTextArea.setFont(new Font("Arial",Font.BOLD, 24));

        setLayout(new BorderLayout());

        //cardTextArea.appendCardData();

        add(toolbar, BorderLayout.NORTH);
        add(new JScrollPane(cardTextArea), BorderLayout.CENTER);
    }

    void setCardData(ArrayList<Card> cards) {
        //cardTextArea.setCardData(cards);
        this.cards = cards;
    }

    void refresh() {
        for (Card card : cards) {
            cardTextArea.append(card.toString());
        }
    }
}
