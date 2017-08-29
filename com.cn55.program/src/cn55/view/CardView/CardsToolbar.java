package cn55.view.CardView;

import cn55.view.CustomComponents.Style;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CardsToolbar extends JPanel {

    private JButton addCard;
    private JButton deleteCard;
    private JButton sortCards;
    //private JTextField searchField;
    private JButton searchButton;
    private CardListener createCardListener;
    private CardListener deleteCardListener;

    // Constructor
    CardsToolbar() {

        Border innerBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK),
                "Actions",
                TitledBorder.LEFT,
                TitledBorder.CENTER,
                Style.titledBorderFont());

        Border outerBorder = BorderFactory.createEmptyBorder(20,10,20,10);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        Font btnFont = Style.buttonFont();
        Color btnColor = Style.btnColor();
        Color textColor = Style.btnTextColor();
        addCard = new JButton("Add Card");
        addCard.setFont(btnFont);
        addCard.setForeground(textColor);
        addCard.setBackground(btnColor);
        deleteCard = new JButton("Delete Card");
        deleteCard.setFont(btnFont);
        deleteCard.setForeground(textColor);
        deleteCard.setBackground(btnColor);
        sortCards = new JButton("Sort Cards");
        sortCards.setFont(btnFont);
        sortCards.setForeground(textColor);
        sortCards.setBackground(btnColor);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(addCard);
        add(deleteCard);

        // TODO Add a combobox for the type of sorting required
        add(sortCards);

        // Registration of listeners
        ButtonListener handler = new ButtonListener();
        addCard.addActionListener(handler);
        deleteCard.addActionListener(handler);
        sortCards.addActionListener(handler);
    }

    void setCreateCardListener(CardListener listener) {
        this.createCardListener = listener;
    }

    void setDeleteCardListener(CardListener listener) {
        this.deleteCardListener = listener;
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addCard) {
                //new CardForm();
                if (createCardListener != null) {
                    createCardListener.formActionOccurred();
                }
            } else if (e.getSource() == deleteCard) {
                if (deleteCardListener != null) {
                    deleteCardListener.formActionOccurred();
                }
            } else if (e.getSource() == sortCards) {

                // TODO Listen on ComboBox changes
                JOptionPane.showMessageDialog(null,"Sorting Cards Selected");
            }
        }
    }
}
