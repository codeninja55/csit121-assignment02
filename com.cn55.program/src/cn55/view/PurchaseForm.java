package cn55.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PurchaseForm {
    private HashMap<String, String> newPurchase;
    private String cardID;

    PurchaseForm(String generatedCardID) {

        JComboBox<String> purchaseTypeCombo = new JComboBox<>();

        DefaultComboBoxModel<String> options = new DefaultComboBoxModel<>();
        options.addElement("Please Choose Below");
        options.addElement("Purchase with Existing Card");
        options.addElement("Purchase withNew Card");
        options.addElement("Cash Purchase");

        purchaseTypeCombo.setModel(options);
        purchaseTypeCombo.setSelectedIndex(0);
        purchaseTypeCombo.setEditable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        gridBagLayoutComponents();

    }

    public void gridBagLayoutComponents() {

        GridBagConstraints gc = new GridBagConstraints();

        /*========== 1st Row ==========*/
        gc.gridy = 0;

        gc.gridx = 0;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(10,0,10,0);

        /*========== 2nd Row ==========*/
        gc.gridy++;

        gc.gridx = 0;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(10,0,10,0);

        /*========== Last Row ==========*/
        gc.gridy++;

        gc.gridx = 0;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(10,0,30,0);

    }

    private void existingCardPurchase() {

    }

    private void newCardPurchase() {

    }

    private void cashPurchase() {

    }

}
