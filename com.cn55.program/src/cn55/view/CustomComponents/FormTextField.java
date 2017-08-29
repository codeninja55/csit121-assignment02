package cn55.view.CustomComponents;

import cn55.view.CustomComponents.Style;

import javax.swing.JTextField;

public class FormTextField extends JTextField {

    public FormTextField(int columns) {
        super(columns);
        setMinimumSize(getPreferredSize());
        setVisible(false);
        setFont(Style.textFieldFont());
    }

    public FormTextField(String text) {
        super(text);
        setMinimumSize(getPreferredSize());
        setVisible(false);
        setFont(Style.textFieldFont());
    }

    public FormTextField(String text, int columns) {
        super(text, columns);
        setMinimumSize(getPreferredSize());
        setVisible(false);
        setFont(Style.textFieldFont());
    }
}
