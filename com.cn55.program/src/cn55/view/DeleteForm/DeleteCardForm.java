package cn55.view.DeleteForm;

import cn55.view.ButtonListener;
import cn55.view.CustomComponents.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteCardForm extends JPanel {

    private JPanel deleteForm;
    private FormLabel cardIDLabel;
    private FormTextField cardIDTextField;
    private ErrorLabel errorLabel;
    private ErrorLabel ruleErrLabel;
    private FormButton deleteBtn;

    private ButtonListener cancelListener;
    private DeleteListener deleteListener;

    /*============================== CONSTRUCTORS ==============================*/
    public DeleteCardForm() {

        deleteForm = new JPanel(new GridBagLayout());
        //deleteLabel = new JLabel("Enter CARD ID to Delete Card");
        JButton cancelBtn = new JButton("Cancel Delete");
        cardIDLabel = new FormLabel("Delete by Card ID");
        cardIDLabel.setVisible(true);
        cardIDTextField = new FormTextField(20);
        cardIDTextField.setVisible(true);
        errorLabel = new ErrorLabel("CARD DOES NOT EXIST");
        ruleErrLabel = new ErrorLabel("INVALID CARD ID NUMBER");
        deleteBtn = new FormButton("Delete Card");
        deleteBtn.setVisible(true);

        setName("DeleteForm");
        setLayout(new BorderLayout());
        Dimension dim = getPreferredSize();
        dim.width = 800;
        setPreferredSize(dim);
        setMinimumSize(getPreferredSize());

        /* BORDERS - Adding 3 Borders around the form */
        Border outInnerBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1,1,1,1,Style.red500()),
                "Delete Cards",
                TitledBorder.LEFT,
                TitledBorder.CENTER,
                new Font("Verdana",Font.BOLD,22),
                Style.red500());
        Border inInnerBorder = BorderFactory.createEmptyBorder(25,25,25,25);
        Border innerBorder = BorderFactory.createCompoundBorder(outInnerBorder, inInnerBorder);
        Border outerBorder = BorderFactory.createEmptyBorder(1,10,10,10);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0; gc.gridy = 0; gc.weightx = 1; gc.weighty = 0.2;
        gc.anchor = GridBagConstraints.PAGE_END;
        gc.insets = new Insets(20,0,0,0);
        deleteForm.add(cardIDLabel, gc);

        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.2;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.insets = new Insets(20,0,20,0);
        Dimension textFieldDim = getPreferredSize();
        textFieldDim.width = 350;
        textFieldDim.height = 50;
        cardIDTextField.setPreferredSize(textFieldDim);
        deleteForm.add(cardIDTextField, gc);

        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.1;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.insets = new Insets(0,0,0,0);
        deleteForm.add(errorLabel, gc);

        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.1;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.insets = new Insets(0,0,0,0);
        deleteForm.add(ruleErrLabel, gc);

        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 2;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.insets = new Insets(20,0,20,0);
        deleteForm.add(deleteBtn, gc);

        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteEvent event = new DeleteEvent(this,
                        cardIDLabel, cardIDTextField, errorLabel, ruleErrLabel);

                if (deleteListener != null) {
                    deleteListener.deleteEventOccurred(event);
                }
            }
        });

        add(deleteForm, BorderLayout.CENTER);

        cancelBtn.setFont(Style.buttonFont());
        cancelBtn.setForeground(Style.btnTextColor());
        cancelBtn.setBackground(Style.red500());
        add(cancelBtn, BorderLayout.SOUTH);

        cancelBtn.addActionListener(e -> {
            if (cancelListener != null)
                cancelListener.buttonActionOccurred();
        });

        setVisible(false);
    }

    /*============================== MUTATORS ==============================*/
    public void setDeleteListener(DeleteListener listener) {
        this.deleteListener = listener;
    }

    public void setCancelListener(ButtonListener listener) {
        this.cancelListener = listener;
    }

    /*============================== ACCESSORS ==============================*/

    public JPanel getDeleteForm() {
        return deleteForm;
    }

    public FormLabel getCardIDLabel() {
        return cardIDLabel;
    }

    public FormTextField getCardIDTextField() {
        return cardIDTextField;
    }

    public FormButton getDeleteBtn() {
        return deleteBtn;
    }
}
