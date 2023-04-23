package com.digdes.school.predicates;

import java.util.Map;
import java.util.function.Predicate;

public class LikePredicate implements Predicate<Map<String, Object>> {
    private final String fieldName;
    private final String pattern;

    public LikePredicate(String fieldName, String pattern) {
        this.fieldName = fieldName;
        this.pattern = pattern;
    }

    @Override
    public boolean test(Map<String, Object> stringObjectMap) {
        String value = (String) stringObjectMap.get(fieldName);
        return value != null && value.contains(pattern);
    }
}
