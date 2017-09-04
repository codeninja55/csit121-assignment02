package cn55.view.DeleteForm;

import cn55.view.CustomComponents.FormLabel;
import cn55.view.CustomComponents.FormTextField;

import javax.swing.*;
import java.util.EventObject;

public class DeleteEvent extends EventObject {
    private FormLabel idLabel;
    private FormTextField idTextField;
    private FormLabel nameLabel;
    private FormTextField nameTextField;
    private JLabel errorLabel;
    private JLabel ruleErrLabel;

    /*============================== CONSTRUCTORS ==============================*/
    public DeleteEvent(Object source) {
        super(source);
    }

    /* DELETE CONSTRUCTOR FOR DELETING CARDS */
    public DeleteEvent(Object source, FormLabel searchIDLabel, FormTextField searchIDTextField,
                       JLabel errorLabel, JLabel ruleErrLabel) {
        super(source);
        this.idLabel = searchIDLabel;
        this.idTextField = searchIDTextField;
        this.errorLabel = errorLabel;
        this.ruleErrLabel = ruleErrLabel;
    }

    public DeleteEvent(Object source, FormLabel categoryIDLabel, FormTextField categoryIDTextField,
                       FormLabel nameLabel, FormTextField nameTextField,
                       JLabel errorLabel, JLabel ruleErrLabel) {
        super(source);
        this.idLabel = categoryIDLabel;
        this.idTextField = categoryIDTextField;
        this.nameLabel = nameLabel;
        this.nameTextField = nameTextField;
        this.errorLabel = errorLabel;
        this.ruleErrLabel = ruleErrLabel;
    }

    /*============================== MUTATORS ==============================*/

    public void setIdLabel(FormLabel idLabel) {
        this.idLabel = idLabel;
    }

    public void setIdTextField(FormTextField idTextField) {
        this.idTextField = idTextField;
    }

    public void setNameLabel(FormLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public void setNameTextField(FormTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public void setErrorLabel(JLabel errorLabel) {
        this.errorLabel = errorLabel;
    }

    public void setRuleErrLabel(JLabel ruleErrLabel) {
        this.ruleErrLabel = ruleErrLabel;
    }

    /*============================== ACCESSORS ==============================*/

    public FormLabel getIdLabel() {
        return idLabel;
    }

    public FormTextField getIdTextField() {
        return idTextField;
    }

    public FormLabel getNameLabel() {
        return nameLabel;
    }

    public FormTextField getNameTextField() {
        return nameTextField;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public JLabel getRuleErrLabel() {
        return ruleErrLabel;
    }
}