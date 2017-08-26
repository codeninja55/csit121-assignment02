package cn55.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

class CardForm {

    private HashMap<String,String> newCard;
    private String cardID;

    /*====================  CONSTRUCTOR for Creating Cards ====================*/
    CardForm(String generatedCardID) {
        String[] cardType = {"Anon Card", "Basic Card", "Premium Card"};
        JComboBox<String> cardTypeCombo = new JComboBox<>(cardType);
        cardTypeCombo.setFont(Style.buttonFont());
        JLabel cardTypeComboLabel = new JLabel("Choose the Card Type to Create");
        cardTypeComboLabel.setFont(Style.labelFont());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        //gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(10,0,30,0);

        panel.add(cardTypeComboLabel, gc);

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
            if (cardTypeCombo.getSelectedItem() == "Anon Card") this.createAnonCard(generatedCardID);
            else this.createOtherCardForm(cardTypeCombo.getSelectedItem(), generatedCardID);
        }
    }

    /*==================== DEFAULT CONSTRUCTOR for Deleting Cards ====================*/
    CardForm() {
        deleteForm(false);
    }

    void deleteForm(boolean complete) {

        if (!complete) {
            JLabel deleteLabel = new JLabel("Input Card ID to Delete Card");
            JLabel cardIDLabel = new JLabel("Card ID: ");
            JTextField cardIDTextField = new JTextField(20);

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();
            gc.gridx = 1;
            gc.gridy = 0;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.CENTER;
            gc.insets = new Insets(10,0,30,0);
            deleteLabel.setFont(Style.labelFont());
            panel.add(deleteLabel, gc);

            gc.gridx = 0;
            gc.gridy = 1;
            gc.insets = new Insets(10,0,50,0);
            gc.anchor = GridBagConstraints.LINE_END;
            cardIDLabel.setFont(Style.labelFont());
            panel.add(cardIDLabel, gc);

            gc.gridx = 1;
            gc.gridy = 1;
            gc.insets = new Insets(10,0,50,0);
            gc.anchor = GridBagConstraints.LINE_START;
            cardIDTextField.setPreferredSize(new Dimension(350,50));
            cardIDTextField.setFont(Style.textFieldFont());
            panel.add(cardIDTextField, gc);

            int result = JOptionPane.showConfirmDialog(null,
                    panel,
                    "Delete Card",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION)
                this.cardID = cardIDTextField.getText();
            else
                System.out.println("Delete Cancelled");
        } else {
            this.deleteError();
        }

    }

    private void deleteError () {
        Object[] options = {"Yes", "No"};

        int confirm = JOptionPane.showOptionDialog(null,
                "The card does not exist." +
                        "\nWould you like to try again?",
                "Delete Card Error",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                options[0]);

        if (confirm == JOptionPane.OK_OPTION) this.deleteForm(false);
        else { System.out.println("Delete Redo Cancelled"); }
    }

    /*====================  FORMS for Creating Cards ====================*/
    private void createAnonCard(String cardID) {

        JTextField cardIDTextField = new JTextField(cardID, 15);
        cardIDTextField.setEditable(false);

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
        panel.add(cardIDTextField, gc);

        int confirm = JOptionPane.showConfirmDialog(null,
                panel,
                "Create Anon Card",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        // TODO make a temp card

        if (confirm == JOptionPane.OK_OPTION) {
            HashMap<String,String> cardMap = new HashMap<>();
            cardMap.put("cardType", "AnonCard");
            cardMap.put("cardID", cardID);

            this.newCard = cardMap;
        }
    }

    private void createOtherCardForm(Object cardChoice, String cardID) {

        String title, cardType;

        if (cardChoice.equals("Basic Card")) {
            title = "Create Basic Card";
            cardType = "BasicCard";
        } else {
            title = "Create Premium Card";
            cardType = "PremiumCard";
        }

        // TODO use generateCardID method from shop class
        JTextField cardIDTextField = new JTextField(cardID, 15);
        cardIDTextField.setEditable(false);
        JTextField name = new JTextField(30);
        JTextField email = new JTextField(30);

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
        panel.add(cardIDTextField, gc);

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

        if (confirm == JOptionPane.OK_OPTION) {
            HashMap<String,String> cardMap = new HashMap<>();

            cardMap.put("cardType", cardType);
            cardMap.put("cardID",cardID);
            cardMap.put("name", name.getText());
            cardMap.put("email", email.getText());

            this.newCard = cardMap;
        }
    }

    /*====================  ACCESSORS ====================*/

    HashMap<String, String> getCardMap() { return newCard; }
    String getCardID() { return cardID; }
}
