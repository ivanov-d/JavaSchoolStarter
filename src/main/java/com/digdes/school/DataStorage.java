package com.digdes.school;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataStorage {
    private List<Map<String, Object>> data = new ArrayList<>();

    public DataStorage(List<Map<String, Object>> data) {
        this.data = data;
    }


    public List<Map<String, Object>> insert(Map<String, Object> input) {
        Map<String, Object> values = new HashMap<>();
        values.put("id", null);
        values.put("lastName",null);
        values.put("age", null);
        values.put("cost", null);
        values.put("active",null);
        values.putAll(input);
        data.add(values);
        return Collections.singletonList(values);
    }
    public List<Map<String, Object>> select(Predicate<Map<String, Object>> predicate) {
        return data
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> delete(Predicate<Map<String, Object>> predicate) {
        List<Map<String, Object>> result = select(predicate);
        data.removeIf(predicate);
        return result;
    }

    public List<Map<String, Object>> update(Map<String, Object> values, Predicate<Map<String, Object>> predicate) {
        List<Map<String, Object>> result = select(predicate);

        data.removeIf(predicate);
        result.forEach(map -> map.putAll(values));
        data.addAll(result);
        return result;
    }
}
