package cn55.model.CardModel;

import java.util.Comparator;

public class CategoriesComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
