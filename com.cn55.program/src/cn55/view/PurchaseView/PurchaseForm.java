package cn55.view.PurchaseView;

import cn55.view.ButtonListener;
import cn55.view.CardView.CardType;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PurchaseForm extends JPanel {

    private int generatedReceiptID;
    private String generatedCardID;

    private DefaultComboBoxModel<String> existingCardModel;
    private ArrayList<String> categoriesList;
    private HashMap<FormLabel, FormTextField> categoriesMap;

    private JComboBox<String> purchaseTypeCombo;
    private DefaultComboBoxModel<String> options;

    private JPanel createPurchaseFormPanel;
    private FormLabel receiptIDLabel;
    private FormTextField receiptIDTextField;
    private FormLabel cardIDLabel;
    private FormTextField cardIDTextField;
    private FormLabel existingCardLabel;
    private JComboBox<String> existingCardCombo;

    private ButtonGroup cardTypeRBGroup;
    private FormLabel cardTypeLabel;
    private JRadioButton anonCardRB;
    private JRadioButton basicCardRB;
    private JRadioButton premiumCardRB;

    private FormLabel cardNameLabel;
    private FormTextField cardNameTextField;
    private FormLabel cardEmailLabel;
    private FormTextField cardEmailTextField;

    private PurchaseListener createPurchaseListener;
    private ButtonListener cancelListener;
    private FormButton createBtn;
    private FormButton clearBtn;

    /* CONSTRUCTOR */
    PurchaseForm() {
        /* INITIALIZE ALL COMPONENTS */
        purchaseTypeCombo = new JComboBox<>();
        options = new DefaultComboBoxModel<>();
        JButton cancelBtn = new JButton(" Cancel New Purchase ");

        /* NOTE: All FormLabels and FormTextField are hidden by default */
        receiptIDLabel = new FormLabel("Receipt ID: ");
        receiptIDTextField = new FormTextField(20);
        receiptIDTextField.setText(Integer.toString(generatedReceiptID));
        cardIDLabel = new FormLabel("Card ID: ");
        cardIDTextField = new FormTextField(20);
        existingCardLabel = new FormLabel("Select Existing Card ID: ");
        existingCardCombo = new JComboBox<>();

        cardTypeLabel = new FormLabel("Card Type: ");
        cardTypeRBGroup = new ButtonGroup();
        anonCardRB = new JRadioButton(CardType.AnonCard.getName());
        basicCardRB = new JRadioButton(CardType.BasicCard.getName());
        premiumCardRB = new JRadioButton(CardType.PremiumCard.getName());

        cardNameLabel = new FormLabel("Customer Name: ");
        cardNameTextField = new FormTextField(20);
        cardEmailLabel = new FormLabel("Customer Email: ");
        cardEmailTextField = new FormTextField(20);

        createBtn = new FormButton(" Add Purchase ");
        clearBtn = new FormButton(" Clear ");

        /* SET UP CARD TYPE GROUP */
        cardTypeRBGroup.add(anonCardRB);
        cardTypeRBGroup.add(basicCardRB);
        cardTypeRBGroup.add(premiumCardRB);
        anonCardRB.setSelected(true);

        setLayout(new BorderLayout());
        /* SIZING - Make sure the form is at least always 600 pixels */
        Dimension dim = getPreferredSize();
        dim.width = 600;
        setPreferredSize(dim);
        setMinimumSize(getPreferredSize());

        /* BORDERS - Adding 3 Borders around the form */
        Border outInnerBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK),
                "New Purchase",
                TitledBorder.LEFT,
                TitledBorder.CENTER,
                new Font("Verdana",Font.BOLD,22));
        Border inInnerBorder = BorderFactory.createEmptyBorder(25,25,25,25);
        Border innerBorder = BorderFactory.createCompoundBorder(outInnerBorder, inInnerBorder);
        Border outerBorder = BorderFactory.createEmptyBorder(1,10,10,10);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        /* PURCHASE TYPE COMBO BOX - Create the combo box and its model */
        options.addElement("Please Choose Purchase Type Below");
        options.addElement("Purchase with Existing Card");
        options.addElement("Purchase with New Card");
        options.addElement("Cash Purchase");

        purchaseTypeCombo.setModel(options);
        purchaseTypeCombo.setSelectedIndex(0);
        purchaseTypeCombo.setEditable(false);
        purchaseTypeCombo.setFont(Style.comboboxFont());
        add(purchaseTypeCombo, BorderLayout.NORTH);

        cancelBtn.setFont(Style.buttonFont());
        cancelBtn.setForeground(Style.btnTextColor());
        cancelBtn.setBackground(Style.red500());
        add(cancelBtn, BorderLayout.SOUTH);

        /*========== COMBO BOX LISTENER ==========*/
        purchaseTypeCombo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (e.getItem().equals(options.getElementAt(0))) {
                        basePurchaseForm();
                    } else if (e.getItem().equals(options.getElementAt(1))) {
                        existingCardPurchase();
                    } else if (e.getItem().equals(options.getElementAt(2))) {
                        newCardPurchase();
                    } else if (e.getItem().equals(options.getElementAt(3))) {
                        cashPurchase();
                    }
                }
            }
        });

        /*========== CANCEL BUTTON LISTENER ==========*/
        cancelBtn.addActionListener(e -> {
            if (cancelListener != null) {
                cancelListener.buttonActionOccurred();
            }
        });

        /*cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cancelListener != null) {
                    cancelListener.buttonActionOccurred();
                }
            }
        });*/
        setVisible(false);
    }

    /*==================== BASE FORM ====================*/

    public void createPurchaseFormPanel() {
        /* CREATE BASE CARD FORM WITH PURCHASE - PANEL */
        createPurchaseFormPanel = new JPanel();

        createPurchaseFormPanel.setLayout(new GridBagLayout());
        this.add(createPurchaseFormPanel, BorderLayout.CENTER);

        /* Dynamically creates categoriesMap HashMap<FormLabel, FormTextField> */
        createCategoriesListForm();

        GridBagConstraints gc = new GridBagConstraints();

        /*========== FIRST ROW - RECEIPT ID ==========*/
        gc.fill = GridBagConstraints.NONE;
        gc.gridy = 0; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.02;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(20,0,0,10);

        createPurchaseFormPanel.add(receiptIDLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(20,0,0,10);

        receiptIDTextField.setEditable(false);
        createPurchaseFormPanel.add(receiptIDTextField, gc);

        /*========== NEW ROW - CARD ID ==========*/
        labelGridConstraints(gc);
        createPurchaseFormPanel.add(cardIDLabel, gc);

        textFieldGridConstraints(gc);
        createPurchaseFormPanel.add(cardIDTextField, gc);

        /*========== NEW ROW - EXISTING CARD ID ==========*/
        labelGridConstraints(gc);
        createPurchaseFormPanel.add(existingCardLabel, gc);

        textFieldGridConstraints(gc);
        existingCardCombo.setFont(Style.textFieldFont());
        existingCardCombo.setEditable(false);
        existingCardCombo.setPreferredSize(new Dimension(224,25));
        existingCardCombo.setMinimumSize(existingCardCombo.getPreferredSize());
        createPurchaseFormPanel.add(existingCardCombo, gc);
        existingCardCombo.setVisible(false);

        /*========== NEW ROW - CARD TYPE BUTTON GROUP ==========*/
            /* LABEL FOR RADIO GROUP + RADIO BUTTON 1 */
            gc.gridy++; gc.gridx = 0; gc.weighty = 0.005;
            gc.anchor = GridBagConstraints.LINE_END;
            gc.insets = new Insets(10,0,0,10);
            createPurchaseFormPanel.add(cardTypeLabel, gc);

            textFieldGridConstraints(gc);
            anonCardRB.setFont(Style.labelFont());
            createPurchaseFormPanel.add(anonCardRB, gc);
            anonCardRB.setVisible(false);

            /*========== NEW ROW - RADIO BUTTON 2 ==========*/
            textFieldGridConstraints(gc);
            gc.gridy++;
            basicCardRB.setFont(Style.labelFont());
            createPurchaseFormPanel.add(basicCardRB, gc);
            basicCardRB.setVisible(false);

            /*========== NEW ROW - RADIO BUTTON 3 ==========*/
            textFieldGridConstraints(gc);
            gc.gridy++;
            premiumCardRB.setFont(Style.labelFont());
            createPurchaseFormPanel.add(premiumCardRB, gc);
            premiumCardRB.setVisible(false);

        /*========== NEW ROW ==========*/
        labelGridConstraints(gc);
        gc.weighty = 0.02;
        gc.insets = new Insets(20,0,0,5);
        createPurchaseFormPanel.add(cardNameLabel, gc);

        textFieldGridConstraints(gc);
        gc.insets = new Insets(20,0,0,0);
        createPurchaseFormPanel.add(cardNameTextField, gc);

        /*========== NEW ROW ==========*/
        labelGridConstraints(gc);
        createPurchaseFormPanel.add(cardEmailLabel, gc);

        textFieldGridConstraints(gc);
        createPurchaseFormPanel.add(cardEmailTextField, gc);

        /*========== NEW ROW - CATEGORIES LIST ==========*/
        for (HashMap.Entry<FormLabel, FormTextField> item : categoriesMap.entrySet()) {
            labelGridConstraints(gc);
            gc.insets = new Insets(10,0,0,5);
            item.getKey().setName("Cat");
            createPurchaseFormPanel.add(item.getKey(), gc);
            item.getValue().setName("Cat");
            textFieldGridConstraints(gc);
            createPurchaseFormPanel.add(item.getValue(), gc);
        }

        /*========== BUTTON ROW ==========*/
        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(20,0,0,10);

        createPurchaseFormPanel.add(createBtn, gc);

        createBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String receiptIDStr = receiptIDTextField.getText();
                int receiptID = Integer.parseInt(receiptIDStr);

                CardType cardType;
                if (anonCardRB.isSelected()) {
                    cardType = CardType.AnonCard;
                } else if (basicCardRB.isSelected()) {
                    cardType = CardType.BasicCard;
                } else if (premiumCardRB.isSelected()) {
                    cardType = CardType.PremiumCard;
                }

                /* CATEGORIES */
                Map<String, Double> categories = new HashMap<>();
                for (HashMap.Entry<FormLabel, FormTextField> item : categoriesMap.entrySet()) {
                    System.out.println(item.getValue().getText());
                    String textFieldStr = item.getValue().getText();
                    String labelStr = item.getKey().getText();

                    if (textFieldStr.isEmpty()) {
                        categories.put(, 0);
                    } else if (textFieldStr)
                }

                String cardID;
                if (purchaseTypeCombo.getSelectedItem() == options.getElementAt(1)) {
                    System.out.println("ADDING: Purchase with Existing Card");
                    cardID = (String)existingCardCombo.getSelectedItem();

                } else if (purchaseTypeCombo.getSelectedItem() == options.getElementAt(2)) {
                    System.out.println("ADDING: Purchase with New Card");
                    cardID = cardIDTextField.getText();
                } else if (purchaseTypeCombo.getSelectedItem() == options.getElementAt(3)) {
                    System.out.println("ADDING: Cash Purchase");
                    cardID = cardIDTextField.getText();
                    /* Purchase(Map<String, Double> categories, int receiptID) */
                }

                PurchaseEvent event = new PurchaseEvent(
                        this);

                if (createPurchaseListener != null) {
                    createPurchaseListener.formActionOccurred(event);
                }
            }
        });

        // gc.gridy - take the gridy from previous button -
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(20,10,0,0);

        createPurchaseFormPanel.add(clearBtn, gc);
    }

    private void basePurchaseForm() {
        for (Component item : createPurchaseFormPanel.getComponents()) {
            item.setVisible(false);
        }
    }

    private void existingCardPurchase() {
        receiptIDLabel.setVisible(true);
        receiptIDTextField.setText(Integer.toString(generatedReceiptID));
        receiptIDTextField.setVisible(true);

        cardIDLabel.setVisible(false);
        cardIDTextField.setText(null);
        cardIDTextField.setVisible(false);

        existingCardCombo.setModel(existingCardModel);
        existingCardCombo.setSelectedIndex(0);
        existingCardLabel.setVisible(true);
        existingCardCombo.setVisible(true);

        cardTypeLabel.setVisible(false);
        anonCardRB.setVisible(false);
        basicCardRB.setVisible(false);
        premiumCardRB.setVisible(false);

        cardNameLabel.setVisible(false);
        cardNameTextField.setVisible(false);
        cardEmailLabel.setVisible(false);
        cardEmailTextField.setVisible(false);

        setCategoriesVisible(true);

        createBtn.setEnabled(false);
        createBtn.setVisible(true);
        clearBtn.setVisible(true);

        existingCardCombo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (!e.getItem().equals(existingCardModel.getElementAt(0))) {
                    createBtn.setEnabled(true);
                } else {
                    createBtn.setEnabled(false);
                }
            }
        });
    }

    private void newCardPurchase() {
        receiptIDLabel.setVisible(true);
        receiptIDTextField.setVisible(true);

        existingCardLabel.setVisible(false);
        existingCardCombo.setVisible(false);
        cardIDLabel.setVisible(true);
        cardIDTextField.setText(null);
        cardIDTextField.setEnabled(true);
        cardIDTextField.setVisible(true);

        cardTypeLabel.setVisible(true);
        anonCardRB.setVisible(true);
        basicCardRB.setVisible(true);
        premiumCardRB.setVisible(true);

        /*========== RADIO BUTTON LISTENER ==========*/

        anonCardRB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardNameLabel.setVisible(false);
                cardNameTextField.setVisible(false);
                cardEmailLabel.setVisible(false);
                cardEmailTextField.setVisible(false);
            }
        });

        basicCardRB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableNameAndEmail();
            }
        });

        premiumCardRB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableNameAndEmail();
            }
        });

        setCategoriesVisible(true);

        createBtn.setVisible(true);
        clearBtn.setVisible(true);
    }

    private void cashPurchase() {
        receiptIDLabel.setVisible(true);
        receiptIDTextField.setVisible(true);

        cardIDLabel.setVisible(true);
        cardIDTextField.setText("Cash");
        cardIDTextField.setEnabled(false);
        cardIDTextField.setVisible(true);

        existingCardLabel.setVisible(false);
        existingCardCombo.setVisible(false);

        cardTypeLabel.setVisible(false);
        anonCardRB.setVisible(false);
        basicCardRB.setVisible(false);
        premiumCardRB.setVisible(false);

        cardNameLabel.setVisible(false);
        cardNameTextField.setVisible(false);
        cardEmailLabel.setVisible(false);
        cardEmailTextField.setVisible(false);

        setCategoriesVisible(true);

        createBtn.setEnabled(true);
        createBtn.setVisible(true);
        clearBtn.setVisible(true);
    }

    /*==================== MUTATORS ====================*/

    public void setCancelPurchaseListener(ButtonListener listener) { this.cancelListener = listener; }

    public void setCreatePurchaseListener(PurchaseListener listener) { this.createPurchaseListener = listener; }

    public void setGeneratedReceiptID(int generatedReceiptID) { this.generatedReceiptID = generatedReceiptID; }

    public void setGeneratedCardID(String generatedCardID) { this.generatedCardID = generatedCardID; }

    public void setCardModel(DefaultComboBoxModel<String> cardModel) { this.existingCardModel = cardModel; }

    public void setCategoriesList(ArrayList<String> categoriesList) {
        this.categoriesList = categoriesList;
    }

    private void createCategoriesListForm() {
        HashMap<FormLabel, FormTextField> categoriesMap = new HashMap<>();
        for (String category : categoriesList) {
            String categoryStr = category + ": $";
            categoriesMap.put(new FormLabel(categoryStr),
                    new FormTextField(20));
        }
        this.categoriesMap = categoriesMap;
    }

    private void setCategoriesVisible(boolean isVisible) {
        for (HashMap.Entry<FormLabel, FormTextField> item : categoriesMap.entrySet()) {
            item.getKey().setVisible(isVisible);
            item.getValue().setVisible(isVisible);
        }
    }

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

    private void enableNameAndEmail () {
        cardNameLabel.setVisible(true);
        cardNameTextField.setVisible(true);
        cardEmailLabel.setVisible(true);
        cardEmailTextField.setVisible(true);
    }

    /*==================== ACCESSORS ====================*/
    public JComboBox<String> getPurchaseTypeCombo() {
        return purchaseTypeCombo;
    }

    public JPanel getCreatePurchaseFormPanel() { return createPurchaseFormPanel; }
}
