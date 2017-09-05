package cn55.view.PurchaseView;

import cn55.model.CardType;
import cn55.model.Category;
import cn55.model.Database;
import cn55.model.PurchaseType;
import cn55.view.ButtonListener;
import cn55.view.CustomComponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

public class PurchaseForm extends JPanel {
    private int generatedReceiptID;

    private DefaultComboBoxModel<String> existingCardModel;
    private ArrayList<Category> categoriesList;
    private HashMap<JLabel[], FormTextField> categoriesMap;

    private JComboBox<String> purchaseTypeCombo;
    private DefaultComboBoxModel<String> options;

    private JPanel baseCreatePurchaseForm;
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
    public PurchaseForm() {
        /* INITIALIZE ALL COMPONENTS */
        purchaseTypeCombo = new JComboBox<>();
        options = new DefaultComboBoxModel<>();
        CancelButton cancelBtn = new CancelButton("Cancel New Purchase");

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

        /* INITIALIZE THIS PANEL */
        setLayout(new BorderLayout());
        /* SIZING - Make sure the form is at least always 800 pixels */
        Dimension dim = getPreferredSize();
        dim.width = 800;
        setPreferredSize(dim);
        setMinimumSize(getPreferredSize());
        setBorder(Style.formBorder("New Purchase"));

        /* SET UP CARD TYPE GROUP */
        cardTypeRBGroup.add(anonCardRB);
        cardTypeRBGroup.add(basicCardRB);
        cardTypeRBGroup.add(premiumCardRB);
        anonCardRB.setSelected(true);

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

        add(cancelBtn, BorderLayout.SOUTH);

        /*========== CANCEL BUTTON LISTENER ==========*/
        cancelBtn.addActionListener(e -> {
            if (cancelListener != null) {
                cancelListener.buttonActionOccurred();
            }
        });

        setVisible(false);
    }

    /*==================== BASE FORM ====================*/

    public void createBasePurchaseForm() {
        /* CREATE BASE FORM WITH PURCHASE - PANEL */
        baseCreatePurchaseForm = new JPanel(new GridBagLayout());

        this.add(baseCreatePurchaseForm, BorderLayout.CENTER);

        /* Dynamically creates categoriesMap HashMap<FormLabel, FormTextField> */
        createCategoriesListForm();

        GridBagConstraints gc = new GridBagConstraints();

        /*========== FIRST ROW - RECEIPT ID ==========*/
        gc.fill = GridBagConstraints.NONE;
        gc.gridy = 0; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.1;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(20,0,0,10);

        baseCreatePurchaseForm.add(receiptIDLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(20,0,0,10);

        receiptIDTextField.setName("ReceiptIDTextField");
        receiptIDTextField.setEditable(false);
        receiptIDTextField.setText(Integer.toString(generatedReceiptID));
        baseCreatePurchaseForm.add(receiptIDTextField, gc);

        /*========== NEW ROW - CARD ID ==========*/
        labelGridConstraints(gc);
        baseCreatePurchaseForm.add(cardIDLabel, gc);

        textFieldGridConstraints(gc);
        cardIDTextField.setEditable(false);
        baseCreatePurchaseForm.add(cardIDTextField, gc);

        /*========== NEW ROW - CARD ID ERROR LABEL ==========*/
        labelGridConstraints(gc);
        baseCreatePurchaseForm.add(new JLabel(""), gc);

        textFieldGridConstraints(gc);
        baseCreatePurchaseForm.add(cardIDErrorLabel, gc);

        /*========== NEW ROW - EXISTING CARD ID ==========*/
        labelGridConstraints(gc);
        baseCreatePurchaseForm.add(existingCardLabel, gc);

        textFieldGridConstraints(gc);
        existingCardCombo.setFont(Style.textFieldFont());
        existingCardCombo.setEditable(false);
        existingCardCombo.setPreferredSize(receiptIDTextField.getPreferredSize());
        existingCardCombo.setMinimumSize(existingCardCombo.getPreferredSize());
        baseCreatePurchaseForm.add(existingCardCombo, gc);
        existingCardCombo.setVisible(false);

        /*========== NEW ROW - CARD TYPE BUTTON GROUP ==========*/
            /* LABEL FOR RADIO GROUP + RADIO BUTTON 1 */
            gc.gridy++; gc.gridx = 0; gc.weighty = 0.005;
            gc.anchor = GridBagConstraints.LINE_END;
            gc.insets = new Insets(10,0,0,10);
            baseCreatePurchaseForm.add(cardTypeLabel, gc);

            textFieldGridConstraints(gc);
            anonCardRB.setFont(Style.labelFont());
            baseCreatePurchaseForm.add(anonCardRB, gc);
            anonCardRB.setVisible(false);

            /*========== NEW ROW - RADIO BUTTON 2 ==========*/
            textFieldGridConstraints(gc);
            gc.gridy++;
            basicCardRB.setFont(Style.labelFont());
            baseCreatePurchaseForm.add(basicCardRB, gc);
            basicCardRB.setVisible(false);

            /*========== NEW ROW - RADIO BUTTON 3 ==========*/
            textFieldGridConstraints(gc);
            gc.gridy++;
            gc.insets = new Insets(10,0,20,0);
            premiumCardRB.setFont(Style.labelFont());
            baseCreatePurchaseForm.add(premiumCardRB, gc);
            premiumCardRB.setVisible(false);

        /*========== NEW ROW ==========*/
        labelGridConstraints(gc);
        gc.weighty = 0.1;
        gc.insets = new Insets(20,0,0,5);
        baseCreatePurchaseForm.add(cardNameLabel, gc);

        textFieldGridConstraints(gc);
        gc.insets = new Insets(20,0,0,0);
        baseCreatePurchaseForm.add(cardNameTextField, gc);

        /*========== NEW ROW ==========*/
        labelGridConstraints(gc);
        gc.insets = new Insets(10,0,20,0);
        baseCreatePurchaseForm.add(cardEmailLabel, gc);

        textFieldGridConstraints(gc);
        gc.insets = new Insets(10,0,20,0);
        baseCreatePurchaseForm.add(cardEmailTextField, gc);

        /* TODO - ADD THIS INTO A JSCROLLPANE SO IT CAN BE SCROLLED */
        /*========== NEW ROW - CATEGORIES LIST ==========*/
        for (HashMap.Entry<JLabel[], FormTextField> item : categoriesMap.entrySet()) {
            labelGridConstraints(gc);
            baseCreatePurchaseForm.add((FormLabel)item.getKey()[0], gc);

            item.getValue().setFont(Style.textFieldFont());
            textFieldGridConstraints(gc);
            baseCreatePurchaseForm.add(item.getValue(), gc);

            labelGridConstraints(gc);
            baseCreatePurchaseForm.add(new JLabel(""), gc);

            textFieldGridConstraints(gc);
            baseCreatePurchaseForm.add((ErrorLabel)item.getKey()[1], gc);
        }

        /*========== NEW ROW - PURCHASE ERROR LABEL ==========*/
        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.1; gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(20,0,20,0);
        purchaseErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        purchaseErrorLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
        purchaseErrorLabel.setBorder(Style.formBorder(""));
        baseCreatePurchaseForm.add(purchaseErrorLabel, gc);

        /*========== BUTTON ROW ==========*/
        gc.gridy++; gc.gridx = 0; gc.weightx = 0.5; gc.weighty = 3; gc.gridwidth = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(20,0,0,10);
        baseCreatePurchaseForm.add(createBtn, gc);

        createBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PurchaseEvent event = new PurchaseEvent(this, purchaseTypeCombo,
                        generatedReceiptID, categoriesMap, receiptIDTextField,
                        cardIDLabel, cardIDTextField, cardIDErrorLabel,
                        existingCardCombo, anonCardRB, basicCardRB,
                        premiumCardRB, cardNameLabel, cardNameTextField,
                        cardEmailLabel, cardEmailTextField, purchaseErrorLabel);

                if (createPurchaseListener != null)
                    createPurchaseListener.formActionOccurred(event);
            }
        });

        gc.gridx = 1; gc.weightx = 0.5;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(20,10,0,0);
        baseCreatePurchaseForm.add(clearBtn, gc);

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Component c : baseCreatePurchaseForm.getComponents()) {
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
        for (Component item : baseCreatePurchaseForm.getComponents()) {
            if (item instanceof FormTextField) ((FormTextField) item).setText("");
        }

        receiptIDTextField.setText(Integer.toString(generatedReceiptID));

        setVisible(false);
    }

    private void basePurchaseForm() {
        hideErrorLabels();
        hideAllFormComponents(false);
    }

    private void existingCardPurchase() {
        hideErrorLabels();
        hideAllFormComponents(false);

        receiptIDLabel.setVisible(true);
        receiptIDTextField.setText(Integer.toString(generatedReceiptID));
        receiptIDTextField.setVisible(true);

        existingCardCombo.setModel(existingCardModel);
        existingCardCombo.setSelectedIndex(0);
        existingCardLabel.setVisible(true);
        existingCardCombo.setVisible(true);

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
        hideErrorLabels();
        hideAllFormComponents(false);
        receiptIDLabel.setVisible(true);
        receiptIDTextField.setVisible(true);

        cardIDLabel.setVisible(true);
        cardIDTextField.setText(Database.getNextCardID());
        cardIDTextField.setVisible(true);

        cardTypeLabel.setVisible(true);
        anonCardRB.setSelected(true);
        anonCardRB.setVisible(true);
        basicCardRB.setVisible(true);
        premiumCardRB.setVisible(true);

        enableNameAndEmail(false);

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
        hideErrorLabels();
        hideAllFormComponents(false);
        receiptIDLabel.setVisible(true);
        receiptIDTextField.setVisible(true);

        cardIDLabel.setVisible(true);
        cardIDTextField.setText("Cash");
        cardIDTextField.setVisible(true);

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

    public void setCategoriesList(ArrayList<Category> categoriesList) {
        this.categoriesList = categoriesList;
    }

    private void createCategoriesListForm() {
        HashMap<JLabel[], FormTextField> categoriesMap = new HashMap<>();
        for (int i = 0; i < categoriesList.size(); i++) {
            JLabel[] labelArr = new JLabel[2];
            String categoryStr = categoriesList.get(i).getName() + ": $";
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

    private void hideAllFormComponents(boolean isVisible) {
        for (Component item : baseCreatePurchaseForm.getComponents())
            item.setVisible(isVisible);
    }

    private void enableNameAndEmail (boolean isVisible) {
        cardNameLabel.setEnabled(isVisible);
        cardNameTextField.setEditable(isVisible);
        cardEmailLabel.setEnabled(isVisible);
        cardEmailTextField.setEditable(isVisible);
        cardNameLabel.setVisible(true);
        cardNameTextField.setVisible(true);
        cardEmailLabel.setVisible(true);
        cardEmailTextField.setVisible(true);
    }

    private void hideErrorLabels() {
        for (Component comp : baseCreatePurchaseForm.getComponents()) {
            if (comp instanceof ErrorLabel)
                comp.setVisible(false);
        }
    }

    /*============================== ACCESSORS  ==============================*/
    public JComboBox<String> getPurchaseTypeCombo() {
        return purchaseTypeCombo;
    }

    public JPanel getBaseCreatePurchaseForm() {
        return baseCreatePurchaseForm;
    }
}
