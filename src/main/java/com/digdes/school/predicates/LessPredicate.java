package com.digdes.school.predicates;

import java.util.Map;
import java.util.function.Predicate;

class LessPredicate implements Predicate<Map<String, Object>> {
    private final String fieldName;
    private final Double fieldValue;

    public LessPredicate(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = Double.valueOf(fieldValue);
    }

    @Override
    public boolean test(Map<String, Object> stringObjectMap) {
        return fieldValue < (Double) stringObjectMap.get(fieldName);
    }
}
