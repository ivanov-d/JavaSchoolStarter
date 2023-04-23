package com.digdes.school.predicates;

import com.digdes.school.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;

public class PredicateParser {

    public Predicate<Map<String, Object>> parse(String request) {
        return Arrays.stream(request.split("(?i)or"))
                .map(this::parseWithAnd)
                .reduce(Predicate::or)
                .orElse(x -> true);
    }

    private Predicate<Map<String, Object>> parseWithAnd(String request) {
        return Arrays.stream(request.split("(?i)and"))
                .map(this::parseSimple)
                .reduce(Predicate::and)
                .orElse(x -> true);
    }

    private Predicate<Map<String, Object>> parseSimple(String request) {
        String separator;
        if (request.contains("!=")) {
            separator = "!=";
        } else if (request.contains("<=")) {
            separator = "<=";
        } else if (request.contains(">=")) {
            separator = ">=";
        } else if (request.contains(">")) {
            separator = ">";
        } else if (request.contains("<")) {
            separator = "<";
        } else if (request.contains("=")) {
            separator = "=";
        } else if (request.contains("ilike")) {
            separator = "ilike";
        } else if (request.contains("like")) {
            separator = "like";
        } else {
            throw new IllegalArgumentException("Bad WHERE " + request);
        }
        String fieldName = StringUtils.substringBefore(request, separator).trim().replaceAll("[’‘`]", "");
        String fieldValue = StringUtils.substringAfter(request, separator).trim().replaceAll("[’‘`]", "");
        return switch (separator) {
            case "=" -> new EqualsPredicate(fieldName, fieldValue);
            case "!=" -> new EqualsPredicate(fieldName, fieldValue).negate();
            case "<" -> new LessPredicate(fieldName, fieldValue);
            case ">" -> new GreaterPredicate(fieldName, fieldValue);
            case "<=" -> new LessPredicate(fieldName, fieldValue).or(new EqualsPredicate(fieldName, fieldValue));
            case ">=" -> new GreaterPredicate(fieldName, fieldValue).or(new EqualsPredicate(fieldName, fieldValue));
            case "ilike" -> new EqualsPredicate(fieldName, fieldValue);
            case "like" -> new EqualsPredicate(fieldName, fieldValue);
            default -> throw new IllegalArgumentException("Bad WHERE " + request);
        };
    }

}
