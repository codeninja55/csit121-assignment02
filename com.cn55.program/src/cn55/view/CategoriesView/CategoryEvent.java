package cn55.view.CategoriesView;

import java.util.EventObject;

public class CategoryEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CategoryEvent(Object source) {
        super(source);
    }
}
