package cn55.view;

import javax.swing.*;
import java.awt.*;

public class CardForm {

    public CardForm() {
        String[] cardType = {"Anon Card", "Basic Card", "Premium Card"};
        JComboBox<String> cardTypeCombo = new JComboBox<>(cardType);

        JPanel panel = new JPanel(new GridLayout());
        panel.add(cardTypeCombo);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Card",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            System.out.println(cardTypeCombo.getSelectedItem());
        } else {
            System.out.println("Cancelled");
        }
    }
}
