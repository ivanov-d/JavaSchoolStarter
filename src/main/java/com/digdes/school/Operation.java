package com.digdes.school;

import java.util.Map;
import java.util.function.Predicate;

public class Operation {
    private String action;
    private Map<String, Object> values;

    private Predicate<Map<String, Object>> predicate;

    public Operation(String action, Map<String, Object> values, Predicate<Map<String, Object>> predicate) {
        this.action = action;
        this.values = values;
        this.predicate = predicate;
    }

    public String getAction() {
        return action;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public Predicate<Map<String, Object>> getPredicate() {
        return predicate;
    }
}
