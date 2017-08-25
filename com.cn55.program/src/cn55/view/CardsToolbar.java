package cn55.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;

class CardsToolbar extends JPanel {

    private JButton addCard;
    private JButton deleteCard;
    private JButton sortCards;
    //private JTextField searchField;
    private JButton searchButton;
    private FormListener createCardListener;
    private FormListener deleteCardListener;

    // Constructor
    CardsToolbar() {

        Border innerBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK),
                "Actions",
                TitledBorder.LEFT,
                TitledBorder.CENTER,
                new Font("Verdana",Font.BOLD,22));

        Border outerBorder = BorderFactory.createEmptyBorder(20,10,20,10);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        Font btnFont = new Font("Verdana", Font.BOLD, 24);
        addCard = new JButton("Add Card");
        addCard.setFont(btnFont);
        deleteCard = new JButton("Delete Card");
        deleteCard.setFont(btnFont);
        sortCards = new JButton("Sort Cards");
        sortCards.setFont(btnFont);
        //searchField = new JTextField("Search for Card", 50);
        searchButton = new JButton("Search");
        searchButton.setFont(btnFont);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(addCard);
        add(deleteCard);
        add(sortCards);
        add(searchButton);
        //add(searchField);

        // Registration of listeners
        ButtonListener handler = new ButtonListener();
        addCard.addActionListener(handler);
        deleteCard.addActionListener(handler);
        sortCards.addActionListener(handler);
        searchButton.addActionListener(handler);
    }

    void setCreateCardListener(FormListener listener) {
        this.createCardListener = listener;
    }

    void setDeleteCardListener(FormListener listener) {
        this.deleteCardListener = listener;
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addCard) {
                //new CardForm();
                if (createCardListener != null) {
                    createCardListener.cardFormActionEvent();
                }
            } else if (e.getSource() == deleteCard) {
                if (deleteCardListener != null) {
                    deleteCardListener.cardFormActionEvent();
                }
            } else if (e.getSource() == sortCards) {
                JOptionPane.showMessageDialog(null,"Sorting Cards Selected");
            } else if (e.getSource() == searchButton) {
                JOptionPane.showMessageDialog(null, "Search Selected");
            }
        }
    }
}
