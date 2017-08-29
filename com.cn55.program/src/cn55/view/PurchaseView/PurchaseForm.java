package cn55.view.PurchaseView;

import cn55.view.ButtonListener;
import cn55.view.CardView.CardType;
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

public class PurchaseForm extends JPanel {

    private int generatedReceiptID;
    private String generatedCardID;

    private DefaultComboBoxModel<String> existingCardModel;
    private ArrayList<String> categoriesList;

    private JComboBox<String> purchaseTypeCombo;
    private DefaultComboBoxModel<String> options;

    private JPanel createPurchaseFormPanel;
    private FormLabel receiptIDLabel;
    private FormTextField receiptIDTextField;
    private JLabel cardIDLabel;
    private JTextField cardIDTextField;
    private JLabel existingCardLabel;
    private JComboBox<String> existingCardCombo;

    private ButtonGroup cardTypeRBGroup;
    private JLabel cardTypeLabel;
    private JRadioButton anonCardRB;
    private JRadioButton basicCardRB;
    private JRadioButton premiumCardRB;

    private JLabel cardNameLabel;
    private JTextField cardNameTextField;
    private JLabel cardEmailLabel;
    private JTextField cardEmailTextField;

    private HashMap<FormLabel, FormTextField> categoriesMap;

    private ButtonListener cancelListener;
    private JButton createBtn;
    private JButton clearBtn;

    /* CONSTRUCTOR */
    PurchaseForm() {
        /* INITIALIZE ALL COMPONENTS */
        purchaseTypeCombo = new JComboBox<>();
        options = new DefaultComboBoxModel<>();
        JButton cancelBtn = new JButton(" Cancel New Purchase ");

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
        Border inInnerBorder = BorderFactory.createEmptyBorder(30,30,30,30);
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

    /*===================================================*/
    /*==================== BASE FORM ====================*/
    /*===================================================*/

    public void createPurchaseFormPanel() {
        /* CREATE BASE CARD FORM WITH PURCHASE - PANEL */
        createPurchaseFormPanel = new JPanel();

        receiptIDLabel = new FormLabel("Receipt ID: ");
        receiptIDTextField = new FormTextField(20);
        cardIDLabel = new JLabel("Card ID: ");
        cardIDTextField = new JTextField(20);
        existingCardLabel = new JLabel("Select Existing Card ID: ");
        existingCardCombo = new JComboBox<>();

        cardTypeLabel = new JLabel("Card Type: ");
        cardTypeRBGroup = new ButtonGroup();
        anonCardRB = new JRadioButton(" Anon Card");
        basicCardRB = new JRadioButton(" Basic Card");
        premiumCardRB = new JRadioButton(" Premium Card");

        cardNameLabel = new JLabel("Customer Name: ");
        cardNameTextField = new JTextField(20);
        cardEmailLabel = new JLabel("Customer Email: ");
        cardEmailTextField = new JTextField(20);

        createBtn = new JButton(" Add Purchase ");
        clearBtn = new JButton(" Clear ");

        createPurchaseFormPanel.setLayout(new GridBagLayout());
        this.add(createPurchaseFormPanel, BorderLayout.CENTER);

        createCategoriesListForm();

        receiptIDTextField.setText(Integer.toString(generatedReceiptID));

        /* SET UP CARD TYPE GROUP */
        cardTypeRBGroup.add(anonCardRB);
        cardTypeRBGroup.add(basicCardRB);
        cardTypeRBGroup.add(premiumCardRB);
        anonCardRB.setSelected(true);
        anonCardRB.setActionCommand(CardType.AnonCard.getName());
        basicCardRB.setActionCommand(CardType.BasicCard.getName());
        premiumCardRB.setActionCommand(CardType.PremiumCard.getName());

        GridBagConstraints gc = new GridBagConstraints();
        Insets labelInset = new Insets(10,0,0,10);

        /*========== FIRST ROW - RECEIPT ID ==========*/
        gc.fill = GridBagConstraints.NONE;
        gc.gridy = 0; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.02;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(20,0,0,10);

        //receiptIDLabel.setFont(Style.labelFont());
        createPurchaseFormPanel.add(receiptIDLabel, gc);
        receiptIDLabel.setVisible(false);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(20,0,0,10);

        receiptIDTextField.setEditable(false);
        createPurchaseFormPanel.add(receiptIDTextField, gc);

        /*========== NEW ROW - CARD ID ==========*/
        labelGridConstraints(gc);
        cardIDLabel.setFont(Style.labelFont());
        createPurchaseFormPanel.add(cardIDLabel, gc);
        cardIDLabel.setVisible(false);

        textFieldGridConstraints(gc);
        cardIDTextField.setFont(Style.textFieldFont());
        cardIDTextField.setEditable(false);
        createPurchaseFormPanel.add(cardIDTextField, gc);
        cardIDTextField.setVisible(false);

        /*========== NEW ROW - EXISTING CARD ID ==========*/
        labelGridConstraints(gc);
        existingCardLabel.setFont(Style.labelFont());
        createPurchaseFormPanel.add(existingCardLabel, gc);
        existingCardLabel.setVisible(false);

        textFieldGridConstraints(gc);
        existingCardCombo.setFont(Style.textFieldFont());
        existingCardCombo.setEditable(false);
        existingCardCombo.setPreferredSize(receiptIDTextField.getPreferredSize());
        createPurchaseFormPanel.add(existingCardCombo, gc);
        existingCardCombo.setVisible(false);

        /*========== NEW ROW - CARD TYPE BUTTON GROUP ==========*/
            /* LABEL FOR RADIO GROUP + RADIO BUTTON 1 */
            gc.gridy++; gc.gridx = 0; gc.weighty = 0.005;
            gc.anchor = GridBagConstraints.LINE_END;
            gc.insets = labelInset;

            cardTypeLabel.setFont(Style.labelFont());
            createPurchaseFormPanel.add(cardTypeLabel, gc);
            cardTypeLabel.setVisible(false);

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
        cardNameLabel.setFont(Style.labelFont());
        createPurchaseFormPanel.add(cardNameLabel, gc);
        cardNameLabel.setVisible(false);

        textFieldGridConstraints(gc);
        gc.insets = new Insets(20,0,0,0);

        cardNameTextField.setFont(Style.textFieldFont());
        createPurchaseFormPanel.add(cardNameTextField, gc);
        cardNameTextField.setVisible(false);

        /*========== NEW ROW ==========*/
        labelGridConstraints(gc);
        cardEmailLabel.setFont(Style.labelFont());
        createPurchaseFormPanel.add(cardEmailLabel, gc);
        cardEmailLabel.setVisible(false);

        textFieldGridConstraints(gc);
        cardEmailTextField.setFont(Style.textFieldFont());
        createPurchaseFormPanel.add(cardEmailTextField, gc);
        cardEmailTextField.setVisible(false);

        /*========== NEW ROW - CATEGORIES LIST ==========*/
        for (HashMap.Entry<FormLabel, FormTextField> item : categoriesMap.entrySet()) {
            labelGridConstraints(gc);
            gc.insets = new Insets(10,0,0,0);
            createPurchaseFormPanel.add(item.getKey(), gc);
            textFieldGridConstraints(gc);
            createPurchaseFormPanel.add(item.getValue(), gc);
        }

        /*========== BUTTON ROW ==========*/
        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(20,0,0,10);

        createBtn.setFont(new Font("Verdana", Font.BOLD, 20));
        createBtn.setBackground(Style.red500());
        createBtn.setForeground(Color.WHITE);
        createPurchaseFormPanel.add(createBtn, gc);
        createBtn.setVisible(false);

        // gc.gridy - take the gridy from previous button -
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(20,10,0,0);

        clearBtn.setFont(new Font("Verdana", Font.BOLD, 20));
        clearBtn.setBackground(Style.red500());
        clearBtn.setForeground(Color.WHITE);
        createPurchaseFormPanel.add(clearBtn, gc);
        clearBtn.setVisible(false);
    }

    private void basePurchaseForm() {
        for (Component item : createPurchaseFormPanel.getComponents()) {
            createPurchaseFormPanel.remove(item);
        }
    }

    private void existingCardPurchase() {
        System.out.println("Existing Card Purchase");

        receiptIDLabel.setVisible(true);
        receiptIDTextField.setVisible(true);

        cardIDLabel.setVisible(false);
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

        for (HashMap.Entry<FormLabel, FormTextField> item : categoriesMap.entrySet()) {
            item.getKey().setVisible(true);
            item.getValue().setVisible(true);
        }

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

        /*if (existingCardCombo.getActionCommand().equals("Please Select")) {
            createBtn.setEnabled(false);
        } else {
            createBtn.setEnabled(true);
        }*/
    }

    private void newCardPurchase() {
        System.out.println("New Card Purchase");

        receiptIDLabel.setVisible(true);
        receiptIDTextField.setVisible(true);

        existingCardLabel.setVisible(false);
        existingCardCombo.setVisible(false);
        cardIDLabel.setVisible(true);
        cardIDTextField.setText(generatedCardID);
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

        createBtn.setVisible(true);
        clearBtn.setVisible(true);
    }

    private void cashPurchase() {
        System.out.println("Cash Purchase");

        receiptIDLabel.setVisible(true);
        receiptIDTextField.setVisible(true);

        cardIDLabel.setVisible(true);
        cardIDTextField.setText("Cash");
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

        createBtn.setEnabled(true);
        createBtn.setVisible(true);
        clearBtn.setVisible(true);
    }

    /*==================== MUTATORS ====================*/

    public void setCancelPurchaseListener(ButtonListener listener) { this.cancelListener = listener; }

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
