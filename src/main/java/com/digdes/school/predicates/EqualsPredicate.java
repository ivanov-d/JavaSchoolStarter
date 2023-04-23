package com.digdes.school.predicates;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class EqualsPredicate implements Predicate<Map<String, Object>> {
    private final String fieldName;
    private final Object fieldValue;

    public EqualsPredicate(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    @Override
    public boolean test(Map<String, Object> stringObjectMap) {
        return Objects.equals(String.valueOf(fieldValue), String.valueOf(stringObjectMap.get(fieldName)));
    }
}
