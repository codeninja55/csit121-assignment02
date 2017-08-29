package cn55.view.CustomComponents;

import cn55.view.CustomComponents.Style;

import javax.swing.JTextField;

public class FormTextField extends JTextField {

    public FormTextField(int columns) {
        super(columns);
        setMinimumSize(getPreferredSize());
        setFont(Style.textFieldFont());
        setVisible(false);
    }

    public FormTextField(String text) {
        super(text);
        setMinimumSize(getPreferredSize());
        setFont(Style.textFieldFont());
        setVisible(false);
    }

    public FormTextField(String text, int columns) {
        super(text, columns);
        setMinimumSize(getPreferredSize());
        setFont(Style.textFieldFont());
        setVisible(false);
    }
}
