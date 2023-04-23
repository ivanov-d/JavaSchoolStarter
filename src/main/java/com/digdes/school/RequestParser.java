package com.digdes.school;

import com.digdes.school.predicates.PredicateParser;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RequestParser {
    public Operation parse(String request) {
        List<String> list = Collections.list(new StringTokenizer(request))
                .stream()
                .map(token -> (String) token)
                .toList();
        String action = new StringTokenizer(request).nextToken().toUpperCase();
        int valuesStart = request.toUpperCase().indexOf("VALUES");
        int whereStart = request.toUpperCase().indexOf("WHERE");
        Map<String, Object> values = new HashMap<>();
        if (valuesStart != -1) {
            String valuesSubstring = request.substring(valuesStart + 6).trim();
            if (whereStart != -1) {
                valuesSubstring = valuesSubstring.substring(0, valuesSubstring.toUpperCase().indexOf("WHERE"));
            }
            List<String> pairs = Arrays.stream(valuesSubstring.split(","))
                    .map(String::trim)
                    .toList();
            Map<String, Object> collect = pairs
                    .stream()
                    .collect(Collectors.toMap(s -> StringUtils.substringBefore(s, "=").replaceAll("[’‘`]", "").trim(),
                            s -> StringUtils.substringAfter(s, "=").trim().replaceAll("[’‘`]", "")));

            collect.computeIfPresent("id", (k, v) -> Long.valueOf((String) v));
            collect.computeIfPresent("lastName", (k, v) -> String.valueOf(v));
            collect.computeIfPresent("age", (k, v) -> Long.valueOf((String) v));
            collect.computeIfPresent("cost", (k, v) -> Double.valueOf((String) v));
            collect.computeIfPresent("active", (k, v) -> Boolean.valueOf((String) v));


            values.putAll(collect);
        }
        Predicate<Map<String, Object>> mapPredicate = map -> true;

        if (whereStart != -1) {
            String predicateSubstring = request.substring(whereStart + 5).trim();
            mapPredicate = new PredicateParser().parse(predicateSubstring);
        }
        return new Operation(action.toUpperCase(), values, mapPredicate);
    }
}
