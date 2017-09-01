package cn55.view.PurchaseView;

import cn55.model.CardModel.CategoriesComparator;
import cn55.model.PurchaseType;
import cn55.view.ButtonListener;
import cn55.model.CardType;
import cn55.view.CustomComponents.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PurchaseForm extends JPanel {
    private int generatedReceiptID;

    private DefaultComboBoxModel<String> existingCardModel;
    private ArrayList<String> categoriesList;
    private HashMap<JLabel[], FormTextField> categoriesMap;

    private JComboBox<String> purchaseTypeCombo;
    private DefaultComboBoxModel<String> options;

    private JPanel createPurchaseForm;
    private FormLabel receiptIDLabel;
    private FormTextField receiptIDTextField;
    private FormLabel cardIDLabel;
    private FormTextField cardIDTextField;
    private ErrorLabel cardIDErrorLabel;
    private FormLabel existingCardLabel;
    private JComboBox<String> existingCardCombo;

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
    private ErrorLabel purchaseErrorLabel;

    /*============================== CONSTRUCTORS ==============================*/
    PurchaseForm() {
        /* INITIALIZE ALL COMPONENTS */
        purchaseTypeCombo = new JComboBox<>();
        options = new DefaultComboBoxModel<>();
        JButton cancelBtn = new JButton("Cancel New Purchase");

        /* NOTE: All FormLabels and FormTextField are hidden by default */
        receiptIDLabel = new FormLabel("Receipt ID: ");
        receiptIDTextField = new FormTextField(20);
        cardIDLabel = new FormLabel("Card ID: ");
        cardIDTextField = new FormTextField(20);
        cardIDErrorLabel = new ErrorLabel("INVALID CARD ID");
        existingCardLabel = new FormLabel("Select Existing Card: ");
        existingCardCombo = new JComboBox<>();

        cardTypeLabel = new FormLabel("Card Type: ");
        ButtonGroup cardTypeRBGroup = new ButtonGroup();
        anonCardRB = new JRadioButton(CardType.AnonCard.getName());
        basicCardRB = new JRadioButton(CardType.BasicCard.getName());
        premiumCardRB = new JRadioButton(CardType.PremiumCard.getName());

        cardNameLabel = new FormLabel("Customer Name: ");
        cardNameTextField = new FormTextField(20);
        cardEmailLabel = new FormLabel("Customer Email: ");
        cardEmailTextField = new FormTextField(20);

        createBtn = new FormButton("Add Purchase");
        clearBtn = new FormButton("Clear");
        purchaseErrorLabel = new ErrorLabel("PURCHASE NOT ADDED");

        /* SET UP CARD TYPE GROUP */
        cardTypeRBGroup.add(anonCardRB);
        cardTypeRBGroup.add(basicCardRB);
        cardTypeRBGroup.add(premiumCardRB);
        anonCardRB.setSelected(true);

        setLayout(new BorderLayout());
        /* SIZING - Make sure the form is at least always 800 pixels */
        Dimension dim = getPreferredSize();
        dim.width = 800;
        setPreferredSize(dim);
        setMinimumSize(getPreferredSize());
        setBorder(Style.formBorder("New Purchase"));

        /* PURCHASE TYPE COMBO BOX - Create the combo box and its model */
        options.addElement("Please Choose Purchase Type Below");
        options.addElement(PurchaseType.ExistingCardPurchase.getName());
        options.addElement(PurchaseType.NewCardPurchase.getName());
        options.addElement(PurchaseType.CashPurchase.getName());

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

        setVisible(false);
    }

    /*==================== BASE FORM ====================*/

    public void createPurchaseForm() {
        /* CREATE BASE FORM WITH PURCHASE - PANEL */
        createPurchaseForm = new JPanel();

        createPurchaseForm.setLayout(new GridBagLayout());
        this.add(createPurchaseForm, BorderLayout.CENTER);

        /* Dynamically creates categoriesMap HashMap<FormLabel, FormTextField> */
        createCategoriesListForm();

        GridBagConstraints gc = new GridBagConstraints();

        /*========== FIRST ROW - RECEIPT ID ==========*/
        gc.fill = GridBagConstraints.NONE;
        gc.gridy = 0; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.02;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(20,0,0,10);

        createPurchaseForm.add(receiptIDLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(20,0,0,10);

        receiptIDTextField.setName("ReceiptIDTextField");
        receiptIDTextField.setEditable(false);
        receiptIDTextField.setText(Integer.toString(generatedReceiptID));
        createPurchaseForm.add(receiptIDTextField, gc);

        /*========== NEW ROW - CARD ID ==========*/
        labelGridConstraints(gc);
        createPurchaseForm.add(cardIDLabel, gc);

        textFieldGridConstraints(gc);
        createPurchaseForm.add(cardIDTextField, gc);

        /*========== NEW ROW - CARD ID ERROR LABEL ==========*/
        labelGridConstraints(gc);
        createPurchaseForm.add(new JLabel(""), gc);

        textFieldGridConstraints(gc);
        createPurchaseForm.add(cardIDErrorLabel, gc);

        /*========== NEW ROW - EXISTING CARD ID ==========*/
        labelGridConstraints(gc);
        createPurchaseForm.add(existingCardLabel, gc);

        textFieldGridConstraints(gc);
        existingCardCombo.setFont(Style.textFieldFont());
        existingCardCombo.setEditable(false);
        existingCardCombo.setPreferredSize(new Dimension(224,25));
        existingCardCombo.setMinimumSize(existingCardCombo.getPreferredSize());
        createPurchaseForm.add(existingCardCombo, gc);
        existingCardCombo.setVisible(false);

        /*========== NEW ROW - CARD TYPE BUTTON GROUP ==========*/
            /* LABEL FOR RADIO GROUP + RADIO BUTTON 1 */
            gc.gridy++; gc.gridx = 0; gc.weighty = 0.005;
            gc.anchor = GridBagConstraints.LINE_END;
            gc.insets = new Insets(10,0,0,10);
            createPurchaseForm.add(cardTypeLabel, gc);

            textFieldGridConstraints(gc);
            anonCardRB.setFont(Style.labelFont());
            createPurchaseForm.add(anonCardRB, gc);
            anonCardRB.setVisible(false);

            /*========== NEW ROW - RADIO BUTTON 2 ==========*/
            textFieldGridConstraints(gc);
            gc.gridy++;
            basicCardRB.setFont(Style.labelFont());
            createPurchaseForm.add(basicCardRB, gc);
            basicCardRB.setVisible(false);

            /*========== NEW ROW - RADIO BUTTON 3 ==========*/
            textFieldGridConstraints(gc);
            gc.gridy++;
            premiumCardRB.setFont(Style.labelFont());
            createPurchaseForm.add(premiumCardRB, gc);
            premiumCardRB.setVisible(false);

        /*========== NEW ROW ==========*/
        labelGridConstraints(gc);
        gc.weighty = 0.02;
        gc.insets = new Insets(20,0,0,5);
        createPurchaseForm.add(cardNameLabel, gc);

        textFieldGridConstraints(gc);
        gc.insets = new Insets(20,0,0,0);
        createPurchaseForm.add(cardNameTextField, gc);

        /*========== NEW ROW ==========*/
        labelGridConstraints(gc);
        createPurchaseForm.add(cardEmailLabel, gc);

        textFieldGridConstraints(gc);
        createPurchaseForm.add(cardEmailTextField, gc);

        /*========== NEW ROW - CATEGORIES LIST ==========*/
        for (HashMap.Entry<JLabel[], FormTextField> item : categoriesMap.entrySet()) {
            labelGridConstraints(gc);
            gc.insets = new Insets(10,0,0,5);
            createPurchaseForm.add((FormLabel)item.getKey()[0], gc);

            item.getValue().setFont(Style.textFieldFont());
            textFieldGridConstraints(gc);
            createPurchaseForm.add(item.getValue(), gc);

            labelGridConstraints(gc);
            createPurchaseForm.add(new JLabel(""), gc);

            textFieldGridConstraints(gc);
            createPurchaseForm.add((ErrorLabel)item.getKey()[1], gc);
        }

        /*========== NEW ROW - PURCHASE ERROR LABEL ==========*/

        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.1; gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(20,0,20,0);
        purchaseErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        purchaseErrorLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
        purchaseErrorLabel.setBorder(Style.formBorder(""));
        createPurchaseForm.add(purchaseErrorLabel, gc);

        /*========== BUTTON ROW ==========*/
        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 2; gc.gridwidth = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(20,0,0,10);

        createPurchaseForm.add(createBtn, gc);

        createBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PurchaseEvent event = new PurchaseEvent(this, purchaseTypeCombo,
                        generatedReceiptID, categoriesMap, receiptIDTextField,
                        cardIDLabel, cardIDTextField, cardIDErrorLabel,
                        existingCardCombo, anonCardRB, basicCardRB,
                        premiumCardRB, cardNameLabel, cardNameTextField,
                        cardEmailLabel, cardEmailTextField, purchaseErrorLabel);

                if (createPurchaseListener != null) {
                    createPurchaseListener.formActionOccurred(event);
                }
            }
        });

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(20,10,0,0);

        createPurchaseForm.add(clearBtn, gc);

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Component c : createPurchaseForm.getComponents()) {
                    if (c instanceof JTextField && ((JTextField) c).isEditable())
                        ((JTextField) c).setText("");

                    if (c instanceof ErrorLabel)
                        ((ErrorLabel) c).setVisible(false);

                    if (c instanceof FormLabel)
                        c.setForeground(Color.BLACK);
                }
            }
        });

        /* BY DEFAULT CLEAR ALL TEXT FIELDS */
        for (Component item : createPurchaseForm.getComponents()) {
            if (item instanceof FormTextField) ((FormTextField) item).setText("");
        }

        receiptIDTextField.setText(Integer.toString(generatedReceiptID));
    }

    private void basePurchaseForm() {
        for (Component item : createPurchaseForm.getComponents()) {
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

        cardTypeLabel.setVisible(false);
        anonCardRB.setVisible(false);
        basicCardRB.setVisible(false);
        premiumCardRB.setVisible(false);

        existingCardCombo.setModel(existingCardModel);
        existingCardCombo.setSelectedIndex(0);
        existingCardLabel.setVisible(true);
        existingCardCombo.setVisible(true);

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
        anonCardRB.setSelected(true);
        anonCardRB.setVisible(true);
        basicCardRB.setVisible(true);
        premiumCardRB.setVisible(true);

        /*========== RADIO BUTTON REGISTRATION ==========*/

        anonCardRB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableNameAndEmail(false);
            }
        });

        basicCardRB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableNameAndEmail(true);
            }
        });

        premiumCardRB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableNameAndEmail(true);
            }
        });

        setCategoriesVisible(true);

        createBtn.setVisible(true);
        createBtn.setEnabled(true);
        clearBtn.setVisible(true);
    }

    private void cashPurchase() {
        receiptIDLabel.setVisible(true);
        receiptIDTextField.setVisible(true);

        cardIDLabel.setVisible(true);
        cardIDTextField.setText("Cash");
        cardIDTextField.setEditable(false);
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

    /*==================== LISTENER CALLBACKS ====================*/

    public void setCancelPurchaseListener(ButtonListener listener) { this.cancelListener = listener; }

    public void setCreatePurchaseListener(PurchaseListener listener) { this.createPurchaseListener = listener; }

    /*============================== MUTATORS  ==============================*/
    public void setGeneratedReceiptID(int generatedReceiptID) { this.generatedReceiptID = generatedReceiptID; }

    public void setCardModel(DefaultComboBoxModel<String> cardModel) { this.existingCardModel = cardModel; }

    public void setCategoriesList(ArrayList<String> categoriesList) {
        this.categoriesList = categoriesList;
    }

    private void createCategoriesListForm() {
        HashMap<JLabel[], FormTextField> categoriesMap = new HashMap<>();
        categoriesList.sort(new CategoriesComparator());
        /* TESTING */
        System.err.println("Purchase Form");
        System.out.println(categoriesList);
        for (int i = 0; i < categoriesList.size(); i++) {
            JLabel[] labelArr = new JLabel[2];
            String categoryStr = categoriesList.get(i) + ": $";
            labelArr[0] = new FormLabel(categoryStr);
            labelArr[1] = new ErrorLabel("INVALID AMOUNT");

            categoriesMap.put(labelArr, new FormTextField(20));
        }
        this.categoriesMap = categoriesMap;
    }

    private void setCategoriesVisible(boolean isVisible) {
        for (HashMap.Entry<JLabel[], FormTextField> item : categoriesMap.entrySet()) {
            item.getKey()[0].setVisible(isVisible);
            item.getKey()[1].setVisible(!isVisible);
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

    private void enableNameAndEmail (boolean isEnabled) {
        cardNameLabel.setVisible(isEnabled);
        cardNameTextField.setVisible(isEnabled);
        cardEmailLabel.setVisible(isEnabled);
        cardEmailTextField.setVisible(isEnabled);
    }

    /*============================== ACCESSORS  ==============================*/
    public JComboBox<String> getPurchaseTypeCombo() {
        return purchaseTypeCombo;
    }

    public JPanel getCreatePurchaseForm() { return createPurchaseForm; }
}
