package cn55.view.CategoriesView;

import cn55.view.CustomComponents.FormTextField;

import javax.swing.*;
import java.util.EventObject;

public class CategoryEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */

    private FormTextField categoryNameTextField;
    private JTextArea categoryDescTextField;

    /*============================== CONSTRUCTORS  ==============================*/
    public CategoryEvent(Object source) {
        super(source);
    }

    public CategoryEvent(Object source, FormTextField categoryNameTextField, JTextArea categoryDescTextField) {
        super(source);
        this.categoryNameTextField = categoryNameTextField;
        this.categoryDescTextField = categoryDescTextField;
    }

    /*============================== MUTATORS  ==============================*/

    public void setCategoryNameTextField(FormTextField categoryNameTextField) {
        this.categoryNameTextField = categoryNameTextField;
    }

    public void setCategoryDescTextField(JTextArea categoryDescTextField) {
        this.categoryDescTextField = categoryDescTextField;
    }

    /*============================== ACCESSORS  ==============================*/
    public FormTextField getCategoryNameTextField() {
        return categoryNameTextField;
    }

    public JTextArea getCategoryDescTextField() {
        return categoryDescTextField;
    }
}
