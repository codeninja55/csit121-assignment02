package cn55.view.PurchaseView;

import cn55.model.CardType;
import cn55.view.CustomComponents.FormLabel;
import cn55.view.CustomComponents.FormTextField;

import javax.swing.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;

public class PurchaseEvent extends EventObject {

    private JPanel purchaseForm;
    private JComboBox<String> purchaseTypeCombo;

    private int generatedReceiptID;
    private HashMap<FormLabel, FormTextField> categoriesMap;

    private FormLabel receiptIDLabel;
    private FormTextField receiptIDTextField;
    private FormLabel cardIDLabel;
    private FormTextField cardIDTextField;
    private FormLabel existingCardLabel;
    private JComboBox<String> existingCardCombo;

    private JRadioButton anonCardRB;
    private JRadioButton basicCardRB;
    private JRadioButton premiumCardRB;

    private FormLabel cardNameLabel;
    private FormTextField cardNameTextField;
    private FormLabel cardEmailLabel;
    private FormTextField cardEmailTextField;

    public PurchaseEvent(Object source) {
        super(source);
    }

    public PurchaseEvent(Object source, JPanel purchaseForm, JComboBox<String> purchaseTypeCombo,
                         int generatedReceiptID, HashMap<FormLabel, FormTextField> categoriesMap,
                         FormLabel receiptIDLabel, FormTextField receiptIDTextField, FormLabel cardIDLabel,
                         FormTextField cardIDTextField, FormLabel existingCardLabel, JComboBox<String> existingCardCombo,
                         JRadioButton anonCardRB, JRadioButton basicCardRB, JRadioButton premiumCardRB,
                         FormLabel cardNameLabel, FormTextField cardNameTextField, FormLabel cardEmailLabel,
                         FormTextField cardEmailTextField) {
        super(source);
        this.purchaseForm = purchaseForm;
        this.purchaseTypeCombo = purchaseTypeCombo;
        this.generatedReceiptID = generatedReceiptID;
        this.categoriesMap = categoriesMap;
        this.receiptIDLabel = receiptIDLabel;
        this.receiptIDTextField = receiptIDTextField;
        this.cardIDLabel = cardIDLabel;
        this.cardIDTextField = cardIDTextField;
        this.existingCardLabel = existingCardLabel;
        this.existingCardCombo = existingCardCombo;
        this.anonCardRB = anonCardRB;
        this.basicCardRB = basicCardRB;
        this.premiumCardRB = premiumCardRB;
        this.cardNameLabel = cardNameLabel;
        this.cardNameTextField = cardNameTextField;
        this.cardEmailLabel = cardEmailLabel;
        this.cardEmailTextField = cardEmailTextField;
    }

    public JPanel getPurchaseForm() {
        return purchaseForm;
    }

    public JComboBox<String> getPurchaseTypeCombo() {
        return purchaseTypeCombo;
    }

    public int getGeneratedReceiptID() {
        return generatedReceiptID;
    }

    public HashMap<FormLabel, FormTextField> getCategoriesMap() {
        return categoriesMap;
    }

    public FormLabel getReceiptIDLabel() {
        return receiptIDLabel;
    }

    public FormTextField getReceiptIDTextField() {
        return receiptIDTextField;
    }

    public FormLabel getCardIDLabel() {
        return cardIDLabel;
    }

    public FormTextField getCardIDTextField() {
        return cardIDTextField;
    }

    public FormLabel getExistingCardLabel() {
        return existingCardLabel;
    }

    public JComboBox<String> getExistingCardCombo() {
        return existingCardCombo;
    }

    public JRadioButton getAnonCardRB() {
        return anonCardRB;
    }

    public JRadioButton getBasicCardRB() {
        return basicCardRB;
    }

    public JRadioButton getPremiumCardRB() {
        return premiumCardRB;
    }

    public FormLabel getCardNameLabel() {
        return cardNameLabel;
    }

    public FormTextField getCardNameTextField() {
        return cardNameTextField;
    }

    public FormLabel getCardEmailLabel() {
        return cardEmailLabel;
    }

    public FormTextField getCardEmailTextField() {
        return cardEmailTextField;
    }
}
