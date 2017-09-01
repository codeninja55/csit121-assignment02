package cn55.model;

import java.util.Comparator;

public class CategoriesComparator implements Comparator<Category> {
    @Override
    public int compare(Category o1, Category o2) {
        return o1.compareTo(o2);
    }
}
