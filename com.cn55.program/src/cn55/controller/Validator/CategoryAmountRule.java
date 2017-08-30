package cn55.controller.Validator;

import java.util.HashMap;

public class CategoryAmountRule implements PurchaseRule {
    @Override
    public void validate(PurchaseValidData validData) {
        HashMap<String, Double> categories = validData.getCategories();

    }
}
