package cn55.view.CategoriesView;

import cn55.model.Database;
import cn55.view.ButtonListener;
import cn55.view.CustomComponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoriesForm extends JPanel {

    private JPanel createCategoriesForm;
    private FormLabel categoryNameLabel;
    private FormTextField categoryNameTextField;
    private FormLabel categoryDescLabel;
    private JTextArea categoryDescTextField;

    private FormButton createBtn;
    private FormButton clearBtn;
    private JButton cancelBtn;

    private CategoryListener createCategoryListener;
    private ButtonListener cancelListener;

    public CategoriesForm() {
        /* INITIALIZE ALL COMPONENTS */
        createCategoriesForm = new JPanel(new GridBagLayout());
        categoryNameLabel = new FormLabel("Category Name");
        categoryNameTextField = new FormTextField(35);
        categoryDescLabel = new FormLabel("Category Description");
        categoryDescTextField = new JTextArea(5,35);
        createBtn = new FormButton("Create Category");
        clearBtn = new FormButton("Clear");
        cancelBtn = new JButton("Cancel New Category");

        /* INITIALIZE THIS PANEL */
        setLayout(new BorderLayout());
        /* SIZING - Make sure the form is at least always 800 pixels */
        Dimension dim = getPreferredSize();
        dim.width = 800;
        setPreferredSize(dim);
        setMinimumSize(getPreferredSize());
        setBorder(Style.formBorder("New Category"));

        /* FORM AREA */
        GridBagConstraints gc = new GridBagConstraints();

        /*========== FIRST ROW ==========*/
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0; gc.gridy = 0; gc.weightx = 1; gc.weighty = 0.1; gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.PAGE_END;
        gc.insets = new Insets(20,0,0,0);
        createCategoriesForm.add(categoryNameLabel, gc);

        /*========== NEW ROW ==========*/
        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.1; gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.insets = new Insets(20,0,0,0);
        Dimension textFieldDim = getPreferredSize();
        textFieldDim.width = 350;
        textFieldDim.height = 50;
        categoryNameTextField.setPreferredSize(textFieldDim);
        createCategoriesForm.add(categoryNameTextField, gc);

        /*========== NEW ROW ==========*/
        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.1; gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.insets = new Insets(20,0,0,0);
        createCategoriesForm.add(categoryDescLabel, gc);

        /*========== NEW ROW ==========*/
        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.1; gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.insets = new Insets(20,0,10,0);
        categoryDescTextField.setFont(Style.textAreaFont());

        createCategoriesForm.add(categoryDescTextField, gc);

        /*========== BUTTON ROW ==========*/
        gc.gridy++; gc.gridx = 0; gc.weightx = 0.5; gc.weighty = 3; gc.gridwidth = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(20,0,0,10);
        createCategoriesForm.add(createBtn, gc);

        gc.gridx = 1; gc.gridwidth = 1; gc.weightx = 1.5;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(20,10,0,0);
        createCategoriesForm.add(clearBtn, gc);

        add(createCategoriesForm, BorderLayout.CENTER);

        /* CANCEL BUTTON SETUP */
        cancelBtn.setFont(Style.buttonFont());
        cancelBtn.setForeground(Style.btnTextColor());
        cancelBtn.setBackground(Style.red500());
        add(cancelBtn, BorderLayout.SOUTH);

        /* SET FORM CUSTOM COMPONENTS VISIBLE */
        for (Component c : createCategoriesForm.getComponents()) {
            if (c instanceof FormLabel || c instanceof FormTextField || c instanceof FormButton) {
                c.setVisible(true);
            }
        }

        setVisible(false);

        /* BUTTON REGISTRATION AND CALLBACKS */
        FormListener handler = new FormListener();
        createBtn.addActionListener(handler);
        clearBtn.addActionListener(handler);
        cancelBtn.addActionListener(handler);
    }

    /*============================== MUTATORS  ==============================*/
    public void setCreateCategoryListener(CategoryListener listener) {
        this.createCategoryListener = listener;
    }

    public void setCancelListener(ButtonListener listener) {
        this.cancelListener = listener;
    }

    /*============================== ACCESSORS  ==============================*/


    /*=========================================================================*/
    /*============================== INNER CLASS ==============================*/
    /*=========================================================================*/
    class FormListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == createBtn) {
                CategoryEvent event = new CategoryEvent(this,
                                                    categoryNameTextField,
                                                    categoryDescTextField);

                if (createCategoryListener != null) {
                    createCategoryListener.createCategoryEventOccurred(event);
                }

            } else if (e.getSource() == clearBtn) {
                for (Component c : createCategoriesForm.getComponents()) {
                    if (c instanceof JTextField && ((JTextField) c).isEditable())
                        ((JTextField) c).setText("");

                    if (c instanceof JTextArea)
                        ((JTextArea) c).setText("");

                    if (c instanceof FormLabel)
                        c.setForeground(Color.BLACK);
                }
            }else if (e.getSource() == cancelBtn) {
                if (cancelListener != null) {
                    cancelListener.buttonActionOccurred();
                }
            }
        }
    }
}
