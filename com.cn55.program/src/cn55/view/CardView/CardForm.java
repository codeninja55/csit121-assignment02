package cn55.view.CardView;

import cn55.model.CardType;
import cn55.view.ButtonListener;
import cn55.view.CustomComponents.FormButton;
import cn55.view.CustomComponents.FormLabel;
import cn55.view.CustomComponents.FormTextField;
import cn55.view.CustomComponents.Style;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

public class CardForm extends JPanel {
    private HashMap<String,String> newCard;
    private String generatedCardID;

    private JComboBox<String> cardTypeCombo;
    private DefaultComboBoxModel<String> options;

    private JPanel createCardForm;
    /* TODO - Cannot use a generatedCardID because everytime you create the form it will increase the card idCounter */
    private FormLabel cardIDLabel;
    private FormTextField cardIDTextField;
    private FormLabel cardNameLabel;
    private FormTextField cardNameTextField;
    private FormLabel cardEmailLabel;
    private FormTextField cardEmailTextField;

    private CardListener cardListener;
    private ButtonListener cancelListener;
    private FormButton createBtn;
    private FormButton clearBtn;

    /*====================  CONSTRUCTOR for Creating Cards ====================*/
    public CardForm() {
        /* INITIALIZE ALL COMPONENTS */
        cardTypeCombo = new JComboBox<>();
        options = new DefaultComboBoxModel<>();
        JButton cancelBtn = new JButton("Cancel New Card");

        /* NOTE: All FormLabels and FormTextFields are hidden by default */
        /*cardIDLabel = new FormLabel("Card ID: ");
        cardIDTextField = new FormTextField(20);*/
        cardIDTextField.setText(generatedCardID);
        cardNameLabel = new FormLabel("Name: ");
        cardNameTextField = new FormTextField(20);
        cardEmailLabel = new FormLabel("Email: ");
        cardEmailTextField = new FormTextField(20);

        createBtn = new FormButton("Add Card");
        clearBtn = new FormButton("Clear");

        setName("CardForm");
        setLayout(new BorderLayout());
        /* SIZING - Make sure the form is at least always 800 pixels */
        Dimension dim = getPreferredSize();
        dim.width = 800;
        setPreferredSize(dim);
        setMinimumSize(getPreferredSize());

        /* BORDERS - Adding 3 Borders around the form */
        setBorder(Style.formBorder("New Card"));

        /* CARD TYPE COMBO BOX - Create the model and the combobox */
        options.addElement("Please Choose Card Type Below");
        options.addElement(CardType.AnonCard.getName());
        options.addElement(CardType.BasicCard.getName());
        options.addElement(CardType.PremiumCard.getName());

        cardTypeCombo.setModel(options);
        cardTypeCombo.setSelectedIndex(0);
        cardTypeCombo.setEditable(false);
        cardTypeCombo.setFont(Style.comboboxFont());
        add(cardTypeCombo, BorderLayout.NORTH);

        cancelBtn.setFont(Style.buttonFont());
        cancelBtn.setForeground(Style.btnTextColor());
        cancelBtn.setBackground(Style.red500());
        add(cancelBtn, BorderLayout.SOUTH);

        /* CANCEL BUTTON LISTENER */
        cancelBtn.addActionListener(e -> {
            if (cancelListener != null) {
                cancelListener.buttonActionOccurred();
            }
        });

        /* COMBO BOX LISTENER */
        cardTypeCombo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (e.getItem().equals(options.getElementAt(0))) {
                        baseCardForm();
                    } else if (e.getItem().equals(options.getElementAt(1))) {
                        anonCardForm();
                    } else if (e.getItem().equals(options.getElementAt(2))) {
                        basicCardForm();
                    } else if (e.getItem().equals(options.getElementAt(3))) {
                        premiumCardForm();
                    }
                }
            }
        });

        setVisible(false);
    }

    /*============================== BASE CREATE CARD FORM ==============================*/
    public void createCardForm() {
        /* CREATE BASE FORM - PANEL */
        createCardForm = new JPanel();

        createCardForm.setLayout(new GridBagLayout());
        this.add(createCardForm, BorderLayout.CENTER);

        GridBagConstraints gc = new GridBagConstraints();

        /*========== FIRST ROW - CARD ID ==========*/
        gc.fill = GridBagConstraints.NONE;
        gc.gridy = 0; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.02;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(20,0,0,10);

        createCardForm.add(cardIDLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(20,0,0,0);

        cardIDTextField.setEditable(false);
        createCardForm.add(cardIDTextField, gc);



        /*========== BUTTON ROW ==========*/
        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(20,0,0,10);

        createCardForm.add(createBtn, gc);

        createBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardEvent event = new CardEvent(this);
                if (cardListener != null)
                    cardListener.formActionOccurred();
            }
        });

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(20,10,0,0);

        createCardForm.add(clearBtn, gc);

        /* BY DEFAULT CLEAR ALL TEXT FIELDS */
        for (Component item : createCardForm.getComponents()) {
            if (item instanceof FormTextField) ((FormTextField) item).setText("");
        }

        setVisible(false);
    }

    private void baseCardForm() {

    }

    private void anonCardForm() {

    }

    private void basicCardForm() {

    }

    private void premiumCardForm() {

    }

    /*============================== LISTENER CALLBACKS ==============================*/

    public void setCardListener(CardListener cardListener) {
        this.cardListener = cardListener;
    }

    public void setCancelListener(ButtonListener listener) { this.cancelListener = listener; }

    /*====================  BASE FORMS ====================*/
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

        // TODO make a temp card
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

        JTextField cardIDTextField = new JTextField(cardID, 15);
        cardIDTextField.setEditable(false);
        JTextField name = new JTextField(30);
        JTextField email = new JTextField(30);

        JPanel createCardForm = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10,0,30,10);
        //JTextField totalAmount = new JTextField();
        //panel.add(new JLabel("Card ID: "), gc);
        createCardForm.add(new JLabel("Customer Name: "), gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10,0,30,0);
        //panel.add(cardIDTextField, gc);
        createCardForm.add(name, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(10,0,0,5);
        createCardForm.add(new JLabel("Customer Email: "), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(10,0,0,0);
        createCardForm.add(email, gc);

        /*gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(10,0,50,5);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(10,0,50,0);*/

        /*========== BUTTON ROW ==========*/
        gc.gridy++; gc.gridx = 0; gc.weightx = 0.5; gc.weighty = 2; gc.gridwidth = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(20,0,0,10);
        createCardForm.add(createBtn, gc);

        gc.gridx = 1; gc.weightx = 1.5;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(20,10,0,0);
        createCardForm.add(clearBtn, gc);

        int confirm = JOptionPane.showConfirmDialog(null,
                createCardForm,
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

    /*============================== MUTATORS  ==============================*/
    public void setGeneratedCardID(String cardID) { this.generatedCardID = cardID; }

    private void labelGridConstraints(GridBagConstraints gc) {
        gc.gridy++;
        gc.gridx = 0;
        gc.insets = new Insets(10,0,0,10);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
    }

    private void textFieldGridConstraints(GridBagConstraints gc) {
        gc.gridx = 1;
        gc.insets = new Insets(10,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
    }

    /*============================== ACCESSORS  ==============================*/

    public JComboBox<String> getCardTypeCombo() {
        return cardTypeCombo;
    }
    public JPanel getCreateCardForm() {
        return createCardForm;
    }
    public HashMap<String, String> getCardMap() { return newCard; }
    public String getCardID() { return generatedCardID; }
}
