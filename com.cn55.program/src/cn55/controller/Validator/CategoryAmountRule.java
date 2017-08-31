package cn55.controller.Validator;

import java.util.regex.Pattern;

public class CategoryAmountRule implements FormRule {

    private static final String VALUE_PATTERN = "\\d*";
    private static final Pattern regex = Pattern.compile(VALUE_PATTERN);

    public boolean validate(FormValidData validData) {
        return regex.matcher(validData.getCatValueStr()).matches();
    }
}
