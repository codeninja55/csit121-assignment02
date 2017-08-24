import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardsToolbar extends JPanel {

    private JButton addCard;
    private JButton deleteCard;
    private JButton sortCards;
    //private JTextField searchField;
    private JButton searchButton;

    public CardsToolbar() {

        addCard = new JButton("Add Card");
        deleteCard = new JButton("Delete Card");
        sortCards = new JButton("Sort Cards");
        //searchField = new JTextField("Search for Card", 50);
        searchButton = new JButton("Search");

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(addCard);
        add(deleteCard);
        add(sortCards);

        add(searchButton);
        //add(searchField);

        ButtonListener handler = new ButtonListener();
        addCard.addActionListener(handler);
        deleteCard.addActionListener(handler);
        sortCards.addActionListener(handler);
        searchButton.addActionListener(handler);

    }

    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == addCard) {
                JOptionPane.showMessageDialog(null,"Adding Card Selected");
            } else if (e.getSource() == deleteCard) {
                JOptionPane.showMessageDialog(null,"Deleting Card Selected");
            } else if (e.getSource() == sortCards) {
                JOptionPane.showMessageDialog(null,"Sorting Cards Selected");
            } else if (e.getSource() == searchButton) {
                JOptionPane.showMessageDialog(null, "Search Selected");
            }
        }
    }
}
