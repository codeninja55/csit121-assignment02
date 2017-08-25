package cn55.view;

import javax.swing.*;
import java.awt.*;

public class CardForm {

    public CardForm() {
        String[] cardType = {"Anon Card", "Basic Card", "Premium Card"};
        JComboBox<String> cardTypeCombo = new JComboBox<>(cardType);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        //gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(10,0,30,0);

        panel.add(new JLabel("Choose the Card Type to Create"), gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(10,0,50,0);
        panel.add(cardTypeCombo, gc);

        int result = JOptionPane.showConfirmDialog(null,
                panel,
                "Card Type",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            System.out.println(cardTypeCombo.getSelectedItem().toString());

            if (cardTypeCombo.getSelectedItem() == "Anon Card") {
                this.createAnonCard();
            } else {
                this.createOtherCardForm(cardTypeCombo.getSelectedItem());
            }
        } else {
            System.out.println("Cancelled");
        }
    }

    public void createAnonCard() {

        // TODO use generateCardID method from shop class
        JTextField cardID = new JTextField("Test Card ID", 30);
        cardID.setEditable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(10,0,30,0);

        panel.add(new JLabel("Anon Card Created"), gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10,0,50,0);
        panel.add(new JLabel("Card ID: "), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10,0,50,0);
        panel.add(cardID, gc);

        int confirm = JOptionPane.showConfirmDialog(null,
                panel,
                "Create Anon Card",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        // TODO make a temp card

        /*if (confirm == JOptionPane.OK_OPTION) {
            // Add to Database
        } else {
            // DELETE the temp card
        }*/
    }

    public void createOtherCardForm(Object cardType) {

        String title;

        if (cardType.equals("Basic Card")) {
            title = "Create Basic Card";
        } else {
            title = "Create Premium Card";
        }

        // TODO use generateCardID method from shop class
        JTextField cardID = new JTextField("Test Card ID", 30);
        cardID.setEditable(false);
        JTextField name = new JTextField(40);
        JTextField email = new JTextField(40);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10,0,30,10);
        //JTextField totalAmount = new JTextField();

        panel.add(new JLabel("Card ID: "), gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10,0,30,0);
        panel.add(cardID, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(10,0,0,5);
        panel.add(new JLabel("Customer Name: "), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(10,0,0,0);
        panel.add(name, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(10,0,50,5);
        panel.add(new JLabel("Customer Email: "), gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(10,0,50,0);
        panel.add(email, gc);

        int confirm = JOptionPane.showConfirmDialog(null,
                panel,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        // TODO make temp new card before confirm

        /*if (result == JOptionPane.OK_OPTION) {
            // Add to Database
        } else {
            // DELETE the CARd
        }*/
    }
}
