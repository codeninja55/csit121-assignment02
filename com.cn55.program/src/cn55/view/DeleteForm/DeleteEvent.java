package cn55.view.DeleteForm;

import cn55.view.CustomComponents.FormLabel;
import cn55.view.CustomComponents.FormTextField;

import javax.swing.*;
import java.util.EventObject;

public class DeleteEvent extends EventObject {
    private FormLabel searchIDLabel;
    private FormTextField searchIDTextField;
    private JLabel errorLabel;
    private JLabel ruleErrLabel;

    /*============================== CONSTRUCTORS ==============================*/
    public DeleteEvent(Object source) {
        super(source);
    }

    public DeleteEvent(Object source, FormLabel searchIDLabel, FormTextField searchIDTextField,
                       JLabel errorLabel, JLabel ruleErrLabel) {
        super(source);
        this.searchIDLabel = searchIDLabel;
        this.searchIDTextField = searchIDTextField;
        this.errorLabel = errorLabel;
        this.ruleErrLabel = ruleErrLabel;
    }

    /*============================== MUTATORS ==============================*/

    public void setSearchIDLabel(FormLabel searchIDLabel) {
        this.searchIDLabel = searchIDLabel;
    }

    public void setSearchIDTextField(FormTextField searchIDTextField) {
        this.searchIDTextField = searchIDTextField;
    }

    public void setErrorLabel(JLabel errorLabel) {
        this.errorLabel = errorLabel;
    }

    public void setRuleErrLabel(JLabel ruleErrLabel) {
        this.ruleErrLabel = ruleErrLabel;
    }

    /*============================== ACCESSORS ==============================*/

    public FormLabel getSearchIDLabel() {
        return searchIDLabel;
    }

    public FormTextField getSearchIDTextField() {
        return searchIDTextField;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public JLabel getRuleErrLabel() {
        return ruleErrLabel;
    }
}