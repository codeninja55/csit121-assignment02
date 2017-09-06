package cn55.model;

import java.util.Comparator;

public class CategoriesIDComparator implements Comparator<Category> {
    public int compare(Category o1, Category o2) {
        return Integer.compare(o1.getId(), o2.getId());
    }
}
