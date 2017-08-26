package cn55.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

public class PurchaseForm {

    private int generatedReceiptID;
    private HashMap<String, String> newPurchase;
    private JPanel panel;
    private JLabel purchaseTypeLabel;
    private JComboBox<String> purchaseTypeCombo;
    DefaultComboBoxModel<String> options;

    PurchaseForm(int generatedReceiptID) {

        this.generatedReceiptID = generatedReceiptID;

        purchaseTypeLabel = new JLabel("Choose a way to make a Purchase");
        purchaseTypeLabel.setFont(Style.labelFont());

        purchaseTypeCombo = new JComboBox<>();
        options = new DefaultComboBoxModel<>();
        options.addElement("Please Choose Below");
        options.addElement("Purchase with Existing Card");
        options.addElement("Purchase with New Card");
        options.addElement("Cash Purchase");
        purchaseTypeCombo.setModel(options);
        purchaseTypeCombo.setSelectedIndex(0);
        purchaseTypeCombo.setEditable(false);
        purchaseTypeCombo.setFont(Style.buttonFont());

        panel = new JPanel(new GridBagLayout());
        gridBagLayoutComponents();

        JOptionPane.showConfirmDialog(null,
                panel,
                "Purchase Type",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }

    @SuppressWarnings("Convert2Lambda")
    private void gridBagLayoutComponents() {

        GridBagConstraints gc = new GridBagConstraints();

        /*========== 1st Row ==========*/
        gc.gridy = 0;

        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.insets = new Insets(30,0,10,0);

        panel.add(purchaseTypeLabel, gc);

        /*========== 2nd Row ==========*/
        gc.gridy = 1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.insets = new Insets(10,0,10,0);

        panel.add(purchaseTypeCombo, gc);

        JLabel cardIDLabel = new JLabel("Card ID: ");
        JTextField cardIDTextField = new JTextField(15);

        gc.gridy = 2;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10,0,10,0);
        cardIDLabel.setFont(Style.labelFont());

        panel.add(cardIDLabel, gc);
        cardIDLabel.setVisible(false);

        gc.gridy = 2;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10,0,10,0);
        cardIDTextField.setFont(Style.textFieldFont());

        panel.add(cardIDTextField, gc);
        cardIDTextField.setVisible(false);

        /*========== Last Row ==========*/
        /*gc.gridy++;

        gc.gridx = 0;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(10,0,30,0);*/

        /*========== COMBO BOX LISTENER ==========*/
        purchaseTypeCombo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    if (e.getItem().equals(options.getElementAt(1))) {
                        existingCardPurchase();
                    } else if (e.getItem().equals(options.getElementAt(2))) {
                        newCardPurchase();
                    } else if (e.getItem().equals(options.getElementAt(3))) {
                        cashPurchase();
                    }
                }
            }
        });
    }

    private void existingCardPurchase() {
        System.out.println("Existing Card Purchase");
        JLabel cardIDLabel = new JLabel("Card ID: ");
        JTextField cardIDTextField = new JTextField(15);

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 2;
        gc.gridx = 0;
        //gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(10,0,10,0);
        cardIDLabel.setFont(Style.labelFont());

        panel.add(cardIDLabel, gc);

        gc.gridy = 2;
        gc.gridx = 1;
        //gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(10,0,10,0);
        cardIDTextField.setFont(Style.textFieldFont());

        panel.add(cardIDTextField, gc);

    }

    private void newCardPurchase() {
        System.out.println("New Card Purchase");

        JLabel cardIDLabel = new JLabel("Card ID: ");
        JTextField cardIDTextField = new JTextField(15);

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 2;
        gc.gridx = 0;
        //gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(10,0,10,0);
        cardIDLabel.setFont(Style.labelFont());

        panel.add(cardIDLabel, gc);

        gc.gridy = 2;
        gc.gridx = 1;
        //gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(10,0,10,0);
        cardIDTextField.setFont(Style.textFieldFont());

        cardIDTextField.setEditable(false);
        cardIDTextField.setText(Integer.toString(generatedReceiptID));
        panel.add(cardIDTextField, gc);

    }

    private void cashPurchase() {
        System.out.println("Cash Purchase");
    }

    /* INNER CLASS TO listen to event changes in ComboBox */
    public class ItemChangeListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Object item = e.getItem();
                // do something with object
            }
        }
    }
}
