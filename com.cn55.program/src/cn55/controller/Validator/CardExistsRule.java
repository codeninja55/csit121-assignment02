package cn55.controller.Validator;

import cn55.model.Database;

public class CardExistsRule implements ExistsRule {
    public int existsValidating(FormValidData validData) {
        return Database.getDBInstance().getCardMap().getOrDefault(validData.getCardID(), -1);
    }
}
